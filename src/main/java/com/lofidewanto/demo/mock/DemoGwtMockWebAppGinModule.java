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
package com.lofidewanto.demo.mock;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.lofidewanto.demo.client.common.ServicePreparator;
import com.lofidewanto.demo.client.domain.ConfluenceContentClient;
import com.lofidewanto.demo.mock.common.MockServicePreparator;
import com.lofidewanto.demo.mock.domain.MockConfluenceContentClient;

public class DemoGwtMockWebAppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		// Bind the SimpleEventBus as Singleton
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

        // Bind ServicePreparator
        bind(ServicePreparator.class).to(MockServicePreparator.class).in(Singleton.class);

        // Using the mocks
		bind(ConfluenceContentClient.class).to(MockConfluenceContentClient.class).in(Singleton.class);
	}
}
