package com.centime.concatenate.filter;

import java.net.InetAddress;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.centime.concatenate.threadlocal.RequestIdThreadLocal;

@Component
public class RequestIdFilter extends OncePerRequestFilter {

	private String responseHeader;
	private String requestHeader = "requestId";;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws java.io.IOException, ServletException {
		try {
			String token;
			if (StringUtils.hasLength(requestHeader) && StringUtils.hasLength(request.getHeader(requestHeader))) {
				token = request.getHeader(requestHeader);
			} else {
				token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
			}
			String hostName = InetAddress.getLocalHost().getHostName();
			if (hostName != null) {
				token = hostName + "_" + token;
			}
			RequestIdThreadLocal.getThreadLocal().set(token);
			if (StringUtils.hasLength(responseHeader)) {
				response.addHeader(responseHeader, token);
			}
			chain.doFilter(request, response);
		} finally {
			response.setHeader("x-requestId", RequestIdThreadLocal.getThreadLocal().get());
			RequestIdThreadLocal.getThreadLocal().remove();
		}
	}
}
