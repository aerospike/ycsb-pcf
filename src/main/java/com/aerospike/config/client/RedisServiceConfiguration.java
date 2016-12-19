package com.aerospike.config.client;

/*
 * Copyright 2016 Aerospike, Inc.
 *
 * Portions may be licensed to Aerospike, Inc. under one or more contributor
 * license agreements WHICH ARE COMPATIBLE WITH THE APACHE LICENSE, VERSION 2.0.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Map;

public class RedisServiceConfiguration implements IServiceConfiguration {
	private final String hostname;
	private final Integer port;
	private final String password;
	
	public RedisServiceConfiguration(Map<String, Object> credentials) {
		this.hostname = (String)credentials.get("host");
		this.port = (Integer)credentials.get("port");
		this.password = (String)credentials.get("password");
	}

	public final String getPropertyString() {
		return ",-db,com.yahoo.ycsb.db.RedisClient" +
				",-p,redis.host=" + this.hostname + 
				",-p,redis.port=" + this.port + 
				",-p,redis.password=" + this.password;
	}
	
	@Override
	public String toString() {
		return "Host: " + this.hostname + ":" + this.port +
			   " Password: " + this.password;
	}
}

