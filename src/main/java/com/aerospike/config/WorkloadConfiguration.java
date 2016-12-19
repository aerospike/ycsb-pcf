package com.aerospike.config;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aerospike.controller.ClientController;
import com.aerospike.util.WorkloadConfig;

@Configuration
public class WorkloadConfiguration {
	private Map<String, WorkloadConfig> workloads = new LinkedHashMap<String, WorkloadConfig>();

	public WorkloadConfiguration() {
		try {
			InputStream in = getResourceAsStream("com/aerospike/properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String resource;

			while ((resource = br.readLine()) != null) {
				if (!resource.endsWith("template")) {
					final Properties properties = new Properties();
					WorkloadConfig workloadConfig = new WorkloadConfig();
					try {
						properties.load(ClientController.class.getClassLoader().getResourceAsStream("com/aerospike/properties/" + resource));
						workloadConfig.setFromProperties(properties);
						workloadConfig.setResourcename(resource);
					
					} catch (Exception e) {
						e.printStackTrace();

					}
					workloads.put(resource, workloadConfig);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);

		return in == null ? getClass().getResourceAsStream(resource) : in;
	}
	
	private ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	@Bean
	public Map<String, WorkloadConfig> getWorkloads() {
		return this.workloads;
	}
}
