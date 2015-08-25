package com.gamsion.chris.mastermodule;

import java.util.ArrayList;
import java.util.List;

import com.gamsion.chris.utility.EmotionModule.EmotionModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.module.GamsionModule;

public class ControllerModule implements GamsionModule{
	EmotionModule emotionModule = new EmotionModule("C:\\Users\\John\\Desktop\\save\\example2.txt");
	GamsionLogger logger = new GamsionLogger(GamsionLogger.DEBUG);
	public static void main(String[] args){
		ControllerModule master = new ControllerModule();
		master.readLogs();
		master.emotionModule.getEmotionList();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		master.readLogs();
	}
	
	public void readLogs(){
		List<String> EmotionModuleLog = new ArrayList<String>();
		EmotionModuleLog.addAll(emotionModule.readLog());
		for(int i = 0; i<EmotionModuleLog.size(); i++){
			int level = Integer.valueOf(EmotionModuleLog.get(i).substring(0, 1));
			EmotionModuleLog.set(i, EmotionModuleLog.get(i).substring(1));
			logger.log(emotionModule.getName(), level, EmotionModuleLog.get(i));
		}
	}
	
	
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
		return false;
	}




	@Override
	public List<String> readLog() {
		return null;
	}

}
