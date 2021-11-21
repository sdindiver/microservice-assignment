package com.centime.gateway.servicediscovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.centime.gateway.services.ColorServiceImpl;
import com.centime.gateway.threadlocal.RequestIdThreadLocal;
import com.centime.gateway.util.AppLogger;
import com.centime.gateway.util.LoggerUtil;

@Component
public class ServiceDiscovery {
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger LOG = LoggerFactory.getLogger(ColorServiceImpl.class);

	public String helloService(HttpMethod method) {
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		HttpHeaders headers = new HttpHeaders();
		headers.set("requestId", RequestIdThreadLocal.getThreadLocal().get());
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		String response= restTemplate.exchange("http://hello-service/hello", HttpMethod.GET, entity, String.class)
				.getBody();
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		return response;

	}
	
	public String concatenateService(HttpMethod method,String body){
		HttpHeaders headers = new HttpHeaders();
		headers.set("requestId", RequestIdThreadLocal.getThreadLocal().get());
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		String response =  restTemplate
		.exchange("http://concatenate-service/concatenate", method, entity, String.class)
		.getBody();
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		return response;
		
	}
}
