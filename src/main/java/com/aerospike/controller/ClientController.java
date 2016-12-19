package com.aerospike.controller;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aerospike.config.client.IServiceConfiguration;
import com.aerospike.util.WorkloadConfig;

@Controller
public class ClientController {

	@Autowired(required=false)
	Map<String, IServiceConfiguration> services;

	@Autowired
	Map<String, WorkloadConfig> workloads;

	private WorkloadConfig workloadConfig = null;
	private String serviceNameA;

	private List<String[]> results = null;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List<String> serviceNames = new ArrayList<String>();

		boolean bound = (services == null);

		if (services != null) {
			for (String serviceName : services.keySet()) {

				if (serviceNameA == null) {
					serviceNameA = serviceName;
				}
				serviceNames.add(serviceName);
				bound = true;
			}
		}
		if (workloadConfig == null) workloadConfig = workloads.get(workloads.keySet().toArray()[0]);

		model.addAttribute("services", serviceNames);
		model.addAttribute("bound", bound);
		model.addAttribute("serviceNameA", this.serviceNameA);
		model.addAttribute("isConfig", true);
		if (!model.containsAttribute("workloadConfig")) {
			model.addAttribute("workloadConfig", this.workloadConfig);
		}
		model.addAttribute("workloads", this.workloads.values());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String runBenchmark(@Valid WorkloadConfig newConfig, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@RequestParam(value="action", required=true) String action) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.workloadConfig", bindingResult);
			redirectAttributes.addFlashAttribute("workloadConfig", newConfig);
			return "redirect:/";
		}
		this.workloadConfig = newConfig; 
		this.workloads.put(newConfig.getResourcename(), newConfig);
		
		Properties properties = newConfig.toProperties();
		com.aerospike.util.Exporter.stringArrayList.clear();	
		if (services == null || services.size() == 0) {
			redirectAttributes.addFlashAttribute("error", "No services available!");	
			return "redirect:/results";
		}

		try {
			if (action.equals("load")) {
				/* LOAD DATA FOR SERVICE */
				String args = "-s,-load,-p,exporter=com.aerospike.util.Exporter";
				
				IServiceConfiguration csf = services.get(this.serviceNameA);
				args += csf.getPropertyString();
				
				com.aerospike.util.Exporter.stringArrayList.clear();
				com.yahoo.ycsb.Client.webMain(args.split(","), properties);
				results = new ArrayList<String[]>(com.aerospike.util.Exporter.stringArrayList);
			}
			else if (action.equals("run")) {
				/* RUN  SERVICE */		
				String args = "-t,-p,exporter=com.aerospike.util.Exporter";
				IServiceConfiguration csf = services.get(this.serviceNameA);
				args += csf.getPropertyString();

				com.aerospike.util.Exporter.stringArrayList.clear();
				com.yahoo.ycsb.Client.webMain(args.split(","), properties);
				results = new ArrayList<String[]>(com.aerospike.util.Exporter.stringArrayList);
			} else {
				redirectAttributes.addFlashAttribute("error", "Invalid action for run command");
				return "redirect:/";	
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/";			
		}
		
		return "redirect:/results";
	}

	@RequestMapping("/results") 
	public String setResults(Model model) {
		if ( services == null) {
			String[] testResults = { "TEST", "TEST", "TEST" };
			results = new ArrayList<String[]>();
			results.add(testResults);
			results.add(testResults);
		}

		model.addAttribute("serviceNameA", serviceNameA);
		model.addAttribute("hasResults", results != null);
		model.addAttribute("results", results);
		model.addAttribute("isConfig", false);
		model.addAttribute("properties", this.workloadConfig.toProperties());

		return "results";
	}

	@RequestMapping("/workload")
	public String setWorkload(@RequestParam("name") String workload, RedirectAttributes redirectAttributes) {
		this.workloadConfig = workloads.get(workload);
		redirectAttributes.addFlashAttribute("workloadConfig", this.workloadConfig);
		return "redirect:/";
	}
	
	@RequestMapping("/serviceA")
	public String setServiceA(@RequestParam("name") String service, RedirectAttributes redirectAttributes) {
		this.serviceNameA = service;
		return "redirect:/";
	}
}
