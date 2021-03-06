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
package com.lofidewanto.demo.server.service.content;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lofidewanto.demo.server.domain.Attachment;
import com.lofidewanto.demo.server.domain.AttachmentImpl;
import com.lofidewanto.demo.server.domain.AllAttachments;
import com.lofidewanto.demo.shared.DemoGwtServiceEndpoint;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class ConfluenceContentServiceImpl implements ConfluenceContentService {

	private static final Logger logger = LoggerFactory
			.getLogger(ConfluenceContentServiceImpl.class);

	private static final String ATTACHMENT_PAGE_ID_PARAM = "{pageId}";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MapAllAttachments mapAllAttachments;

	@Value("${confluence.url}")
	private String confluenceUrl;

	@Value("${confluence.pageid}")
	private String confluencePageId;

	@Override
	public List<Attachment> getAllAttachments() {
		// Connect to Confluence
		String confluenceAttachmentList = DemoGwtServiceEndpoint.CONFLUENCE_ATTACHMENT_LIST;
		String url = confluenceUrl.concat(confluenceAttachmentList);
		URI uri = replacePageId(url);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		logger.info("URI: " + uri.toString());

		ResponseEntity<AllAttachments> allAttachmentWithResponseEntiry =
				restTemplate.exchange(uri, HttpMethod.GET, entity, AllAttachments.class);

		AllAttachments allAttachments = allAttachmentWithResponseEntiry.getBody();

		List<Attachment> attachments = mapAllAttachments.mapAllAttachments2List(allAttachments);

		return attachments;
	}

	URI replacePageId(String url) {
		// Replace {pageId} with confluencePageId
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.buildAndExpand(confluencePageId).encode().toUri();

		return uri;
	}

	@Override
	public List<Attachment> getAttachmentsByPageId(String pageId) {
		throw new NotImplementedException();
	}

	@Override
	public Attachment getAttachmentById(String id) {
		throw new NotImplementedException();
	}

	@Override
	public Attachment getAttachmentByDownloadLink(String downloadLink) {
		// Get InputStream from Confluence
		String url = confluenceUrl.concat(downloadLink);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.ALL_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		// Tips: https://stackoverflow.com/questions/36379835/getting-inputstream-with-resttemplate
		ResponseEntity<Resource> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				entity, Resource.class);

		InputStream responseInputStream;
		int fileSize = 0;
		try {
			responseInputStream = responseEntity.getBody().getInputStream();
			fileSize = responseInputStream.available();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		Attachment attachment = new AttachmentImpl();
		attachment.setFileContent(responseInputStream);
		attachment.setFileSize(String.valueOf(fileSize));

		return attachment;
	}

	URI buildDownloadUri(String url) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		return builder.build().toUri();
	}

}
