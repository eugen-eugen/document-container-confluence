/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.lofidewanto.demo.client.common;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.lofidewanto.demo.client.domain.RestConfluenceContentClient;

@Singleton
public class RestServicePreparator implements ServicePreparator {

	private static Logger logger = Logger
			.getLogger(RestServicePreparator.class.getName());

	@Inject
	private RestConfluenceContentClient restConfluenceContentClient;

	private String baseUrl;


	public RestServicePreparator() {
    }

    @Override
    public void prepare(String baseUrl) {
        logger.info("Prepare for the resources for the services...");

        this.baseUrl = baseUrl;

        initServices(baseUrl);
    }

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	private void initServices(String baseUrl) {
		Defaults.setDateFormat(null);

		logger.info("Init the domains...");

		Resource resource = new Resource(baseUrl);

		((RestServiceProxy) restConfluenceContentClient).setResource(resource);
	}

}
