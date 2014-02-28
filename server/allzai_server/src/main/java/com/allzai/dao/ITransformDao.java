package com.allzai.dao;

import java.sql.Connection;

/**
 * AUD上级接口<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @since   JDK 1.6
 */
public interface ITransformDao<T> 
{
	Connection getConnection(); 
	
	/**
	 * 执行一个插入、并返回生成主键
	 * 
	 * @param  sql
	 * @param  params
	 * @return -1:数据库未连接,-2:已执行未成功, 0:方法参数错误,other：生成后的主键
	 * @throws RuntimeException
	 */
	int doSave(String insertSql,Object ...params);
	
	/**
	 * 执行一个SQL插入、更新或删除查询。
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws RuntimeException
	 */
	boolean doChanage(String sql, Object ...params);
	
	/**
	 * 执行一个批SQL插入、更新或删除查询。
	 * 
	 * @param sql
	 * @param params 参数数组参数的查询替换。每一行
	 * @return
	 * @throws RuntimeException
	 */
	boolean doBatchChanage(String sql, Object[][] params);
}
