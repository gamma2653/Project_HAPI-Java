package com.gamsion.chris.utility.log;




/**
 * This module is used to
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class GamsionLogger {
	
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	public static final int SEVERE = 3;
	public static final int FATAL = 4;
	public static final String[] levelNames = {"DEBUG", "INFO", "ERROR", "SEVERE", "FATAL"};
	private int thisLevel;
	public GamsionLogger(int printLevel){
		thisLevel = printLevel;
	}
	
	public boolean changePickupDegree(int printLevel){
		if(thisLevel == printLevel){
			return false;
		}else{
			thisLevel = printLevel;
			return true;
		}
	}

	public boolean log(String moduleName, int degree, String message){
		if(degree>=thisLevel){
			System.out.printf("%s %s: %s\n", levelNames[degree], moduleName, message);
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args){
		GamsionLogger logger = new GamsionLogger(GamsionLogger.ERROR);
		logger.log("Gamsion Module", GamsionLogger.FATAL, "Your butt exploded.");
	}

}
