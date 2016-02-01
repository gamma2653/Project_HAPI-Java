package com.gamsion.chris.PersonalityModule;

import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.Utilities;

public class PersonalityModule implements GamsionModule{
	private LogFile logFile = new LogFile(getName(), null);
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
		return null;
	}

	@Override
	public void shutDown() {
		
		logFile.add(Utilities.getDefaultLogShutdown(getName()));
		
	}

	@Override
	public String getVersion() {
		return "0.1 DEV ALPHA";
	}
	@Override
	public boolean hasLog() {
		return !logFile.isEmpty();
	}

	@Override
	public LogFile readLog() {
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		return lf;
	}

	@Override
	public void resetLog() {
		logFile.clear();
		
	}

}
