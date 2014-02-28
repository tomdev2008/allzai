package com.allzai.action.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.upload.FileUploadForm;
import com.allzai.server.upload.FileUploadServer;

public class FileUploadAction extends BaseActionSupport {

	/**
	 * 头像文件上传
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		FileUploadForm form = (FileUploadForm) obj;
		
		return FileUploadServer.getInstance().doFileUpload(form, req);
	}

	@Override
	public Class<FileUploadForm> getFromBean() {
		return FileUploadForm.class;
	}

}
