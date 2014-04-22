package com.allzai.server.topic;

import com.allzai.form.topic.GameTopicForm;
import com.restfb.json.JsonObject;

public class GameTopicServer {
	
	private static final GameTopicServer gameTopicServer = new GameTopicServer();

	public static GameTopicServer getInstance() {
		return gameTopicServer;
	}

	public JsonObject getGameTopic(GameTopicForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		if(form.getPackageName() == null || form.getStart() < 0 || form.getStart() > form.getEnd()) {
			/**
			 * Jx0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Jx0002");
			return json;
		}
		
		try {
			

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Jx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Jx0001");
			return json;
		}
	}

}
