package com.allzai.dao.topic;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;

public class GameTopicDao extends BaseJdbcDaoSupport<BaseBean> {
	
	private static final GameTopicDao gameTopicDao = new GameTopicDao();

	public GameTopicDao() {
	}

	public static GameTopicDao getInstance() {
		return gameTopicDao;
	}

	/**
	 * 获取游戏的更新通知
	 * @param gameId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getTopicNotice(int gameId, int start, int end) {
		
		String sql = "SELECT * FROM game_topic WHERE gameId = ? ORDER BY createTime DESC limit ?,?";

		 return super.doQueryMapList(sql, new Object[]{gameId, start, end - start});
		
	}

	@Override
	public Connection getConnection() {
		return DatabaseManager.getSlaveConn();
	}

}
