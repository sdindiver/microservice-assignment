package com.centime.hello.threadlocal;

public class RequestIdThreadLocal {

	public static ThreadLocal<String> requestId = new ThreadLocal<String>();

	public static ThreadLocal<String> getThreadLocal() {
		return requestId;
	}

}
