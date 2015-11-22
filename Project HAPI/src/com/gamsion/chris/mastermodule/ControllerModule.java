package com.gamsion.chris.mastermodule;

import java.util.List;

import com.gamsion.chris.utility.EmotionModule.EmotionModule;
import com.gamsion.chris.utility.module.GamsionModule;

public class ControllerModule implements GamsionModule{
	EmotionModule emotionModule = new EmotionModule("C:\\Users\\John\\Desktop\\save\\example2.txt");
	
	public static void main(String[] args){
		
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
