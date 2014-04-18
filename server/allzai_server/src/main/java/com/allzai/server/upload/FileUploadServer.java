package com.allzai.server.upload;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.allzai.bean.UserBean;
import com.allzai.cdn.CdnUtil;
import com.allzai.dao.upload.FileUploadDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.face.FaceUtil;
import com.allzai.form.upload.FileUploadForm;
import com.allzai.util.Constants;
import com.allzai.util.StringUtil;
import com.allzai.util.UploadFileUtil;
import com.restfb.json.JsonObject;

public class FileUploadServer {
	
	private static String fileStorePath = null, fileTempPath = null;
	
	private static final FileUploadServer fileUploadServer = new FileUploadServer();
	
	public FileUploadServer() {initPath();}
	
	public static FileUploadServer getInstance() {
		return fileUploadServer;
	}
	
	public JsonObject doFileUpload(FileUploadForm form, HttpServletRequest req) throws Exception {
		
		JsonObject json = new JsonObject();
		
		if(form.getUserId() <= 0) {
			/**
			 * Kx0006:用户不存在 
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Kx0006");
			return json;
		}
		
		boolean suc = false;
		String newurl = null;
		String code = "Kx0001";

		try {
			/**获取用户基本信息*/
			UserBean user = UserSlaveDao.getInstance().queryUserByUserId(form.getUserId());
			if(user == null) {
				/**
				 * Kx0006:用户不存在 
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Kx0006");
				return json;
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					/**获取表单参数*/
					//if ("name".equals(item.getFieldName())) {}
				} else {
					if (item.getName() != null && !item.getName().trim().isEmpty()) {
						/**文件信息校验*/
						String str = checkFileItem(item);
						if(str != null) {code="Kx0002";break;}
						
						/**生成临时文件*/
						String srcFileName = form.getUserId() + "_" + System.currentTimeMillis() + Constants.FileSuffix;
						String path = fileTempPath + srcFileName;
						File srcFile = new File(path);
						
						item.write(srcFile);
						if (!srcFile.exists()) {code="Kx0003";break;}
						
						/**图片人脸和性别识别*/
						if(!StringUtil.isEmpty(form.getGender())) {
							String url = "http://" + req.getServerName() + ":" + req.getServerPort();
							boolean faced = false;
							JSONObject face = FaceUtil.getInstance().doFace(url + "/upload/temp/" + srcFileName);
							if(face == null || face.getJSONArray("face").length() <= 0) {
								code="Kx0007";srcFile.delete();break;
							} else {
								JSONArray array = face.getJSONArray("face");
								for(int i = 0, len= array.length(); i < len; i++) {
									if(array.getJSONObject(i).getJSONObject("attribute").getJSONObject("gender").getString("value").equals(form.getGender())) {
										faced = true;break;
									}
								}
							}
							if(!faced) {code="Kx0008";srcFile.delete();break;}
						}
						
						/**压制存储文件*/
						String md5 = UploadFileUtil.getFileMD5String(srcFile);
						String tarFileName = form.getUserId() + "_" + md5 + Constants.FileSuffix;
						String key = tarFileName;
						path = fileStorePath  + tarFileName;
						File tarFile = new File(path);
						if(UploadFileUtil.doFileCompress(fileTempPath, fileStorePath, srcFileName, tarFileName)) {
							
							/**文件上传至七牛*/
							try {
								JsonObject ret = new JsonObject(CdnUtil.getInstance().putFile2Cdn(key, path));
								if(ret.has("hash") && ret.has("key")) {
									String newkey = ret.getString("key");
									newurl = CdnUtil.getInstance().getFileFromCdn(newkey);
									/**更新用户头像信息*/
									suc = FileUploadDao.getInstance().doEditUserHeadPortrait(form.getUserId(), newkey, newurl);
									String oldkey = user.getHeadKey();
									if(oldkey != null && !newkey.equals(oldkey)) {
										CdnUtil.getInstance().delFileFromCdn(oldkey);
									}
								}
							} catch (Exception e) {
								srcFile.delete();code="Kx0005";
								e.printStackTrace();
							}
						} else {
							code="Kx0004";
						}
						
						/**清理临时文件*/
						tarFile.delete();
						srcFile.delete();
					} else {
						code="Kx0002";break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			/**
			 * Kx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Kx0001");
			return json;
		}
		
		if(suc && newurl != null) {
			/**
			 * Kx0000:上传成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Kx0000");
			json.put("headPortrait", newurl);
			return json;
		} else {
			/**
			 * Kx0000:上传失败
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", code);
			
			return json;
		}
	}

	/**
	 * 检查上传的文件格式以及大小等信息
	 * @param item
	 * @return
	 */
	private String checkFileItem(FileItem item) {
		String msg = null;
		if (!item.getContentType().contains("image")) {
			msg = "filetype is not a image.";
		}
		if(item.getSize() < Constants.MIN_FILE_SIZE) {
			msg = "filesize is less than min allowed.";
		}
		if(item.getSize() > Constants.MAX_FILE_SIZE) {
			msg = "filesize is more than max allowed.";
		}
		return msg;
	}

	/**
	 * 初始化上传后的临时存储路径
	 */
	private void initPath() {
		fileStorePath = UploadFileUtil.getFileStorePath();
		fileTempPath = UploadFileUtil.getFileTempPath();
	}

}
