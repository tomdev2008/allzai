package com.royal.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.royal.model.User;

/**
 * 对象服务接受处理类
 * 
 * @author Royal
 * 
 */
public class ObjectMinaServerHandler extends IoHandlerAdapter {

	// 记录接受数据的次数
	private int count = 0;

	/**
	 * 当客户端 发送 的消息到达时
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		// 我们己设定了服务器解析消息的规则一个User对象为单位传输:
		User u = (User) message;
		System.out.println("这里是服务器(" + session.getLocalAddress() + ")\t收到客户机(" + session.getRemoteAddress() + ")发来的用户对象：" + u.toString() + "---------" + count);

		count++;
		if (count == 1000) {
			count = 0;
			// 服务器主动断开与客户端的连接
			session.close(true);
		}

		// 发送到客户端
		session.write(u);
	}

	/**
	 * 当一个客户端连接进入时
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("incomming client: " + session.getRemoteAddress());
	}

	/**
	 * 当一个客户端关闭时
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getRemoteAddress() + " client Disconnect!");
	}

	/**
	 * 当捕获到异常的时候
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.err.println("error!!!!!!!!!!!!!");
		super.exceptionCaught(session, cause);
	}

}
