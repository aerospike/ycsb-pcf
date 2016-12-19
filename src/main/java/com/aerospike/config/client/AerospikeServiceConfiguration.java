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

public class AerospikeServiceConfiguration implements IServiceConfiguration {
	private final String hostname;
	private final Integer port;
	private final String user;
	private final String password;
	private final String namespace;
	
	public AerospikeServiceConfiguration(Map<String, Object> credentials) {
		this.hostname = (String)credentials.get("hostname");
		this.port = (Integer)credentials.get("port");
		this.user = (String)credentials.get("user");
		this.password = (String)credentials.get("password");
		this.namespace = (String)credentials.get("namespace");
	}
	
	public final String getPropertyString() {
		return ",-db,com.yahoo.ycsb.db.AerospikeClient" +
				",-p,as.host=" + this.hostname + 
				",-p,as.port=" + this.port + 
				",-p,as.user=" + this.user + 
				",-p,as.namespace=" + this.namespace + 
				",-p,as.password=" + this.password;
	}
	
	@Override
	public String toString() {
		return "Host: " + this.hostname + ":" + this.port +
			   " Namespace: " + this.namespace +
			   " User: " + this.user +
			   " Password: " + this.password;
	}
}
