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
package com.lofidewanto.demo.server.domain;

import java.io.InputStream;

/**
 * This Attachment class is used to map from Confluence attachment object.
 */
public class AttachmentImpl implements Attachment {

	private String id;
	private String title;
	private String downloadLink;
	private String mediaType;
	private String fileSize;
	private InputStream fileContent;
	private String version;
	private String comment;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDownloadLink() {
		return downloadLink;
	}

	@Override
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	@Override
	public String getMediaType() {
		return mediaType;
	}

	@Override
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String getFileSize() {
		return fileSize;
	}

	@Override
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public InputStream getFileContent() {
		return fileContent;
	}

	@Override
	public void setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}
}
