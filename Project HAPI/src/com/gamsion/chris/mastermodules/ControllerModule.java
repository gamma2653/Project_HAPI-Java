package com.gamsion.chris.mastermodules;

import com.gamsion.chris.EmotionModule.EmotionModule;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.LogFile;

public class ControllerModule implements GamsionModule {
	EmotionModule emotionModule = new EmotionModule(
			"C:\\Users\\John\\Desktop\\save\\example2.txt");
	LogFile logFile = new LogFile(getName());

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getUName() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void shutDown() {

	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public boolean hasLog() {
		return !logFile.isEmpty() || this.emotionModule.hasLog();
	}

	@Override
	public LogFile readLog() {
		LogFile joinedLog = new LogFile(getName());
		joinedLog.addAll(logFile);
		joinedLog.addAll(emotionModule.readLog());

		return joinedLog;
	}

	@Override
	public void resetLog() {
		logFile.clear();

	}

}
