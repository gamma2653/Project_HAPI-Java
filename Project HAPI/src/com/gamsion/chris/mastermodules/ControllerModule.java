package com.gamsion.chris.mastermodules;

import java.util.Date;

import com.gamsion.chris.EmotionModule.EmotionModule;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;

/**
 * This class is used to create objects that contain all of the modules
 * necessary to have an entity act. It could also be called a Person module.
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 *
 */
public class ControllerModule implements GamsionModule {
	private EmotionModule emotionModule = new EmotionModule(
			"C:\\Users\\John\\Desktop\\save\\example2.txt");
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
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), getName() + " has been shutdown.",
				GamsionLogger.DEBUG));
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
		LogFile joinedLog = new LogFile(getName(), null);
		joinedLog.addAll(logFile);
		joinedLog.addAll(emotionModule.readLog());

		return joinedLog;
	}

	@Override
	public void resetLog() {
		logFile.clear();

	}
	
	/**
	 * @return - A reference to the emotionModule
	 */
	public EmotionModule getEmotionModule(){
		return this.emotionModule;
	}
	@Override
	public ControllerModule clone(){
		ControllerModule cm = new ControllerModule();
		cm.emotionModule = this.getEmotionModule().clone();
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		cm.logFile = lf;
		return cm;
	}

}
