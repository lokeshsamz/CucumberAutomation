package Hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Log
{	
	private static Logger log = LogManager.getLogger();
	
	public static synchronized void startLogging()
	{
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		System.out.println("The current Class name : " + className);
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		System.out.println("The current Method name : " + methodName);		
		
		ThreadContext.put("LogFileName", methodName);
		//System.setProperty("LogFileName", methodName);
		
		Log.Info("=====================================Beginning of the Testcase=====================================");
	}

	public  static void Info(String message)
	{
		log.info(message);
	}

	public  static void Error(String message)
	{
		log.error(message);
	}
	
	public  static void Debug(String message)
	{
		log.debug(message);
	}
}
