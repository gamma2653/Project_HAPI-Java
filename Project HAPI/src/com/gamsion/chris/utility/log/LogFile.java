package com.gamsion.chris.utility.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gamsion.chris.utility.GamsionModule;

/**
 * LogFile is a class that you may instantiate to keep Log classes. It extends
 * ArrayList&#60;Log&#62; allowing you to have a List with extra methods you
 * masy use better suited for Logs.
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class LogFile extends ArrayList<Log> implements Cloneable {
	private static final long serialVersionUID = 1L;
	private static final DateFormat logDateFormat = new SimpleDateFormat("HH.mm.ss");
	private static final DateFormat logFileDateFormat = new SimpleDateFormat("yy.MM.dd.HH.mm");
	private String module = null;
	private boolean overrideName = false;

	/**
	 * Standard Construction.
	 * 
	 * @param module
	 *            - name of a module this logFile is in. (Usually obtained via
	 *            this.getName() for GamsionModules). May be null if logs
	 *            contain their own module names.
	 * @param logs
	 *            - you may create a logFile with logs already in it. May be
	 *            null.
	 */
	public LogFile(String module, List<Log> logs) {
		super();
		if (module != null)
			this.module = module;
		if (logs != null)
			this.addAll(logs);

	}

	/**
	 * @return - A String representation of all the logs within this file.
	 */
	public String read() {
		StringBuilder logData = new StringBuilder();
		for (Log l : this) {
			logData.append(l.getLog(true) + System.lineSeparator());
		}
		return logData.toString();
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public static DateFormat getLogDateFormat() {
		return logDateFormat;
	}

	public static DateFormat getLogFileDateFormat() {
		return logFileDateFormat;
	}

	public void overrideLogName(boolean override) {
		this.overrideName = override;
	}

	public boolean getOverrideLogName() {
		return overrideName;
	}

	public LogFile clone() {
		LogFile logFile = (LogFile) super.clone();
		logFile.module = this.module;
		logFile.overrideName = this.overrideName;
		return logFile;
	}

	public void log(GamsionModule mod, String date, String message, int degree) {
		if (mod == null) {
			throw new RuntimeException("Invalid module. Must be a GamsionModule.");
		} else if (date == null) {
			date = LogFile.getLogDateFormat().format(new Date());
		} else if (message == null) {
			message = "N/A";
		}
		this.add(new Log(date, mod.getName(), message, degree));
	}

	public void log(GamsionModule mod, String message, int degree) {
		if (mod == null) {
			throw new RuntimeException("Invalid module. Must be a GamsionModule.");
		} else if (message == null) {
			message = "N/A";
		}
		String date = LogFile.getLogDateFormat().format(new Date());
		this.add(new Log(date, mod.getName(), message, degree));
	}
}
