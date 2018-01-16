package com.gamsion.chris.utility.log;

import java.util.Date;

public class Log {
	private final String time;
	private final String message;
	private final String module;
	private final int degree;

	/**
	 * <p>
	 * Creates a Log to be used by the Log File.
	 * </p>
	 * 
	 * @param log
	 *            - log data
	 * @param date
	 *            - selected date of the log
	 * @param degree
	 *            - the degree of the log
	 * @param module
	 */
	public Log(String date, String module, String log, Integer degree) {
		if (log == null) {
			this.message = "";
		} else {
			this.message = log;
		}
		if (date == null) {
			this.time = LogFile.getLogDateFormat().format(new Date());
		} else {
			this.time = date;
		}
		if (degree == null) {
			this.degree = GamsionLogger.INFO;
		} else {
			this.degree = degree;
		}
		if (module == null) {
			this.module = "NoModuleGiven";
		} else {
			this.module = module;
		}

	}

	/**
	 * <p>
	 * (Same as toString())
	 * </p>
	 * 
	 * @param b
	 *            - If true, returns the full log. If false, returns just the
	 *            message.(Same as toString())
	 * @return - String representation of the log.
	 */
	public String getLog(boolean b) {
		if (b) {
			return String.format("[%s] %s: [%s] %s", GamsionLogger.levelNames[degree], time, module, message);
		} else {
			return message;
		}

	}

	@Override
	public String toString() {
		return getLog(true);
	}

	/**
	 * <p>Returns the time log was made.</p>
	 * <p>This is in the standard format "HH.mm.ss"</p>
	 * 
	 * @param b
	 *            - If true, returns raw String variable. If false, returns the
	 *            time minus the last two characters (for when default time is
	 *            take and ends with ": ")
	 *            
	 * @return - String representation of Time of Log.
	 */
	public String getTime(boolean b) {
		if (b) {
			return time;
		} else {
			return time.substring(0, time.length() - 2);
		}
	}

	/**
	 * Returns the degree of the log.
	 * 
	 * @return - the degree as an int
	 * @see com.gamsion.chris.utility.log.GamsionLogger.levelNames
	 */
	public int getDegree() {
		return degree;
	}
}
