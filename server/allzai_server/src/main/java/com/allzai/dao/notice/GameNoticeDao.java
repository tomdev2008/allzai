package com.allzai.dao.notice;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;

public class GameNoticeDao extends BaseJdbcDaoSupport<BaseBean> {
	
	private static final GameNoticeDao gameNoticeDao = new GameNoticeDao();

	public GameNoticeDao() {
	}

	public static GameNoticeDao getInstance() {
		return gameNoticeDao;
	}

	/**
	 * 获取游戏的更新通知
	 * @param gameId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getGameNotice(int gameId, int start, int end) {
		
		String sql = "SELECT * FROM game_notice WHERE gameId = ? ORDER BY createTime DESC limit ?,?";

		 return super.doQueryMapList(sql, new Object[]{gameId, start, end - start});
		
	}

	@Override
	public Connection getConnection() {
		return DatabaseManager.getSlaveConn();
	}

	/**
	 * 通过包名查找应用编号
	 * @param packageName
	 * @return
	 */
	public Map<String, Object> getGameInfo(String packageName) {
		
		String querySql = "SELECT id FROM app_info WHERE packageName = ?";
		
		return super.doQueryMap(querySql, packageName);
	}

	/**
	 * 获取用户与游戏以及设备的绑定关系
	 * @param userId
	 * @param deviceId
	 * @param gameId
	 * @return
	 */
	public Map<String, Object> getGamePlayHistory(int userId, int gameId) {
		
		String querySql = "SELECT * FROM play_history WHERE userId = ? AND gameId = ?";
		
		return super.doQueryMap(querySql, userId, gameId);
	}

	/**
	 * 添加用户与游戏以及设备的绑定关系
	 * @param userID
	 * @param deviceId
	 * @param gameId
	 * @return
	 */
	public boolean setGamePlayHistory(int userID, int gameId) {

		String querySql = "INSERT INTO play_history(userId,gameId,createTime) VALUES(?,?,now())";
		
		return super.doChanage(querySql, userID, gameId);
		
	}

}
