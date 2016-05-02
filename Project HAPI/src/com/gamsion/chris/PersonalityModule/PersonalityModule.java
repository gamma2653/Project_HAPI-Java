package com.gamsion.chris.PersonalityModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gamsion.chris.PersonalityModule.traits.STDTrait;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.Utilities;
import com.gamsion.chris.utility.exceptions.EmotionModuleFileException;
import com.gamsion.chris.utility.exceptions.EmotionNotFoundInSaveException;
import com.gamsion.chris.utility.exceptions.EmotionValueOutOfBoundsException;
import com.gamsion.chris.utility.exceptions.SuccessfulSaveCreationException;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

public class PersonalityModule implements GamsionModule, Cloneable {
	private LogFile logFile = new LogFile(getName(), null);
	// Global Random generator object.
	public final Random r = new Random();
	// Here are all the emotions to be used
	private Map<String, STDTrait> traits = null;
	private File savefile;
	private String save_location;
	private String trait_folder_location;
	
	
	
	public void loadTraits() {
		List<STDTrait> list = new ArrayList<STDTrait>();
		list = Utilities.getClassesAsTraits(Utilities.getFilesAsClass(this.trait_folder_location));
		traits = new HashMap<String, STDTrait>();
		for (STDTrait em : list)
			traits.put(em.getPentID(), em);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions were loaded from "
						+ trait_folder_location + ".", GamsionLogger.DEBUG));

	}
	
	/**
	 * @param save_location
	 *            - What save to use. <b>Must be absolute.</b>
	 * @param emotion_folder
	 *            - directories of the emotions to use.
	 */
	public PersonalityModule(String save_location, String emotion_folder) {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "EmotionModule is initializing.",
				GamsionLogger.DEBUG));
		this.setSave_location(save_location);
		this.setTrait_folder(emotion_folder);

		try {
			verifySave();
			loadSave();

		} catch (EmotionModuleFileException e) {
			e.printStackTrace();
		} catch (SuccessfulSaveCreationException e) {
			e.printStackTrace();
		} catch (EmotionValueOutOfBoundsException e) {
			e.printStackTrace();
		} catch (EmotionNotFoundInSaveException e) {
			e.printStackTrace();
		}
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "EmotionModule has been initialized.",
				GamsionLogger.DEBUG));

	}
	
	
	/**
	 * Returns the save_location.
	 * 
	 * @return save_location
	 */
	public String getSave_location() {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Save was gotten", GamsionLogger.DEBUG));
		return save_location;
	}

	/**
	 * Sets the save_location to the desired path. (Automatically updates
	 * savefile).
	 * 
	 * @param save_location
	 */
	public void setSave_location(String save_location) {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Save was set to a new value. Old Value: "
						+ this.save_location + " New Value: " + save_location,
				GamsionLogger.DEBUG));
		this.save_location = save_location;
		savefile = new File(save_location);
	}
	
	/**
	 * Sets the path to load emotions an loads it.
	 * 
	 * @param location
	 *            - Absolute path to folder.
	 */
	public void setTrait_folder(String location) {
		this.trait_folder_location = location;
		this.loadTraits();
	}

	/**
	 * @return - the path to the folder.
	 */
	public String getEmotion_folder() {
		return this.trait_folder_location;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// All implementations and inherited utilities.

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

		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));

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

	@Override
	public PersonalityModule clone() {
		PersonalityModule pm;
		try {
			pm = (PersonalityModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		pm.logFile = this.logFile.clone();
		return pm;

	}

}
