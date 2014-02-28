package com.allzai.dao.upload;

import com.allzai.dao.AutoBeanDao;

public class FileUploadDao {
	
private static FileUploadDao fileUploadDao = new FileUploadDao();
	
	public static FileUploadDao getInstance() {
		return fileUploadDao;
	}

	public boolean doEditUserHeadPortrait(int id, String newkey, String newurl) {
		String sql = "UPDATE user_info SET headKey = ?, headPortrait = ? where id = ?";
		return AutoBeanDao.getInstance().doChanage(sql, new Object[]{newkey, newurl, id});
	}

}
