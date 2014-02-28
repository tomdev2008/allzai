package com.yeahmobi.gamelala.exception;


/**
 * Dao层异常类<p>
 * 所有DAO层都抛出此异常。
 * 
 * @author Eric
 * @version hasoffer-0.0.1, 2013-9-6
 * @since JDK 1.6
 */
public class DaoException extends Exception 
{
	/** */
	private static final long serialVersionUID = -7868057363475799602L;

	public DaoException(String msg)
	{
		super(msg);
	}

	public DaoException(Throwable t) 
	{
		super(t);
		setStackTrace(t.getStackTrace());
	}

	public DaoException(String msg, Throwable t) 
	{
		super(msg, t);
		setStackTrace(t.getStackTrace());
	}

}
