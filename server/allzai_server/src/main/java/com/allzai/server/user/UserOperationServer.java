package com.allzai.server.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.allzai.dao.notice.GameNoticeDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.exception.DaoException;
import com.allzai.form.user.GetOperationForm;
import com.allzai.util.JsonUtil;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

/**
 * 用户操作服务
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-11
 * @see     UserOperationSlaveDao#queryUserOperationList(InputBean)
 * @since   JDK 1.6
 */
public class UserOperationServer 
{
	private static final UserOperationServer operationServer = new UserOperationServer();
	
	private static final Logger logger = Logger.getLogger(UserOperationServer.class);

	public static UserOperationServer getInstance()
	{
		return UserOperationServer.operationServer;
	}
	
	public String getOperationListForJson(GetOperationForm form) throws Exception
	{
		JsonObject json = new JsonObject();
		
		if(form == null || form.getUserId() <= 0 || form.getStart() < 0 || form.getEnd() < form.getStart()) {	
			/**
			 * Cx0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Cx0002");
			json.put("info", "Invalid request.");
			return json.toString();
		}

		try 
		{
			Map<String, Object> map = UserSlaveDao.getInstance().queryUserInfoByUserId(form.getUserId());
			if(map == null || map.size() <= 0) {
				/**
				 * Cx0003:用户名不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Cx0003");
				json.put("info", "user not exists.");
				return json.toString();
			}
			
			map = GameNoticeDao.getInstance().getGameInfo(form.getPackageName());
			if(map == null || map.size() <= 0) {
				/**
				 * Cx0003:应用不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Cx0003");
				json.put("info", "Invalid request.");
				return json.toString();
			} else {
				int gameId = Integer.parseInt(String.valueOf(map.get("id")));
				map = UserSlaveDao.getInstance().queryGamePlayHistoryList(form.getUserId(), gameId);
				
				JsonArray array = new JsonArray();
				array.put(JsonUtil.formatMapToJson(map));
				//还有充值记录需要补充
				
				/**
				 * Cx0000:查询成功
				 */
				json.put("result", Boolean.TRUE);
				json.put("code", "Cx0000");
				json.put("info", "OK");
				json.put("times", array);
				return json.toString();
			}
		}
		catch (DaoException e)
		{
			e.printStackTrace();
			logger.warn("查询用户操作记录列表失败，详细信息如下:", e);
			
			/**
			 * Cx0001:内部错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Cx0001");
			json.put("info", "System Error");
			return json.toString();
		}
	}
}
