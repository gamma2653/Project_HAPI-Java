package com.gamsion.chris.utility.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This module is used to
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class GamsionLogger {
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	public static final int SEVERE = 3;
	public static final int FATAL = 4;
	public static final String[] levelNames = { "DEBUG", "INFO", "ERROR",
			"SEVERE", "FATAL" };
	private int thisLevel;
	private String save_location;
	private StringBuilder fullLog = new StringBuilder();

	public GamsionLogger(int printLevel, String save_location) {
		this.save_location = save_location;
		thisLevel = printLevel;
	}

	public boolean changePickupDegree(int printLevel) {
		if (thisLevel == printLevel) {
			return false;
		} else {
			thisLevel = printLevel;
			return true;
		}
	}


	public boolean log(LogFile log) {
		String str = log.read();
		fullLog.append(str + System.lineSeparator());
		
		if (log.getLevel() >= thisLevel) {
			System.out.println(str);
			return true;
		} else {
			return false;
		}
	}

	public boolean saveLog() {
		final DateFormat fileDateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
		Date date = new Date();
		File f = new File(save_location + fileDateFormat.format(date) + ".log");
		System.out.println(f.getAbsolutePath());
		try {
			if(!f.exists()){
				new File(save_location).mkdir();
				if(!f.createNewFile())return false;
				
				
			}
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fullLog.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public StringBuilder getFullLog() {
		return fullLog;
	}

	public static void main(String[] args) {
		GamsionLogger logger = new GamsionLogger(GamsionLogger.ERROR, "C:\\Users\\John\\logs\\");
		logger.log(new LogFile(GamsionLogger.FATAL, "Gamsion Module",
				"Your butt exploded."));
		logger.log(new LogFile(GamsionLogger.FATAL, "Gamsion Module",
				"Your butt exploded. AGAIN."));
		logger.saveLog();
	}

}
