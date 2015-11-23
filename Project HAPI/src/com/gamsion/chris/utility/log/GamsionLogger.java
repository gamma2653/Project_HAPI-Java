package com.gamsion.chris.utility.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gamsion.chris.utility.GamsionModule;

/**
 * This module is used to
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class GamsionLogger implements GamsionModule {
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	public static final int SEVERE = 3;
	public static final int FATAL = 4;
	public static final String[] levelNames = { "DEBUG", "INFO", "ERROR",
			"SEVERE", "FATAL" };
	private int pickupLevel;
	private String save_location;
	private StringBuilder fullLog = new StringBuilder();

	public GamsionLogger(int printLevel, String save_location) {
		this.save_location = save_location;
		pickupLevel = printLevel;

	}

	public boolean changePickupDegree(int printLevel) {
		if (pickupLevel == printLevel) {
			return false;
		} else {
			pickupLevel = printLevel;
			return true;
		}
	}

	public void log(LogFile logFile) {
		if (logFile.getModule() == null || !logFile.getOverrideLogName()) {
			for (Log log : logFile) {
				String str = log.getLog(true);

				fullLog.append(str + System.lineSeparator());
				if (log.getDegree() >= pickupLevel) {
					System.out.println(str);
				}
			}
		} else {
			for (Log log : logFile) {
				String str = log.getTime(true) + "[" + logFile.getModule()
						+ "] " + log.getLog(false);

				fullLog.append(str + System.lineSeparator());
				if (log.getDegree() >= pickupLevel) {
					System.out.println(str);
				}
			}
		}
	}

	public boolean saveLog() {
		final DateFormat fileDateFormat = new SimpleDateFormat(
				"yyyy_MM_dd-HH_mm_ss");
		Date date = new Date();
		File f = new File(save_location + fileDateFormat.format(date) + ".log");
		try {
			if (!f.exists()) {
				new File(save_location).mkdir();
				if (!f.createNewFile())
					return false;

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
		long start = System.currentTimeMillis();
		GamsionLogger logger = new GamsionLogger(GamsionLogger.DEBUG,
				"C:\\Users\\John\\logs\\");
		List<Log> logsForFile = new ArrayList<Log>();
		logsForFile.add(new Log(null, "Test", "This is a test.",
				GamsionLogger.INFO));
		logsForFile.add(new Log(null, "Test2", "This is too.",
				GamsionLogger.INFO));
		LogFile log = new LogFile("Tester", logsForFile);
		logger.log(log);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public void reset() {
		fullLog = new StringBuilder();
	}

	public void reset(int degree) {
		fullLog = new StringBuilder();
		this.pickupLevel = degree;
	}

	@Override
	public String getName() {
		return "Gamsion Logger";
	}

	@Override
	public String getUName() {
		return "Gamsion_Logger";
	}

	@Override
	public String getDescription() {
		return "Default Logger of Gamsion Modules.";
	}

	@Override
	public void shutDown() {
		saveLog();
	}

	@Override
	public String getVersion() {
		return "0.1A";
	}

	@Override
	public boolean hasLog() {
		return false;
	}

	@Override
	public LogFile readLog() {
		return null;
	}

	@Override
	public void resetLog() {
		
	}

}
