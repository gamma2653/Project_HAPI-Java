package com.gamsion.chris.mastermodules;

import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.LogFile;

public class MasterModule implements GamsionModule {
	Map<String, GamsionModule> modules = new HashMap<String, GamsionModule>();
	// this module's logFile
	LogFile logFile = new LogFile(getName());

	public MasterModule() {
		modules.put("Gamsion_Logger", new GamsionLogger(GamsionLogger.DEBUG,
				"C:\\Users\\John\\"));
		modules.put("Controller_Module", new ControllerModule());
	}

	/**
	 * Reads logs of all module's including it's own.
	 * 
	 */
	public void readAllLogs() {
		checkLogs();

		this.getGamsionLogger().log(logFile);
		;

	}

	public void checkLogs() {
		for (GamsionModule gm : modules.values()) {
			logFile.addAll(gm.readLog());
			gm.resetLog();
		}
	}

	public ControllerModule getControllerModule() {
		return (ControllerModule) modules.get("Controller_Module");
	}

	public GamsionLogger getGamsionLogger() {
		return (GamsionLogger) modules.get("Gamsion_Logger");
	}

	@Override
	public String getName() {
		return "Master Module";
	}

	@Override
	public String getUName() {
		return "Master_Module";
	}

	@Override
	public String getDescription() {
		return "You shouldn't be able to read this...";
	}

	@Override
	public void shutDown() {

	}

	@Override
	public String getVersion() {
		return "0.3A Dev";
	}

	@Override
	public boolean hasLog() {
		return !logFile.isEmpty();
	}

	@Override
	public LogFile readLog() {
		return logFile;
	}

	@Override
	public void resetLog() {
		logFile.clear();

	}

	public static void main(String[] args) {
		MasterModule mm = new MasterModule();

		mm.readAllLogs();

	}

}
