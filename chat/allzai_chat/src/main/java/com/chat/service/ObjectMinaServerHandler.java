package com.chat.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.chat.util.Constancts;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

/**
 * 对象服务接受处理类
 */
public class ObjectMinaServerHandler extends IoHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(ObjectMinaServerHandler.class);

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
		
		logger.info(json.toString());
		
		if(!json.isNull("from")) {
			logger.info("这里是服务器(" + session.getLocalAddress() + ")\t收到客户机(" + session.getRemoteAddress() + ")发来的用户对象：" + json.getString("from") + "\t" + json.getString("to") + "\t" + json.getString("info"));
		}
		
		json = new JsonObject();
		json.put("from", "混沌");
		json.put("to", "菲菲");
		json.put("info", "好的, 我收到了!");
		session.write(json);
	}

	/**
	 * 当一个客户端连接进入时
	 */
	public void sessionOpened(IoSession session) throws Exception {
		doLogger(session, "客户端进入连接");
	}

	/**
	 * 当一个客户端关闭时
	 */
	public void sessionClosed(IoSession session) throws Exception {
		doLogger(session, "客户端退出连接");
	}

	/**
	 * 当捕获到异常的时候
	 */
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.close(true);
		doLogger(session, "客户端连接异常");
	}
	
	/**
	 * 记录请求的日志信息
	 */
	private void doLogger(IoSession session, String action) {
		JsonObject json = new JsonObject();
		json.put("Action", action);
		json.put("RemoteAddress", session.getRemoteAddress());
		json.put("CurrentTime", Constancts.sdf.format(new Date()));
		logger.info("Session = "+ json.toString());
	}
	
	public static void main(String[] args) {
		
//		JsonObject check = new JsonObject();
//		check.put("platform", "APP");	//平台
//		check.put("action", "CHECK");	//动作
//		check.put("myId", "123456");	//自己的ID
//		System.out.println("check="+check.toString());
		
//		JsonObject send = new JsonObject();
//		send.put("platform", "APP");	//平台
//		send.put("action", "SEND");		//动作
//		send.put("userId", "654321");	//好友的ID
//		send.put("myId", "123456");	//自己的ID
//		send.put("myName", "混沌");		//自己的昵称
//		send.put("message", "吃饭了没?");
//		System.out.println("send="+send.toString());
		
		JsonObject json = new JsonObject();
		json.put("result", Boolean.TRUE);
		json.put("code", "Dx0000");
		json.put("info", "接收成功");
		
		JsonArray data = new JsonArray();
		
		JsonObject msg = new JsonObject();
		msg.put("type", 1);
		msg.put("userId", "100000");	//对方的ID
		msg.put("nikeName", "系统");	//对方的昵称
		msg.put("message", "你有一条系统消息");
		msg.put("sendTime", "2014-04-17 16:17:10");
		data.put(msg);
		
		msg = new JsonObject();
		msg.put("type", 0);
		msg.put("userId", "654321");	//对方的ID
		msg.put("nikeName", "菲菲");	//对方的昵称
		msg.put("message", "吃饭了没?");
		msg.put("sendTime", "2014-04-17 16:17:10");
		data.put(msg);
		
		msg = new JsonObject();
		msg.put("type", 0);
		msg.put("userId", "654321");	//对方的ID
		msg.put("nikeName", "菲菲");	//对方的昵称
		msg.put("message", "我都吃好了");
		msg.put("sendTime", "2014-04-17 16:17:11");
		data.put(msg);
		
		json.put("size", 3);
		json.put("data", data);
		
		System.out.println(json.toString());
		
		JsonObject ret = new JsonObject(json.toString());
		if(ret.getBoolean("result") && ret.getInt("size") > 0) {
			
			JsonArray array = ret.getJsonArray("data");
			
			for(int i = 0, len = array.length(); i < len; i++) {
				JsonObject ms = array.getJsonObject(i);
				if(ms.getInt("type") == 1) {
					System.out.println("系统消息="+ms.getString("message") + ", 时间:" + ms.getString("sendTime"));
				} else {
					System.out.println(ms.getString("nikeName") + "对你说:" + ms.getString("message") + ", 时间:" + ms.getString("sendTime"));
				}
			}
		}
		
	}

}
