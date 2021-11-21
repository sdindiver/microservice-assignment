package com.centime.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.centime.hello.util.AppLogger;
import com.centime.hello.util.LoggerUtil;


@org.springframework.web.bind.annotation.RestController
public class HelloController 
{
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping(path="/hello", method=RequestMethod.GET)
	public ResponseEntity<Object> hello(){
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
        return ResponseEntity.ok("Hello");

	}
	

 
   
}
