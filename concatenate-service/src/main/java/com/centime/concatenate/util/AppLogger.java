package com.centime.concatenate.util;

import org.slf4j.Logger;

import com.centime.concatenate.threadlocal.RequestIdThreadLocal;

/**
 * The Class CSPLogger.
 * 
 * 
 */
public class AppLogger {

	/** The Constant SNMP_MESSAGE_VALUE_HOLDER_REGEX. */
	private static final String LOGGER_MESSAGE_REGEX = "\\{\\}";

	/**
	 * Instantiates a new CSP logger.
	 */
	private AppLogger() {

	}

	/**
	 * <p>
	 * Provides a standard process for logging debug messages. Debug messages
	 * should be used to display variable values and additional data throughout
	 * execution of methods to aid in debugging.
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param message
	 *            The message to be logged
	 */
	public static void debug(final Logger log, final Class<?> className, final String methodName, final String message) {
		validateLogger(log, className);
		log.debug("{} {} | {} | {}", new Object[] { className.getName(), methodName, message });

	}

	private static void validateLogger(final Logger logger, final Class<?> className) {
		if (logger == null || className == null) {
			throw new IllegalArgumentException("Logger or className can't be null");
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging info messages. Info messages
	 * should be used to indicate important milestones within program execution
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param message
	 *            The message to be logged
	 */
	public static void info(final Logger log, final Class<?> className, final String methodName, final String message) {
		validateLogger(log, className);
		if (log.isInfoEnabled()) {
			log.info("{} {} | {}| {}", new Object[] { className.getName(), methodName, RequestIdThreadLocal.getThreadLocal().get(),message });
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging warning messages. Warning
	 * messages should be used for non-critical warnings that do not constitute
	 * an error message
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param message
	 *            The message to be logged
	 */
	public static void warn(final Logger log, final Class<?> className, final String methodName, final String message) {
		validateLogger(log, className);
		if (log.isWarnEnabled()) {
			log.warn("{} {} | {}", new Object[] { className.getName(), methodName, message });
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging error messages. Error messages
	 * should be used when an operation fails. All <code>Exceptions</code> are
	 * logged automatically, so errors should only be used where there is
	 * additional information to provide additional clarity as to why the
	 * exception was thrown
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param message
	 *            The message to be logged
	 */
	public static void error(final Logger log, final Class<?> className, final String methodName, final String message) {
		validateLogger(log, className);
		if (log.isErrorEnabled()) {
			log.error("{} {} | {} | {}", new Object[] { className.getName(), methodName,RequestIdThreadLocal.getThreadLocal().get(), message });
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging error messages. Error messages
	 * should be used when an operation fails. All <code>Exceptions</code> are
	 * logged automatically, so errors should only be used where there is
	 * additional information to provide additional clarity as to why the
	 * exception was thrown
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param message
	 *            The message to be logged
	 * @param throwable
	 *            the throwable
	 */
	public static void error(final Logger log, final Class<?> className, final String methodName, final String message,
			final Throwable throwable) {
		validateLogger(log, className);
		if (log.isErrorEnabled()) {
			final StringBuffer errorMethodLog = new StringBuffer();
			errorMethodLog.append(className.getName());
			errorMethodLog.append(" ");
			errorMethodLog.append(methodName);
			errorMethodLog.append("|"+RequestIdThreadLocal.getThreadLocal().get()).append("|");
			errorMethodLog.append(message);
			log.error(errorMethodLog.toString(), throwable);
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging error messages. Error messages
	 * should be used when an operation fails. All <code>Exceptions</code> are
	 * logged automatically, so errors should only be used where there is
	 * additional information to provide additional clarity as to why the
	 * exception was thrown
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param errorCode
	 *            The error code corresponding to the error
	 * @param extraMessage
	 *            Message passed with error code to be logged
	 */

	public static void error(final Logger log, final Class<?> className, final String methodName, final int errorCode,
			final String extraMessage) {
		validateLogger(log, className);
		if (log.isErrorEnabled()) {
			final StringBuffer errorMethodLog = new StringBuffer(25);
			errorMethodLog.append(className.getName());
			errorMethodLog.append(" ");
			errorMethodLog.append(methodName);
			errorMethodLog.append("|"+RequestIdThreadLocal.getThreadLocal().get()).append("|");
			errorMethodLog.append(errorCode);
			errorMethodLog.append("|");
			errorMethodLog.append(extraMessage);
			log.error(errorMethodLog.toString());
		}
	}

	/**
	 * <p>
	 * Provides a standard process for logging error messages. Error messages
	 * should be used when an operation fails. All <code>Exceptions</code> are
	 * logged automatically, so errors should only be used where there is
	 * additional information to provide additional clarity as to why the
	 * exception was thrown
	 * </p>
	 * 
	 * @param log
	 *            The configuration of the current log to be used to write the
	 *            message to
	 * @param className
	 *            the class name
	 * @param methodName
	 *            The name of the method the message originated from
	 * @param errorCode
	 *            The error code corresponding to the error
	 * @param extraMessage
	 *            The message to be logged
	 * @param throwable
	 *            the throwable
	 */
	public static void error(final Logger log, final Class<?> className, final String methodName, final int errorCode,
			final String extraMessage, final Throwable throwable) {
		validateLogger(log, className);
		if (log.isErrorEnabled()) {
			final StringBuffer errorMethodLog = new StringBuffer(25);
			errorMethodLog.append(className.getName());
			errorMethodLog.append(" ");
			errorMethodLog.append(methodName);
			errorMethodLog.append("|"+RequestIdThreadLocal.getThreadLocal().get()).append("|");
			errorMethodLog.append(errorCode);
			errorMethodLog.append("|");
			errorMethodLog.append(extraMessage);
			log.error(errorMethodLog.toString(), throwable);
		}
	}

	/**
	 * Debug.
	 * 
	 * @param log
	 *            the log
	 * @param clazz
	 *            the clazz
	 * @param methodName
	 *            the method name
	 * @param message
	 *            the message
	 * @param parameters
	 *            the parameters
	 */
	public static void debug(final Logger log, final Class<?> clazz, final String methodName, final String message,
			final Object[] parameters) {
		validateLogger(log, clazz);
		if (log.isDebugEnabled()) {
			log.debug("{} {} | {} | {}", new Object[] { clazz.getName(), methodName, RequestIdThreadLocal.getThreadLocal().get(),getMessage(message, parameters) });
		}
	}

	/**
	 * Info.
	 * 
	 * @param log
	 *            the log
	 * @param clazz
	 *            the clazz
	 * @param methodName
	 *            the method name
	 * @param message
	 *            the message
	 * @param parameters
	 *            the parameters
	 */
	public static void info(final Logger log, final Class<?> clazz, final String methodName, final String message,
			final Object[] parameters) {
		validateLogger(log, clazz);
		if (log.isInfoEnabled()) {
			log.info("{} {} | {} | {}", new Object[] { clazz.getName(), methodName,RequestIdThreadLocal.getThreadLocal().get(), getMessage(message, parameters) });
		}
	}

	/**
	 * Error.
	 * 
	 * @param log
	 *            the log
	 * @param clazz
	 *            the clazz
	 * @param methodName
	 *            the method name
	 * @param message
	 *            the message
	 * @param parameters
	 *            the parameters
	 */
	public static void error(final Logger log, final Class<?> clazz, final String methodName, final String message,
			final Object[] parameters) {
		validateLogger(log, clazz);
		log.info("{} {} | {} | {}", new Object[] { clazz.getName(), methodName, RequestIdThreadLocal.getThreadLocal().get(), getMessage(message, parameters) });
	}

	/**
	 * Gets the message.
	 * 
	 * @param message
	 *            the message
	 * @param parameters
	 *            the parameters
	 * @return the message
	 */
	private static Object getMessage(final String message, final Object[] parameters) {
		String msg = message;
		if (parameters != null && parameters.length > 0) {
			for (final Object parameter : parameters) {
				if(parameter != null)
					msg = msg.replaceFirst(LOGGER_MESSAGE_REGEX, parameter.toString());
			}
		}
		return msg;
	}
}
