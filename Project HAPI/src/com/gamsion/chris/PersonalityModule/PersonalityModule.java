package com.gamsion.chris.PersonalityModule;

import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.LogFile;

public class PersonalityModule implements GamsionModule{

	@Override
	public String getName() {
		return "Personality Module";
	}

	@Override
	public String getUName() {
		return "Personality_Module";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLog() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LogFile readLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetLog() {
		// TODO Auto-generated method stub
		
	}

}
