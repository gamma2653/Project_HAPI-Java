package com.gamsion.chris.mastermodules;

import com.gamsion.chris.EmotionModule.EmotionModule;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

/**
 * This class is used to create objects that contain all of the modules
 * necessary to have an entity act. It could also be called a Person module.
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 *
 */
public class ControllerModule implements GamsionModule, Cloneable {
	private EmotionModule emotionModule = new EmotionModule(
			"C:\\Users\\John\\Desktop\\save\\example2.txt",
			"C:/Users/John/git/Project HAPI/Project HAPI/bin/com/gamsion/chris/EmotionModule/emotions");
	private LogFile logFile = new LogFile(getName(), null);

	@Override
	public String getName() {
		return "Controller Module";
	}

	@Override
	public String getUName() {
		return "Controller_Module";
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void shutDown() {
		emotionModule.shutDown();
		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));
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
		// Use com.gamsion.chris.utility.log.LogFile (extends ArrayList<String>)
		// to store logs.
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		lf.addAll(emotionModule.readLog());
		this.resetLog();
		return lf;
	}

	@Override
	public void resetLog() {
		this.logFile.clear();
		this.emotionModule.resetLog();

	}

	/**
	 * @return - A reference to the emotionModule
	 */
	public EmotionModule getEmotionModule() {
		return this.emotionModule;
	}

	@Override
	public ControllerModule clone() {
		ControllerModule cm;
		try {
			cm = (ControllerModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		cm.emotionModule = this.getEmotionModule().clone();
		cm.logFile = this.logFile.clone();
		return cm;
	}

}
