package com.gamsion.chris.mastermodules;

import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.EmotionModule.emotions.EmotionType;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

public class MasterModule implements GamsionModule, Cloneable {
	private Map<String, GamsionModule> modules = new HashMap<String, GamsionModule>();
	// this module's logFile
	private LogFile logFile = new LogFile(getName(), null);

	public MasterModule() {
		GamsionLogger gm = new GamsionLogger(GamsionLogger.DEBUG, "C:\\Users\\Toshiba\\logs\\");
		modules.put(gm.getUName(), gm);
	}

	/**
	 * Reads logs of all module's including it's own.
	 * 
	 */
	public void readAllLogs() {
		GamsionLogger logger = this.getGamsionLogger();
		checkLogs();
		logger.log(logFile, true);
		if (!logger.saveLog()) {
			System.out.printf("%s (%s) was not able to save. Log data lost: %s", logger.getName(),
					logger.getDescription(), logger.getFullLog().toString());
		}

	}

	public void checkLogs() {
		for (GamsionModule gm : modules.values()) {
			logFile.addAll(gm.readLog());
			gm.resetLog();
		}
	}

	public ControllerModule getControllerModule(String name) {
		return (ControllerModule) modules.get(name);
	}

	public void addController(String name, double agreeableness, double conscientousness, double extraversion,
			double neuroticism, double openness) {
		modules.put(name, new ControllerModule(name, agreeableness, conscientousness, extraversion, neuroticism, openness));
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
		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));
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
		this.checkLogs();
		return !logFile.isEmpty();
	}

	@Override
	public LogFile readLog() {
		this.readAllLogs();
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		this.resetLog();
		return lf;

	}

	@Override
	public void resetLog() {
		logFile.clear();
		for (GamsionModule gm : this.modules.values()) {
			gm.resetLog();
		}

	}

	public void addModule(GamsionModule gm) {
		modules.put(gm.getUName(), gm);
	}

	@Override
	public MasterModule clone() {
		this.readAllLogs();
		MasterModule mm;
		try {
			mm = (MasterModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		for (GamsionModule gm : this.modules.values()) {
			if (gm instanceof Cloneable) {
				mm.addModule(gm.clone());
			} else {
				throw new RuntimeException(String.format("Cloning is not support for %s.", gm.getName()));
			}
		}

		mm.logFile = this.logFile.clone();
		return mm;
	}

	public static void main(String[] args) {
		MasterModule mm = new MasterModule();
		mm.getGamsionLogger().setPickupDegree(GamsionLogger.ERROR);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mm.addController("gamma", 50000, 50000, 50000, 50000, 50000);
		mm.getControllerModule("gamma").getEmotion().initializeEM();
		mm.getControllerModule("gamma").getEmotion().setEmotion(EmotionType.admiration, 10000);
		System.out.println(mm.getControllerModule("gamma").getEmotion().getEmotionList());
		mm.getControllerModule("gamma").process(Action.ADMIRE, 100000);
		System.out.println(mm.getControllerModule("gamma").getEmotion().getEmotionList());
		
		mm.readAllLogs();

	}

}
