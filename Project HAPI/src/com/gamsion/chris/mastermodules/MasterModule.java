package com.gamsion.chris.mastermodules;

import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.LogFile;

public class MasterModule implements GamsionModule {
	private Map<String, GamsionModule> modules = new HashMap<String, GamsionModule>();
	// this module's logFile
	private LogFile logFile = new LogFile(getName(), null);

	public MasterModule() {
		GamsionLogger gm = new GamsionLogger(GamsionLogger.DEBUG,
				"C:\\Users\\John\\logs\\");
		ControllerModule cm = new ControllerModule();
		modules.put(gm.getUName(), gm);
		modules.put(cm.getUName(), cm);
	}

	/**
	 * Reads logs of all module's including it's own.
	 * 
	 */
	public void readAllLogs() {
		GamsionLogger logger = this.getGamsionLogger();
		checkLogs();
		logger.log(logFile, true);
		logger.saveLog();

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
		for (GamsionModule gm : modules.values()) {
			gm.shutDown();
		}
		readAllLogs();
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

	public void addModule(GamsionModule gm) {
		modules.put(gm.getUName(), gm);
	}
	@Override
	public MasterModule clone(){
		MasterModule mm = new MasterModule();
		Map<String, GamsionModule> moduletemp = new HashMap<String, GamsionModule>();
		moduletemp.putAll(this.modules);
		mm.modules = moduletemp;
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		mm.logFile = lf;
		return mm;
	}

	public static void main(String[] args) {

		MasterModule mm = new MasterModule();
		mm.getControllerModule().getEmotionModule().randomize("happy", 100);
		System.out.println(mm.getControllerModule().getEmotionModule()
				.getEmotionSafe("happy").getValue());
		mm.shutDown();

	}

}
