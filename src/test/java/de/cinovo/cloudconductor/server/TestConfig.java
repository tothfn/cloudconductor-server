package de.cinovo.cloudconductor.server;

/*
 * #%L
 * cloudconductor-server
 * %%
 * Copyright (C) 2013 - 2014 Cinovo AG
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * #L%
 */

import de.taimos.springcxfdaemon.SpringDaemonTestRunner.RunnerConfig;

/**
 * 
 * Copyright 2013 Cinovo AG<br>
 * <br>
 * 
 * @author hoegertn
 * 
 */
public class TestConfig extends RunnerConfig {
	
	/**
	 * 
	 */
	public TestConfig() {
		this.addProperty("ds.type", "HSQL");
		this.addProperty("ds.demodata", "true");
		this.addProperty("ds.demofile", "liquibase/cf.sql");
		this.addProperty("svc.port", String.valueOf(RunnerConfig.randomPort()));
		this.addProperty("cloudconductor.username", "admin");
		this.addProperty("cloudconductor.password", "password");
		this.addProperty("cloudconductor.url", "localhost:8098");
		this.addProperty("hazelcast.members", "localhost");
	}
	
	@Override
	public String getSpringFile() {
		return "spring/beans.xml";
	}
	
	@Override
	public String getServicePackage() {
		return "de.cinovo.cloudconductor.server";
	}
	
}
