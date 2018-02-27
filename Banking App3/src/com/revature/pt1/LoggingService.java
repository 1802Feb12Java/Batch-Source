package com.revature.pt1;

import org.apache.log4j.Logger;

public class LoggingService {
	
	private static LoggingService ls;// = new LoggingService();
	
	private static Logger log = Logger.getRootLogger();
	
	public void loggingMethod(){
		
		log.trace("Trace msg");
		log.debug("Debug msg");
		log.info("Info msg");
		log.warn("Warn msg");
		log.error("Error msg");
		log.fatal("Fatal msg");
		
	}
	
	public static synchronized LoggingService getInstance(){
		
		if (ls==null){
			ls = new LoggingService();
		}
		
		return ls;
		
	}
	
	public Logger getLogger(){
		
		return Logger.getRootLogger();
		
	}
	
	private LoggingService(){
		
		super();
		
	}

}
