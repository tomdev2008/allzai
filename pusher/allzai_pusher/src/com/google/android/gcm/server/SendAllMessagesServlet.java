/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.server;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet that adds a new message to all registered devices.
 * <p>
 * This servlet is used just by the browser (i.e., not device).
 */
@SuppressWarnings("serial")
public class SendAllMessagesServlet extends BaseServlet {
	
	private static final Logger logger = Logger.getLogger(SendAllMessagesServlet.class);

	private static final int MULTICAST_SIZE = 1000;
	
	//消息内容键
	private static final String PUSH_MSG_KEY = "push";
	//消息内容值
	private static String PUSH_MSG_BODY = null;
	//消息的类型
	private static final String PUSH_KEY_TYPE = "type";
	//消息类型值
	private static String PUSH_KEY_BODY = null;
	
	private Sender sender;

	private static final Executor threadPool = Executors.newFixedThreadPool(5);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		sender = newSender(config);
	}

	/**
	 * Creates the {@link Sender} based on the servlet settings.
	 */
	protected Sender newSender(ServletConfig config) {
		String key = (String) config.getServletContext().getAttribute(
				ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
		return new Sender(key);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		logger.info("into get push send all");
		this.doPost(req, resp);
		logger.info("out get push send all");
		
	}

	/**
	 * Processes the request to add a new message.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setCharacterEncoding("UTF-8");

		PUSH_MSG_BODY = req.getParameter(PUSH_MSG_KEY);
		
		//UTF-8
		if(PUSH_MSG_BODY != null && !PUSH_MSG_BODY.trim().isEmpty()) {
			PUSH_MSG_BODY = new String(PUSH_MSG_BODY.getBytes("ISO8859-1"), "UTF-8");
			logger.info(PUSH_MSG_BODY);
		} else {
			resp.getWriter().append("not provide message needs to be sent.");
			return;
		}

		PUSH_KEY_BODY = req.getParameter(PUSH_KEY_TYPE);
		
		String status = "ok";
		
		if(PUSH_MSG_BODY == null || PUSH_MSG_BODY.trim().isEmpty()) {
			status = "Please provide the needed push messages!";
		} else {
			Map<Integer, String> devices = Datastore.getDevices();
			
			if (devices.isEmpty()) {
				status = "Message ignored as there is no device registered!";
			} else {
				// NOTE: check below is for demonstration purposes; a real
				// application
				// could always send a multicast, even for just one recipient
				if (devices.size() == 1) {
					// send a single message using plain post
					for(String registrationId : devices.values()) {
						Message message = new Message.Builder().addData(PUSH_MSG_KEY, PUSH_MSG_BODY).addData(PUSH_KEY_TYPE, PUSH_KEY_BODY).build();
						Result result = sender.send(message, registrationId, 5);
						status = "Sent message to one device: " + result;
						break;
					}
				} else {
					// send a multicast message using JSON
					// must split in chunks of 1000 devices (GCM limit)
					int total = devices.size();
					Map<String, Integer> partialDevices = new HashMap<String, Integer>(MULTICAST_SIZE);
					int counter = 0;
					int tasks = 0;
					int partialSize = 0;
					for (Integer userId : devices.keySet()) {
						counter++;
						partialDevices.put(devices.get(userId), userId);
						partialSize = partialDevices.size();
						if (partialSize == MULTICAST_SIZE || counter == total) {
							asyncSend(partialDevices);
							partialDevices = new HashMap<String, Integer>(MULTICAST_SIZE);
							tasks++;
						}
					}
					status = "Asynchronously sending " + tasks + " multicast messages to " + total + " devices";
				}
			}
		}
		
		logger.info(status.toString());
		
		req.setAttribute(HomeServlet.ATTRIBUTE_STATUS, status.toString());
		getServletContext().getRequestDispatcher("/secure/home").forward(req, resp);
	}

	private void asyncSend(Map<String, Integer> partialDevices) {
		// make a copy
		final Map<String, Integer> devices_map = new HashMap<String, Integer>(partialDevices);
		final List<String> devices_list = new ArrayList<String>(partialDevices.size());
		for(String key : devices_map.keySet()) {
			devices_list.add(key);
		}
		
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = new Message.Builder().addData(PUSH_MSG_KEY, PUSH_MSG_BODY).addData(PUSH_KEY_TYPE, PUSH_KEY_BODY).build();
				MulticastResult multicastResult;
				try {
					multicastResult = sender.send(message, devices_list, 5);
				} catch (Exception e) {
					logger.error("Error posting messages", e);
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devices_list.size(); i++) {
					String regId = devices_list.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						logger.info("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
						String canonicalRegId = result.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration id:
							// update it
							logger.info("canonicalRegId " + canonicalRegId);
							Datastore.updateRegistration(regId, canonicalRegId, devices_map.get(regId));
						}
					} else {
						String error = result.getErrorCodeName();
						logger.error("Error sending message to " + regId + ": " + error);
						Datastore.unregister(regId, devices_map.get(regId));
						
//						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
//							// application has been removed from device -
//							// unregister it
//							logger.info("Unregistered device: " + regId);
//							
//						} else {
//							logger.severe("Error sending message to " + regId + ": " + error);
//						}
					}
				}
			}
		});
	}
}
