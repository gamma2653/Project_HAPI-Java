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
public class GamsionLogger implements GamsionModule, Cloneable {
	/**
	 * Raw information. Any small action.
	 */
	public static final int DEBUG = 0;
	/**
	 * Used for general console information. More important than DEBUG.
	 */
	public static final int INFO = 1;
	/**
	 * Light error, program still able to run.
	 */
	public static final int ERROR = 2;
	/**
	 * Severe error, program may close suddenly/ freeze.
	 */
	public static final int SEVERE = 3;
	/**
	 * Fatal Error. Program can not continue.
	 */
	public static final int FATAL = 4;
	/**
	 * Used to find the name of each degree. (0=DEBUG, 1=INFO, 2=ERROR,
	 * 3=SEVERE, 4=FATAL)
	 */
	public static final String[] levelNames = { "DEBUG", "INFO", "ERROR", "SEVERE", "FATAL" };
	private int pickupLevel;
	private String save_location;
	private StringBuilder fullLog = new StringBuilder();
	private static LogFile globalLog = new LogFile(null, null);

	/**
	 * @param printLevel
	 *            - detection degree of logs.
	 *            <p>
	 *            <b>Ex)</b> new GamsionLogger(GamsionLogger.<b>ERROR</b>,
	 *            save_location) would pick up logs with the degrees
	 *            <b>ERROR</b>, <b>SEVERE</b>, and <b>FATAL</b>.
	 *            </p>
	 * @param save_location
	 *            - Save location of the log files.
	 */
	public GamsionLogger(int printLevel, String save_location) {
		this.save_location = save_location;
		pickupLevel = printLevel;

	}

	/**
	 * @param printLevel
	 *            - detection degree of logs.
	 *            <p>
	 *            <b>Ex)</b> new GamsionLogger(GamsionLogger.<b>ERROR</b>,
	 *            save_location) would pick up logs with the degrees
	 *            <b>ERROR</b>, <b>SEVERE</b>, and <b>FATAL</b>.
	 *            </p>
	 * @return - Whether the pickup degree was changed or not.
	 */
	public boolean setPickupDegree(int printLevel) {
		if (pickupLevel == printLevel) {
			return false;
		} else {
			pickupLevel = printLevel;
			return true;
		}
	}

	/**
	 * @param logFile
	 *            - Cycles throught the log
	 */
	public void log(LogFile logFile, boolean checkGlobal) {
		if (GamsionLogger.hasGlobalLog() && checkGlobal) {
			LogFile global = GamsionLogger.getGlobalLog();
			for (Log l : global) {
				fullLog.append(l.getLog(true) + System.lineSeparator());
				if (l.getDegree() >= pickupLevel) {
					System.out.println(l);
				}
			}
			GamsionLogger.resetGlobalLog();
		}
		if (logFile.getModule() == null || !logFile.getOverrideLogName()) {
			for (Log log : logFile) {
				fullLog.append(log.getLog(true) + System.lineSeparator());
				if (log.getDegree() >= pickupLevel) {
					System.out.println(log.getLog(true));
				}
			}
		} else {
			for (Log log : logFile) {
				String str = log.getTime(true) + "[" + logFile.getModule() + "] " + log.getLog(false);

				fullLog.append(str + System.lineSeparator());
				if (log.getDegree() >= pickupLevel) {
					System.out.println(str);
				}
			}
		}

	}

	public boolean saveLog() {
		final DateFormat fileDateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
		Date date = new Date();
		File f = new File(save_location + fileDateFormat.format(date) + ".log");
		try {
			if (!f.exists()) {
				if (new File(save_location).mkdir())
					return false;
				System.out.println(f.getAbsolutePath());
				if (!f.createNewFile())
					return false;

			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
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
		long start = System.nanoTime();
		GamsionLogger logger = new GamsionLogger(GamsionLogger.DEBUG, "C:\\Users\\John\\logs\\");
		List<Log> logsForFile = new ArrayList<Log>();
		logsForFile.add(new Log(null, "Test", "This is a test.", GamsionLogger.INFO));
		logsForFile.add(new Log(null, "Test2", "This is too.", GamsionLogger.INFO));
		LogFile log = new LogFile("Tester", logsForFile);
		logger.log(log, true);
		long end = System.nanoTime();
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
		globalLog.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(),
				getName() + " has been shutdown.", GamsionLogger.DEBUG));
	}

	@Override
	public String getVersion() {
		return "0.1A";
	}

	@Override
	public boolean hasLog() {
		return !globalLog.isEmpty();
	}

	@Override
	public LogFile readLog() {
		return globalLog;
	}

	@Override
	public void resetLog() {
		globalLog.clear();
	}

	public static void globalLogFileAdd(LogFile lf) {
		globalLog.addAll(lf);
	}

	public static void globalLogFileAdd(Log l) {
		globalLog.add(l);
	}

	public static LogFile getGlobalLog() {
		return globalLog;
	}

	public static void resetGlobalLog() {

		globalLog.clear();
	}

	public static boolean hasGlobalLog() {
		return !globalLog.isEmpty();
	}

	public GamsionLogger clone() {
		GamsionLogger logger;
		try {
			logger = (GamsionLogger) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

		logger.fullLog = new StringBuilder(this.fullLog);
		logger.pickupLevel = this.pickupLevel;
		logger.save_location = this.save_location;
		return logger;

	}

}
