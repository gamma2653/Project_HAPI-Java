package com.gamsion.chris.utility.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
	private static final DateFormat logDateFormat = new SimpleDateFormat(
			"HH:mm:ss");
	private final int degree;
	private final String module;
	private final String message;

	public LogFile(int degree, String module, String message) {
		this.degree = degree;
		this.module = module;
		this.message = message;
	}

	public String read() {
		return logDateFormat.format(new Date()) + " "
				+ GamsionLogger.levelNames[degree] + " " + module + ": "
				+ message;
	}

	public int getLevel() {
		return degree;
	}

	public String getModule() {
		return module;
	}

	public String getMessage() {
		return message;
	}

}
