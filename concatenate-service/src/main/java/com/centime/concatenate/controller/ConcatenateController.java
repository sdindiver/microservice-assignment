package com.centime.concatenate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.concatenate.request.Request;
import com.centime.concatenate.util.AppLogger;
import com.centime.concatenate.util.LoggerUtil;

@RestController
public class ConcatenateController {

	private static final Logger LOG = LoggerFactory.getLogger(ConcatenateController.class);

	@PostMapping(value="/concatenate",produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> concatenate(@RequestBody Request request) {
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		return ResponseEntity.ok(request.getName() + " " + request.getSurName());
	}

}
