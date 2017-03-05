package com.gamsion.chris.PersonalityModule;

import java.util.Date;
import java.util.Random;

import com.gamsion.chris.PersonalityModule.traits.Trait;
import com.gamsion.chris.PersonalityModule.traits.TraitType;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.UniqueModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

public class PersonalityModule implements GamsionModule, UniqueModule, Cloneable {
	/**
	 * Needed as a unique identifier. Received from Controller.
	 */
	private String idName;
	private LogFile logFile = new LogFile(getName(), null);
	// Global Random generator object.
	public final Random r = new Random();
	// private File savefile;
	private String save_location;
	private String trait_folder_location;
	public Trait agreeableness, conscientousness, extraversion, neuroticism, openness;

	/**
	 * @param idName
	 * @param agreeableness
	 * @param conscientousness
	 * @param extraversion
	 * @param neuroticism
	 * @param openness
	 */
	public PersonalityModule(String idName, double agreeableness, double conscientousness, double extraversion,
			double neuroticism, double openness) {
		this.idName = idName;
		this.initializePM(agreeableness, conscientousness, extraversion, neuroticism, openness);
	}

	public void initializePM(double agreeableness, double conscientousness, double extraversion, double neuroticism, double openness) {
		this.agreeableness = new Trait(TraitType.agreeableness, agreeableness);
		this.conscientousness = new Trait(TraitType.conscientousness, conscientousness);
		this.extraversion = new Trait(TraitType.extraversion, extraversion);
		this.neuroticism = new Trait(TraitType.neuroticism, neuroticism);
		this.openness = new Trait(TraitType.openness, openness);
		
	}

	public void setTrait(String name, Trait t) {
		switch(name){
		case "agreeableness":
			this.agreeableness = t;
			break;
		case "conscientousness":
			this.conscientousness = t;
			break;
		case "extraversion":
			this.extraversion = t;
			break;
		case "neuroticism":
			this.neuroticism = t;
			break;
		case "openness":
			this.openness = t;
			break;
		}
	}

	public Trait getTrait(String name) {
		switch(name){
		case "agreeableness":
			return agreeableness;
		case "conscientousness":
			return conscientousness;
		case "extraversion":
			return extraversion;
		case "neuroticism":
			return neuroticism;
		case "openness":
			return openness;
		default:
			return null;	
		}
	}

	public void loadTraits() {

		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(),
				"Emotions were loaded from " + trait_folder_location + ".", GamsionLogger.DEBUG));

	}

	// /**
	// * @param save_location
	// * - What save to use. <b>Must be absolute.</b>
	// * @param emotion_folder
	// * - directories of the emotions to use.
	// */
	// public PersonalityModule(String save_location, String emotion_folder) {
	// logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
	// getName(), "EmotionModule is initializing.",
	// GamsionLogger.DEBUG));
	// this.setSave_location(save_location);
	// this.setTrait_folder(emotion_folder);
	//
	//// try {
	//// verifySave();
	//// loadSave();
	////
	//// } catch (EmotionModuleFileException e) {
	//// e.printStackTrace();
	//// } catch (SuccessfulSaveCreationException e) {
	//// e.printStackTrace();
	//// } catch (EmotionValueOutOfBoundsException e) {
	//// e.printStackTrace();
	//// } catch (EmotionNotFoundInSaveException e) {
	//// e.printStackTrace();
	//// }
	// logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
	// getName(), "EmotionModule has been initialized.",
	// GamsionLogger.DEBUG));
	//
	// }

	/**
	 * Returns the save_location.
	 * 
	 * @return save_location
	 */
	public String getSave_location() {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), "Save was gotten",
				GamsionLogger.DEBUG));
		return save_location;
	}

	/**
	 * Sets the save_location to the desired path. (Automatically updates
	 * savefile).
	 * 
	 * @param save_location
	 */
	public void setSave_location(String save_location) {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(),
				"Save was set to a new value. Old Value: " + this.save_location + " New Value: " + save_location,
				GamsionLogger.DEBUG));
		this.save_location = save_location;
		// savefile = new File(save_location);
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
	public String getTrait_folder() {
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

	@Override
	public String getUniqueID() {
		return this.idName;
	}

	@Override
	public void setUniqueID(String idName) {
		this.idName = idName;
	}

}
