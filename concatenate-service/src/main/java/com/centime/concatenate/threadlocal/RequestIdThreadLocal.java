package com.centime.concatenate.threadlocal;

public class RequestIdThreadLocal {

	public static ThreadLocal<String> requestId = new ThreadLocal<String>();

	public static ThreadLocal<String> getThreadLocal() {
		return requestId;
	}

}
