package com.chat.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.restfb.json.JsonObject;

/**
 * 对象客户端接受处理类
 */
public class ObjectMinaClientHandler extends IoHandlerAdapter {

	// 当一个服务端连结进入时
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("connect server : " + session.getRemoteAddress());
		
		//传递GPS等信息
		//传递用户等信息
		session.write(new JsonObject());
	}

	// 当一个服务端关闭时
	public void sessionClosed(IoSession session) {
		System.out.println(session.getRemoteAddress() + " server Disconnect !");
	}

	// 当服务器发送的消息到达时:
	public void messageReceived(IoSession session, Object message) throws Exception {

		JsonObject json = (JsonObject) message;
		if(!json.isNull("from")) {
			System.out.println("这里是客户端(" + session.getLocalAddress() + ")\t服务器(" + session.getRemoteAddress() + ")发来的消息: " + json.getString("from") + "\t" + json.getString("to") + "\t" + json.getString("info"));
		}
		
		session.write(new JsonObject());
	}

}
