package com.centime.concatenate.util;
/**
 * 
 * This class is the utility class for loggers.
 *  * 
 */
public class LoggerUtil {

	/**
	 * Instantiates a new logger util.
	 */
	private LoggerUtil() {

	}
	
	public static String getMethodName()
	{
		if(Thread.currentThread().getStackTrace().length >=2 )
			return Thread.currentThread().getStackTrace()[2].getMethodName();
		
		return ""; 
	}

	
}
