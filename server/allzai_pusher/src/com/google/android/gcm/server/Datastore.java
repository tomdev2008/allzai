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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Simple implementation of a data store using standard Java collections.
 * <p>
 * This class is thread-safe but not persistent (it will lost the data when the
 * app is restarted) - it is meant just as an example.
 */
public final class Datastore {

	private static final Map<Integer, String> regIds = new HashMap<Integer, String>();
	private static final Logger logger = Logger.getLogger(Datastore.class.getName());

	private Datastore() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Registers a device.
	 */
	public static void register(int userId, String regId) {
		logger.info("Registering " + regId + " by user " + userId);
		synchronized (regIds) {
			regIds.put(userId, regId);
		}
	}

	/**
	 * Unregisters a device.
	 */
	public static void unregister(String regId, int userId) {
		logger.info("Unregistering " + regId + " by user " + userId);
		synchronized (regIds) {
			regIds.remove(userId);
		}
	}

	/**
	 * Updates the registration id of a device.
	 */
	public static void updateRegistration(String oldId, String newId, int userId) {
		logger.info("Updating " + oldId + " to " + newId + " by user " + userId);
		synchronized (regIds) {
			regIds.put(userId, newId);
		}
	}

	/**
	 * Gets all registered devices.
	 */
	public static Map<Integer, String> getDevices() {
		synchronized (regIds) {
			return new HashMap<Integer, String>(regIds);
		}
	}

}
