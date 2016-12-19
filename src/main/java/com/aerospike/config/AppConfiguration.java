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

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aerospike.config.client.IServiceConfiguration;

import io.pivotal.labs.cfenv.CloudFoundryEnvironment;
import io.pivotal.labs.cfenv.CloudFoundryEnvironmentException;
import io.pivotal.labs.cfenv.CloudFoundryService;

@Configuration
@Profile("cloud")
public class AppConfiguration extends AbstractCloudConfig {

	// Update this map with the label from your PCF service and the class name of your YCSB service configuration
	private static final Map<String, String> labelToConfigurationClass = new HashMap<String, String>();
	static {
		labelToConfigurationClass.put("aerospike-on-demand", "com.aerospike.config.client.AerospikeServiceConfiguration");
		labelToConfigurationClass.put("aerospike",           "com.aerospike.config.client.AerospikeServiceConfiguration");
		labelToConfigurationClass.put("p-redis",             "com.aerospike.config.client.RedisServiceConfiguration");
	}

	private Map<String, IServiceConfiguration> services = new HashMap<String, IServiceConfiguration>();

	public AppConfiguration() throws CloudFoundryEnvironmentException {  
		CloudFoundryEnvironment environment = new CloudFoundryEnvironment(System::getenv);

		if (environment.getServiceNames().size() > 0) {
			for (String serviceName : environment.getServiceNames()) {
				System.out.println("SERVICE NAME: " + serviceName);
				CloudFoundryService service = environment.getService(serviceName);
				if (labelToConfigurationClass.containsKey(service.getLabel())) {
					String className = labelToConfigurationClass.get(service.getLabel());
					try {
						Class<?> clazz = Class.forName(className);
						Constructor<?> constructor = clazz.getConstructor(Map.class);
						IServiceConfiguration config = (IServiceConfiguration) constructor.newInstance(environment.getService(serviceName).getCredentials());
						services.put(serviceName, config);
						System.out.println("ADDED CLASS : " + className);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error creating class " + className);
					}
				}
			}
		}
	}
	
	@Bean
	public Map<String, IServiceConfiguration> getServices() {
		return this.services;
	}
	
}
