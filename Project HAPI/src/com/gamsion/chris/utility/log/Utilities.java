package com.gamsion.chris.utility.log;

import java.util.Date;

public class Utilities {
	public static Log getDefaultLogShutdown(String modulename) {
		return new Log(LogFile.getLogDateFormat().format(new Date()),
				modulename, modulename + " has been shutdown.",
				GamsionLogger.DEBUG);
	}
}
