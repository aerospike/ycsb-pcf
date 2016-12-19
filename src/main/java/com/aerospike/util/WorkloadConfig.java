package com.aerospike.util;

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

import java.math.BigDecimal;
import java.util.Properties;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WorkloadConfig {
	public enum RequestDist {
		uniform, zipfian, hotspot
	}
	
	public enum InsertOrder {
		hashed, ordered
	}
	
	public enum ScanLengthDist {
		uniform, zipfian
	}
	
	private String name                          = "Generic Workload";
	private String resourcename                  = "workloada";
	
	@NotNull
	@Min(1)
	private Integer fieldcount                   = 10;
	
	@NotNull
	@Min(1)
	private Integer fieldlength                  = 100;
	
	private Boolean readallfields                = true;
	private Boolean writeallfields               = true;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal readproportion            = new BigDecimal(0.95);
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal updateproportion          = new BigDecimal(0.95);
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal insertproportion          = new BigDecimal(0.0);
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal scanproportion            = new BigDecimal(0.0);
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal readmodifywriteproportion = new BigDecimal(0.0);
	
	private RequestDist requestdistribution      = RequestDist.uniform;
	
	private Integer maxscanlength                = 1000;
	private ScanLengthDist scanlenthdistribution = ScanLengthDist.uniform;
	private InsertOrder insertorder              = InsertOrder.hashed;
	private Integer operationcount               = 100000;
	private Integer maxexecutiontime             = 600;
	
	@NotNull
	@Min(1)
	private Integer recordcount                  = 100000;
	
	@NotNull
	@Min(1)
	private Integer threadcount                  = 1;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal hotspotdatafraction		 = new BigDecimal(0.0);
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1.0", inclusive = true)
	private BigDecimal hotspotopnfraction		 = new BigDecimal(0.0);
	
	private String workload                      = "com.yahoo.ycsb.workloads.CoreWorkload";
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final Integer getFieldcount() {
		return fieldcount;
	}
	public final void setFieldcount(Integer fieldcount) {
		this.fieldcount = fieldcount;
	}
	public final Integer getFieldlength() {
		return fieldlength;
	}
	public final void setFieldlength(Integer fieldlength) {
		this.fieldlength = fieldlength;
	}
	public final Boolean getReadallfields() {
		return readallfields;
	}
	public final void setReadallfields(Boolean readallfields) {
		this.readallfields = readallfields;
	}
	public final BigDecimal getReadproportion() {
		return readproportion;
	}
	public final void setReadproportion(BigDecimal readproportion) {
		this.readproportion = readproportion;
	}
	public final BigDecimal getUpdateproportion() {
		return updateproportion;
	}
	public final void setUpdateproportion(BigDecimal updateproportion) {
		this.updateproportion = updateproportion;
	}
	public final BigDecimal getInsertproportion() {
		return insertproportion;
	}
	public final void setInsertproportion(BigDecimal insertproportion) {
		this.insertproportion = insertproportion;
	}
	public final BigDecimal getScanproportion() {
		return scanproportion;
	}
	public final void setScanproportion(BigDecimal scanproportion) {
		this.scanproportion = scanproportion;
	}
	public final BigDecimal getReadmodifywriteproportion() {
		return readmodifywriteproportion;
	}
	public final void setReadmodifywriteproportion(BigDecimal readmodifywriteproportion) {
		this.readmodifywriteproportion = readmodifywriteproportion;
	}
	public final RequestDist getRequestdistribution() {
		return requestdistribution;
	}
	public final void setRequestdistribution(RequestDist requestdistribution) {
		this.requestdistribution = requestdistribution;
	}
	public final Integer getMaxscanlength() {
		return maxscanlength;
	}
	public final void setMaxscanlength(Integer maxscanlength) {
		this.maxscanlength = maxscanlength;
	}
	public final ScanLengthDist getScanlenthdistribution() {
		return scanlenthdistribution;
	}
	public final void setScanlenthdistribution(ScanLengthDist scanlenthdistribution) {
		this.scanlenthdistribution = scanlenthdistribution;
	}
	public final InsertOrder getInsertorder() {
		return insertorder;
	}
	public final void setInsertorder(InsertOrder insertorder) {
		this.insertorder = insertorder;
	}
	public final Integer getOperationcount() {
		return operationcount;
	}
	public final void setOperationcount(Integer operationcount) {
		this.operationcount = operationcount;
	}
	public final Integer getMaxexecutiontime() {
		return maxexecutiontime;
	}
	public final void setMaxexecutiontime(Integer maxexecutiontime) {
		this.maxexecutiontime = maxexecutiontime;
	}
	public final Integer getRecordcount() {
		return recordcount;
	}
	public final void setRecordcount(Integer recordcount) {
		this.recordcount = recordcount;
	}
	public Integer getThreadcount() {
		return threadcount;
	}
	public void setThreadcount(Integer threadcount) {
		this.threadcount = threadcount;
	}
	public String getWorkload() {
		return workload;
	}
	public void setWorkload(String workload) {
		this.workload = workload;
	}
	public Boolean getWriteallfields() {
		return writeallfields;
	}
	public void setWriteallfields(Boolean writeallfields) {
		this.writeallfields = writeallfields;
	}
	public final String getResourcename() {
		return resourcename;
	}
	public final void setResourcename(String filename) {
		this.resourcename = filename;
	}
	public BigDecimal getHotspotdatafraction() {
		return hotspotdatafraction;
	}
	public void setHotspotdatafraction(BigDecimal hotspotdatafraction) {
		this.hotspotdatafraction = hotspotdatafraction;
	}
	public BigDecimal getHotspotopnfraction() {
		return hotspotopnfraction;
	}
	public void setHotspotopnfraction(BigDecimal hotspotopnfraction) {
		this.hotspotopnfraction = hotspotopnfraction;
	}
	public void setFromProperties(Properties props) {
//		Field[] fields = WorkloadConfig.class.getDeclaredFields();
		
		for (String name : props.stringPropertyNames()) {
			switch (name) {
				case "name": this.setName(props.getProperty(name)); break;
				case "resourcename": this.setResourcename(props.getProperty(name)); break;
				case "fieldcount": this.setFieldcount(Integer.parseInt(props.getProperty(name))); break;
				case "fieldlength": this.setFieldlength(Integer.parseInt(props.getProperty(name))); break;
				case "readallfields": this.setReadallfields(Boolean.valueOf(props.getProperty(name))); break;
				case "writeallfields": this.setWriteallfields(Boolean.valueOf(props.getProperty(name))); break;
				case "readproportion": this.setReadproportion(new BigDecimal(props.getProperty(name))); break;
				case "updateproportion": this.setUpdateproportion(new BigDecimal(props.getProperty(name))); break;
				case "insertproportion": this.setInsertproportion(new BigDecimal(props.getProperty(name))); break;
				case "scanproportion": this.setScanproportion(new BigDecimal(props.getProperty(name))); break;
				case "readmodifywriteproportion": this.setReadmodifywriteproportion(new BigDecimal(props.getProperty(name))); break;
				case "requestdistribution": this.setRequestdistribution(RequestDist.valueOf(props.getProperty(name))); break;
				case "maxscanlength": this.setMaxscanlength(Integer.parseInt(props.getProperty(name))); break;
				case "scanlenthdistribution": this.setScanlenthdistribution(ScanLengthDist.valueOf(props.getProperty(name))); break;
				case "insertorder": this.setInsertorder(InsertOrder.valueOf(props.getProperty(name))); break;
				case "operationcount": this.setOperationcount(Integer.parseInt(props.getProperty(name))); break;
				case "maxexecutiontime": this.setMaxexecutiontime(Integer.parseInt(props.getProperty(name))); break;
				case "recordcount": this.setRecordcount(Integer.parseInt(props.getProperty(name))); break;
				case "threadcount": this.setThreadcount(Integer.parseInt(props.getProperty(name))); break;
				case "workload": this.setWorkload(props.getProperty(name)); break;	
				case "hotspotdatafraction": this.setHotspotdatafraction(new BigDecimal(props.getProperty(name))); break;	
				case "hotspotopnfraction": this.setHotspotopnfraction(new BigDecimal(props.getProperty(name))); break;	
				
				default: System.out.println("Invalid property name: " + name);	
			}
		}
	}
	
	public Properties toProperties() {
		Properties props = new Properties();
		props.put("name", this.getName());
		props.put("resourcename", this.getResourcename());
		props.put("fieldcount", this.getFieldcount().toString());
		props.put("fieldlength", this.getFieldlength().toString());
		props.put("readallfields", this.getReadallfields().toString());
		props.put("writeallfields", this.getWriteallfields().toString());
		props.put("updateproportion", this.getUpdateproportion().toString());
		props.put("readproportion", this.getReadproportion().toString());
		props.put("scanproportion", this.getScanproportion().toString());
		props.put("insertproportion", this.getInsertproportion().toString());
		props.put("readmodifywriteproportion", this.getReadmodifywriteproportion().toString());
		props.put("requestdistribution", this.getRequestdistribution().toString());
		props.put("maxscanlength", this.getMaxscanlength().toString());
		props.put("scanlenthdistribution", this.getScanlenthdistribution().toString());
		props.put("insertorder", this.getInsertorder().toString());
		props.put("operationcount", this.getOperationcount().toString());
		props.put("maxexecutiontime", this.getMaxexecutiontime().toString());
		props.put("recordcount", this.getRecordcount().toString());
		props.put("threadcount", this.getThreadcount().toString());
		props.put("workload", this.getWorkload());
		props.put("hotspotdatafraction", this.getHotspotdatafraction().setScale(0,BigDecimal.ROUND_HALF_UP).toPlainString());
		props.put("hotspotopnfraction", this.getHotspotopnfraction().toPlainString());
		
		return props;
	}
}
