package com.gamsion.chris.utility.log;

public class LogFile {
	private final int degree;
	private final String module;
	private final String message;
	public LogFile(int degree, String module, String message){
		this.degree = degree;
		this.module = module;
		this.message = message;
	}
	
	public String readLog(){
		return GamsionLogger.levelNames[degree] + " " + module + " " + message;
	}

}
