package com.centime.gateway.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.centime.gateway.annotation.LogMethodParam;
import com.centime.gateway.request.Request;
import com.centime.gateway.response.ColorDataResponse;
import com.centime.gateway.servicediscovery.ServiceDiscovery;
import com.centime.gateway.services.ColorService;
import com.centime.gateway.threadlocal.RequestIdThreadLocal;
import com.centime.gateway.util.AppLogger;
import com.centime.gateway.util.AppUtil;
import com.centime.gateway.util.LoggerUtil;

@RestController
public class GatewayController {
	@Autowired
	private ServiceDiscovery serviceDiscovery;
	
	@Autowired
	private ColorService colorService;
	
	private static final Logger LOG = LoggerFactory.getLogger(GatewayController.class);


	@GetMapping(path = "/health")
	public ResponseEntity<Object> health() {
		return ResponseEntity.ok("UP");
	}

	@PostMapping(value="/concatenate",produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> concatenate(@Valid @RequestBody Request request,HttpServletResponse response){
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		String response1 =  serviceDiscovery.helloService(HttpMethod.GET);
		String response2 = serviceDiscovery.concatenateService(HttpMethod.POST, AppUtil.getJson(request));
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		response.setHeader("x-requestId", RequestIdThreadLocal.getThreadLocal().get());

		return ResponseEntity.ok("{\""+response1 + " " + response2 + "\"}");
	}

	@LogMethodParam
	@GetMapping("/data/{id}")
	public ResponseEntity<ColorDataResponse> getColor(@PathVariable int id,HttpServletResponse response){
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		ColorDataResponse data = colorService.getColorData(id);
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		response.setHeader("x-requestId", RequestIdThreadLocal.getThreadLocal().get());

		return ResponseEntity.ok(data);
	}
	@GetMapping("/data")
	public ResponseEntity<List<ColorDataResponse>> getColors(HttpServletResponse response){
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		List<ColorDataResponse> list = colorService.getColorDatas();
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		response.setHeader("x-requestId", RequestIdThreadLocal.getThreadLocal().get());

		return ResponseEntity.ok(list);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
