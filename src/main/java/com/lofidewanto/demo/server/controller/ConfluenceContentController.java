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
package com.lofidewanto.demo.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lofidewanto.demo.server.domain.Attachment;
import com.lofidewanto.demo.server.service.content.ConfluenceContentService;
import com.lofidewanto.demo.shared.AttachmentDto;
import com.lofidewanto.demo.shared.DemoGwtServiceEndpoint;

@Controller
@CrossOrigin
public class ConfluenceContentController {

    private static final Logger logger = LoggerFactory
            .getLogger(ConfluenceContentController.class);

    @Autowired
    private ConfluenceContentService confluenceContentService;

    /**
     * Get all attachments from Confluence.
     *
     * @return
     */
    @RequestMapping(value = DemoGwtServiceEndpoint.ATTACHMENT_LIST, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<AttachmentDto>> getAllAttachments() {

        final List<Attachment> allAttachments = confluenceContentService
                .getAllAttachments();

        ArrayList<AttachmentDto> attachmentDtos = new ArrayList<>();

        // Copy to DTO
        for (Attachment attachment : allAttachments) {
            AttachmentDto attachmentDto = buildAttachmentDto(attachment);
            attachmentDtos.add(attachmentDto);
        }

        final ResponseEntity<List<AttachmentDto>> listResponseEntity = new ResponseEntity<>(
                attachmentDtos, HttpStatus.OK);

        return listResponseEntity;
    }

    private AttachmentDto buildAttachmentDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setDownloadLink(attachment.getDownloadLink());
        attachmentDto.setFileSize(attachment.getFileSize());
        attachmentDto.setId(attachment.getId());
        attachmentDto.setMediaType(attachment.getMediaType());
        attachmentDto.setTitle(attachment.getTitle());

        return attachmentDto;
    }

}
