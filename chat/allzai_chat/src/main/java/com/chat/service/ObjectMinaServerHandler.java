package com.chat.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.chat.util.Constancts;
import com.restfb.json.JsonObject;

/**
 * 对象服务接受处理类
 */
public class ObjectMinaServerHandler extends IoHandlerAdapter {
	
	private static final Logger log = Logger.getLogger(ObjectMinaServerHandler.class);

	/**
	 * 当客户端 发送 的消息到达时
	 */
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		JsonObject json = new JsonObject();

//		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
//		for (IoSession sess : sessions) {
//            sess.write("群发消息!!!");
//        }
		
		json = (JsonObject) message;
		
		if(!json.isNull("from")) {
			System.out.println("这里是服务器(" + session.getLocalAddress() + ")\t收到客户机(" + session.getRemoteAddress() + ")发来的用户对象：" + json.getString("from") + "\t" + json.getString("to") + "\t" + json.getString("info"));
		}
		
		session.write(new JsonObject());
	}

	/**
	 * 当一个客户端连接进入时
	 */
	public void sessionOpened(IoSession session) throws Exception {
		
		JsonObject json = new JsonObject();
		
		json.put("Action", "客户端进入连接");
		json.put("RemoteAddress", session.getRemoteAddress());
		json.put("LocalAddress", session.getLocalAddress());
		json.put("CurrentTime", Constancts.sdf.format(new Date()));
		
		log.info("Session = "+ json.toString());
	}

	/**
	 * 当一个客户端关闭时
	 */
	public void sessionClosed(IoSession session) throws Exception {
		
		JsonObject json = new JsonObject();
		
		json.put("Action", "客户端退出连接");
		json.put("RemoteAddress", session.getRemoteAddress());
		json.put("LocalAddress", session.getLocalAddress());
		json.put("CurrentTime", Constancts.sdf.format(new Date()));
		
		log.info("Session = "+ json.toString());
	}

	/**
	 * 当捕获到异常的时候
	 */
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		
		JsonObject json = new JsonObject();
		
		json.put("Action", "客户端连接异常");
		json.put("RemoteAddress", session.getRemoteAddress());
		json.put("LocalAddress", session.getLocalAddress());
		json.put("CurrentTime", Constancts.sdf.format(new Date()));
		
		log.info("Session = "+ json.toString());
	}

}
