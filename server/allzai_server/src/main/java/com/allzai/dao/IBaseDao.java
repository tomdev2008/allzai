package com.allzai.dao;

import java.util.List;
import java.util.Map;

/**
 * 基本的DB操作接口 自动提交事务
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-6
 * @param   <T> 参数类型
 * @since   JDK 1.6
 */
public interface IBaseDao<T> extends ITransformDao<T>
{
	/**
	 * 查询Bean通用方法<p>
	 * 将结果集中的第一行数据封装到一个对应的JavaBean实例中
	 * 
	 * @param querySql
	 * @param 类型的类返回的对象从 from <code>handle()</code>
	 * @param params
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("hiding")
	<T> T doQueryBean(String querySql, Class<T> classz, Object...params);
	
	
	/**
	 * 查询BeanList通用方法<p>
	 * 将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
	 * 
	 * @param querySql
	 * @param classz
	 * @param params
	 * @return
	 */
	@SuppressWarnings("hiding")
	<T> List<T> doQueryBeanList(String querySql, Class<T> classz, Object... params);
	
	/**
	 * 查询Map通用方法<p>
	 * 将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值
	 * 
	 * @param querySql
	 * @param params
	 * @return
	 * @throws RuntimeException
	 */
	Map<String, Object> doQueryMap(String querySql, Object... params);
	
	/**
	 * 查询MapList通用方法<p>
	 * 将结果集中的每一行数据都封装到一个Map里，然后存放到List
	 *  
	 * @param querySql
	 * @param params
	 * @return
	 * @throws RuntimeException
	 */
	List<Map<String, Object>> doQueryMapList(String querySql, Object... params);
	
}
