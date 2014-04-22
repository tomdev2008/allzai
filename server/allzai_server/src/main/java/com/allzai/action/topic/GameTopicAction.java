package com.allzai.action.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.topic.GameTopicForm;
import com.allzai.server.topic.GameTopicServer;
import com.restfb.json.JsonObject;

public class GameTopicAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		GameTopicForm form = (GameTopicForm) obj;
		
		return GameTopicServer.getInstance().getGameTopic(form);
	}

	@Override
	public Class<GameTopicForm> getFromBean() {
		return GameTopicForm.class;
	}

}
