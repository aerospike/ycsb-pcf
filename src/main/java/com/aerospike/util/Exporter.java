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

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.yahoo.ycsb.measurements.exporter.MeasurementsExporter;

public class Exporter implements MeasurementsExporter {
	
	public static List<String[]> stringArrayList = new ArrayList<String[]>();
	
	public Exporter(OutputStream os) {
		//stringArrayList.clear();
	}
	
	@Override
	public void close() throws IOException {
		
	}

	@Override
	public void write(String arg0, String arg1, int arg2) throws IOException {
		String[] strs = new String[3];
		strs[0] = arg0;
		strs[1] = arg1;
		strs[2] = arg2 + "";
		
		stringArrayList.add(strs);		
	}

	@Override
	public void write(String arg0, String arg1, double arg2) throws IOException {
		String[] strs = new String[3];
		strs[0] = arg0;
		strs[1] = arg1;
		strs[2] = arg2 + "";
		
		stringArrayList.add(strs);	
	}

}
