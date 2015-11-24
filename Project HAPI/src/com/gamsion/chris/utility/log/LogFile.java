package com.gamsion.chris.utility.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogFile extends ArrayList<Log> {
	private static final long serialVersionUID = 1L;
	private static final DateFormat logDateFormat = new SimpleDateFormat(
			"HH.mm.ss: ");
	private static final DateFormat logFileDateFormat = new SimpleDateFormat(
			"yy.MM.dd.HH.mm");
	private String module = null;
	private boolean overrideName = false;

	public LogFile() {
		super();
	}

	public LogFile(String module) {
		super();
		this.module = module;
	}

	public LogFile(String module, Log[] logs) {
		super();
		this.module = module;
		for (Log l : logs)
			this.add(l);

	}

	public LogFile(String module, List<Log> logs) {
		super();
		this.module = module;
		for (Log l : logs)
			this.add(l);

	}

	public LogFile(Log[] logs) {
		super();
		for (Log l : logs)
			this.add(l);
	}

	public LogFile(List<Log> logs) {
		super();
		for (Log l : logs)
			this.add(l);
	}

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

}
