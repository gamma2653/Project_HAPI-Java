package com.gamsion.chris.mastermodule;

import java.util.ArrayList;
import java.util.List;

import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.module.GamsionModule;

public class MasterModule implements GamsionModule{
	GamsionLogger logger = new GamsionLogger(GamsionLogger.DEBUG, "C:\\Users\\John\\");
	ControllerModule emotions = new ControllerModule();
	public void readLogs(){
		List<String> EmotionModuleLog = new ArrayList<String>();
		EmotionModuleLog.addAll(emotions.emotionModule.readLog());
		for(int i = 0; i<EmotionModuleLog.size(); i++){
			int level = Integer.valueOf(EmotionModuleLog.get(i).substring(0, 1));
			EmotionModuleLog.set(i, EmotionModuleLog.get(i).substring(1));
			logger.log(new LogFile(level,emotions.emotionModule.getName(), EmotionModuleLog.get(i)));
		}
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
		return false;
	}

	@Override
	public List<String> readLog() {
		// TODO Auto-generated method stub
		return null;
	}

}
