package com.centime.gateway.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.centime.gateway.services.ColorServiceImpl;
import com.centime.gateway.util.AppLogger;
import com.centime.gateway.util.LoggerUtil;

@Aspect
@Component
public class LogMethodParamAspect {
	private static final Logger LOG = LoggerFactory.getLogger(LogMethodParamAspect.class);

	@Before("@annotation(com.centime.gateway.annotation.LogMethodParam)")
	public void log(JoinPoint pjp) throws Throwable {
		Object[] signatureArgs = pjp.getArgs();
		for (Object signatureArg : signatureArgs) {
			AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(),
					"Method Name:" + pjp.getSignature().getName() + " AND " + "Arg: " + signatureArg);
		}

	}
}
