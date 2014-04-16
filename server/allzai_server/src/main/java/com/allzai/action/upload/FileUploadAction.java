package com.allzai.action.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.upload.FileUploadForm;
import com.allzai.server.upload.FileUploadServer;
import com.restfb.json.JsonObject;

public class FileUploadAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		FileUploadForm form = (FileUploadForm) obj;
		
		return FileUploadServer.getInstance().doFileUpload(form, req);
	}

	@Override
	public Class<FileUploadForm> getFromBean() {
		return FileUploadForm.class;
	}

}
