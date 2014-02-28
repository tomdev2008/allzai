package com.allzai.dao;

import java.sql.Connection;

import com.allzai.bean.BaseBean;
import com.allzai.system.DatabaseManager;

/**
 * BaseBean公用Dao层支持,自动提交事务<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-18
 * @see     BaseJdbcDaoSupport
 * @since   JDK 1.6
 */
public class AutoBeanDao extends BaseJdbcDaoSupport<BaseBean> 
{
	private static final AutoBeanDao autoDao = new AutoBeanDao();

	public static AutoBeanDao getInstance()
	{
		return AutoBeanDao.autoDao;
	}
	
	/* 
	 * (non-Javadoc)
	 * 
	 * @see com.yeahmobi.gamelala.dao.ITransformDao#getConnection()
	 */
	public Connection getConnection() 
	{
		return DatabaseManager.getMasterConn();
	}

	/**
	 * 添加一个简单的Bean 并返回去其ID<p>
	 *  Bean 必须实现getSaveCondition()方法
	 * 
	 * @param bean
	 * @return
	 */
	public int getAddSingleBean(BaseBean bean)
	{
		Object[] results = bean.getSaveCondition();
		return super.doSave(results[0].toString(), (Object[])results[1]);
	}

	/**
	 * 添加一个简单的Bean
	 * Bean 必须实现getSaveCondition()方法
	 * 
	 * @param bean
	 * @return
	 */
	public boolean addSingleBean(BaseBean bean)
	{
		Object[] results = bean.getSaveCondition();
		return super.doChanage(results[0].toString(), (Object[])results[1]);
	}
	
	/**
	 * 编辑一个简单的Bean
	 * Bean 必须实现getEditCondition()方法
	 * 
	 * @param bean
	 * @return
	 */
	public boolean editSingleBean(BaseBean bean)
	{
		Object[] results = bean.getEditCondition();
		return super.doChanage(results[0].toString(), (Object[])results[1]);
	}

}
