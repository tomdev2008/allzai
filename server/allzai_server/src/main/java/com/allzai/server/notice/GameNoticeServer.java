package com.allzai.server.notice;

import java.util.List;
import java.util.Map;

import com.allzai.dao.notice.GameNoticeDao;
import com.allzai.form.notice.GameNoticeForm;
import com.allzai.util.JsonUtil;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

public class GameNoticeServer {
	
	private static final GameNoticeServer gameNoticeServer = new GameNoticeServer();

	public static GameNoticeServer getInstance() {
		return gameNoticeServer;
	}

	public String getGameNotice(GameNoticeForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		JsonArray array = null;
		
		if(form.getPackageName() == null || form.getStart() < 0 || form.getStart() > form.getEnd()) {
			/**
			 * Jx0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Jx0002");
			json.put("info", "lost param");
			return json.toString();
		}
		
		try {
			Map<String, Object> map = GameNoticeDao.getInstance().getGameInfo(form.getPackageName());
			if(map == null || map.size() <= 0) {
				/**
				 * Jx0003:应用不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Jx0003");
				json.put("info", "game not exists");
				return json.toString();
			}

			List<Map<String, Object>> list = GameNoticeDao.getInstance().getGameNotice(Integer.parseInt(String.valueOf(map.get("id"))), form.getStart(), form.getEnd());
			int size = (list == null || list.size() <= 0) ? 0 : list.size();

			/**
			 * Jx0000:查询成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Jx0000");
			json.put("size", size);
			if(size > 0) {
				array = new JsonArray();
				for(Map<String, Object> tmp : list) {
					array.put(JsonUtil.formatMapToJson(tmp));
				}
				json.put("notices", array);
			}
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Jx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Jx0001");
			json.put("info", "System Error");
			return json.toString();
		}
	}

}
