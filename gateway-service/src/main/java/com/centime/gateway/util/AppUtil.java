package com.centime.gateway.util;

import com.centime.gateway.request.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class AppUtil {

	public static String getJson(Request data) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(data);
			return json;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
