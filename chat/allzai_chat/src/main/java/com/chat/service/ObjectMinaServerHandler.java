package com.chat.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

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
	public void messageReceived(IoSession session, Object object) throws Exception {
		
		JsonObject json = new JsonObject(object);
		JsonObject result = execActionResult(json);
		if(result == null) {return;}
		
		doLogger(session, "客户端发来消息");
		logger.info(json.toString());

		doLogger(session, "响应客户端消息");
		logger.info(result.toString());
		session.write(result);

//		 JsonObject json = new JsonObject();
//		 Collection<IoSession> sessions = session.getService().getManagedSessions().values();
//		 for (IoSession sess : sessions) {
//			 sess.write("群发消息!!!");
//		 }
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
		logger.info("Session = " + json.toString());
	}

	/**
	 * 解析并响应发送的消息
	 * @param json
	 * @return
	 */
	private JsonObject execActionResult(JsonObject json) {
		JsonObject result = new JsonObject();
		
		if (json.has("platform") && json.has("action") && json.has("myId")) {
			String platform = json.getString("platform");
			String action = json.getString("action");
			int myId = json.getInt("myId");
			// String version = json.getString("version");

			if (myId > 0 && Constancts.platform_APP.equals(platform)) {
				
				if (Constancts.action_CHECK.equals(action)) {
					/**
					 * Cx0000: 确认成功
					 */
					result.put("result", Boolean.TRUE);
					result.put("code", "Cx0000");
					result.put("info", "确认成功");
					
					JsonArray data = new JsonArray();
					result.put("data", data);
					result.put("size", 0);
					
					if(Constancts.MSG.containsKey(myId)) {
						try {
							synchronized (Constancts.MSG) {
								Queue<JsonObject> queue = Constancts.MSG.get(myId);
								if(queue != null && queue.size() > 0) {
									int size = queue.size();
									if(size > 0) {
										while(size > 0) {
											data.put(queue.poll());
											size--;
										}
										result.put("data", data);
										result.put("size", size);
									}
								} else {
									Constancts.MSG.remove(myId);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							/**
							 * Cx0001:内部异常
							 */
							result.put("result", Boolean.FALSE);
							result.put("code", "Cx0001");
							result.put("info", "内部异常");
						}
					}
				} else if (Constancts.action_SEND.equals(action)) {
					try {
						/**
						 * Sx0000: 接收成功
						 */
						int userId = json.getInt("userId");
						
						Queue<JsonObject> queue = new LinkedList<JsonObject>();
						json.put("sendTime", Constancts.sdf.format(new Date()));
						json.put("type", 0);
						json.put("userId", myId);
						json.remove("myId");
						
						synchronized (Constancts.MSG) {
							if(Constancts.MSG.containsKey(userId)) {
								queue = Constancts.MSG.get(userId);
								if(queue == null) {
									queue = new LinkedList<JsonObject>();
								}
							}
							queue.add(json);
							Constancts.MSG.put(userId, queue);
						}
						
						/**
						 * Sx0000:接收成功
						 */
						result.put("result", Boolean.TRUE);
						result.put("code", "Sx0000");
						result.put("info", "接收成功");
						
						JsonArray data = new JsonArray();
						result.put("data", data);
						result.put("size", 0);
						
						if(Constancts.MSG.containsKey(myId)) {
							try {
								synchronized (Constancts.MSG) {
									queue = Constancts.MSG.get(myId);
									int size = queue.size();
									if(size > 0) {
										while(size > 0) {
											data.put(queue.poll());
											size--;
										}
										result.put("data", data);
										result.put("size", size);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								/**
								 * Sx0001:内部异常
								 */
								result.put("result", Boolean.FALSE);
								result.put("code", "Sx0001");
								result.put("info", "内部异常");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						/**
						 * Sx0001:内部异常
						 */
						result.put("result", Boolean.FALSE);
						result.put("code", "Sx0001");
						result.put("info", "内部异常");
					}
				} else {
					/**
					 * Cx0003:未知动作
					 */
					result.put("result", Boolean.FALSE);
					result.put("code", "Cx0003");
					result.put("info", "未知动作");
				}
			} else {
				/**
				 * Cx0004:确认失败
				 */
				result.put("result", Boolean.FALSE);
				result.put("code", "Cx0004");
				result.put("info", "确认失败");
			}
			return result;
		}
		return null;
	}

}
