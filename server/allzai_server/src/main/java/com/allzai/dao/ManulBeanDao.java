package com.yeahmobi.gamelala.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.yeahmobi.gamelala.bean.BaseBean;

/**
 * 对符合规则的BaseBean类型提供公共的添加、修改方法
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @see     TransactionDaoSupport
 * @since   JDK 1.6
 */
public class ManulBeanDao extends TransactionDaoSupport<BaseBean> 
{

//	private static ManulBeanDao transacDao = new ManulBeanDao();
	
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
	
	
	/**
	 * 修改用户统计数据
	 * 
	 * @param userId        用户ID
	 * @param credit        积分
	 * @param extendCount   推广记录
	 * @param exchangeCount 兑换记录
	 * @return
	 */
	public boolean editUserCredit(int userId, int credit, int extendCount, int exchangeCount)
	{
		if(userId <= 0)
		{
			return false;
		}
		ArrayList<Integer> params = new ArrayList<Integer>(4);
		String updateSql = "UPDATE user_info SET ";
//		credit = credit + ?  WHERE id = ? ";
		if(credit != 0 )
		{
			updateSql += "credit = credit + ?, ";
			params.add(credit);
		}
		if(extendCount != 0)
		{
			updateSql += "extendCount = extendCount + ?, ";
			params.add(extendCount);
		}
		if(exchangeCount != 0 )
		{
			updateSql += "exchangeCount = exchangeCount + ?, ";
			params.add(exchangeCount);
		}
		updateSql = updateSql.substring(0, updateSql.lastIndexOf(','));
		updateSql += " WHERE id = ?";
		params.add(userId);
		
		return super.doChanage(updateSql, params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.ITransformDao#getConnection()
	 */
	public Connection getConnection() 
	{
		return TransactionManager.getContainer().get();
	}
}
