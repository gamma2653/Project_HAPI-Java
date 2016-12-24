package com.gamsion.chris.EmotionModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gamsion.chris.EmotionModule.emotions.Emotion;
import com.gamsion.chris.EmotionModule.emotions.EmotionType;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.exceptions.EmotionNotFoundInSaveException;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;
import com.gamsion.chris.utility.sql.SQLTools;

/**
 * The purpose of this class is to create an instance of Emotions. From there
 * you are able to manipulate the emotions depending on your program. It is also
 * made to be as dynamic as possible
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 */
public class EmotionModule implements GamsionModule, Cloneable {
	private String idName;
	private LogFile logFile = new LogFile(getName(), null);
	// Global Random generator object.
	public final Random r = new Random();
	// Here are all the emotions to be used
	Emotion admiration, amazement, grief, happiness, loathing, rage, terror, vigilance;
	// String of the save file's path
	private String save_location;
	private SQLTools save;

	@Override
	public boolean hasLog() {
		// Check if log stack is empty.
		return !logFile.isEmpty();
	}

	@Override
	public LogFile readLog() {
		// Use com.gamsion.chris.utility.log.LogFile (extends ArrayList<String>)
		// to store logs.
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		this.resetLog();
		return lf;
	}

	/**
	 * For testing purposes and new modules. Initializes Emotions with default
	 * values.
	 */
	public void initializeEM() {
		this.admiration = new Emotion(EmotionType.admiration);
		this.amazement = new Emotion(EmotionType.amazement);
		this.grief = new Emotion(EmotionType.grief);
		this.happiness = new Emotion(EmotionType.happiness);
		this.loathing = new Emotion(EmotionType.loathing);
		this.rage = new Emotion(EmotionType.rage);
		this.terror = new Emotion(EmotionType.terror);
		this.vigilance = new Emotion(EmotionType.vigilance);
	}

	/**
	 * Use this to verify that a save name ID is existant and reachable by the
	 * Emotion Module.
	 * 
	 * @param name
	 * @return
	 */
	public boolean verifySave(String name) {
		return save.verify(name);
	}

	/**
	 * Loads emotions from the save from the name ID given.
	 * 
	 * @param name
	 * @throws EmotionNotFoundInSaveException
	 */
	public void loadSave(String name) throws EmotionNotFoundInSaveException {
		this.setEmotionMap(save.getEmotions(name));
	}

	/**
	 * Saves the emotions under the name ID given.
	 * 
	 * @param name
	 */
	public void save(String name) {
		save.saveEmotions(name, this.getEmotionMap());
	}

	/**
	 * <p>
	 * Constructs the default EmotionModule
	 * </p>
	 * 
	 * @param idName
	 * @param save_location
	 */
	public EmotionModule(String idName, String save_location) {
		this.idName = idName;
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), "EmotionModule is initializing.",
				GamsionLogger.DEBUG));
		this.setSave_location(save_location);
		logFile.log(this, "EmotionModule has been initialized", GamsionLogger.DEBUG);

	}

	// Why do the droids say this out loud in Star Wars Battlefront II during
	// space battles SO MUCH!?
	@Override
	public void shutDown() {
		save(idName);
		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));
	}

	/**
	 * <p>
	 * Allows for emotions to be mapped according to a map of ids and values.
	 * </p>
	 * 
	 * @param emotions
	 */
	public void setEmotionMap(Map<EmotionType, Emotion> emotions) {
		admiration = emotions.get(admiration.getType());
		amazement = emotions.get(amazement.getType());
		grief = emotions.get(grief.getType());
		happiness = emotions.get(happiness.getType());
		loathing = emotions.get(loathing.getType());
		rage = emotions.get(rage.getType());
		terror = emotions.get(terror.getType());
		vigilance = emotions.get(vigilance.getType());
	}

	/**
	 * Returns the current Map within EmotionModule
	 * 
	 * @return
	 */
	public Map<EmotionType, Emotion> getEmotionMap() {
		Map<EmotionType, Emotion> emotions = new HashMap<EmotionType, Emotion>();
		emotions.put(admiration.getType(), admiration);
		emotions.put(amazement.getType(), amazement);
		emotions.put(grief.getType(), grief);
		emotions.put(happiness.getType(), happiness);
		emotions.put(loathing.getType(), loathing);
		emotions.put(rage.getType(), rage);
		emotions.put(terror.getType(), terror);
		emotions.put(vigilance.getType(), vigilance);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), "Emotions map was gotten.",
				GamsionLogger.DEBUG));
		return emotions;

	}

	/**
	 * Returns a list of the emotions.
	 * 
	 * @return
	 */
	public List<Emotion> getEmotionList() {
		List<Emotion> emotions = new ArrayList<Emotion>();
		emotions.add(admiration);
		emotions.add(amazement);
		emotions.add(grief);
		emotions.add(happiness);
		emotions.add(loathing);
		emotions.add(rage);
		emotions.add(terror);
		emotions.add(vigilance);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), "Emotions list was gotten.",
				GamsionLogger.DEBUG));
		return emotions;

	}

	/**
	 * @param emotionID
	 *            - emotionID to find emotion
	 * @return - returns the emotion within the map you requested.
	 */
	public Emotion getEmotion(EmotionType emotionID) {
		Emotion emotion;
		if (emotionID == EmotionType.admiration) {
			emotion = admiration;
		} else if (emotionID == EmotionType.amazement) {
			emotion = amazement;
		} else if (emotionID == EmotionType.grief) {
			emotion = grief;
		} else if (emotionID == EmotionType.happiness) {
			emotion = happiness;
		} else if (emotionID == EmotionType.loathing) {
			emotion = loathing;
		} else if (emotionID == EmotionType.rage) {
			emotion = rage;
		} else if (emotionID == EmotionType.terror) {
			emotion = terror;
		} else if (emotionID == EmotionType.vigilance) {
			emotion = vigilance;
		} else {
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), emotionID + " was not found.",
					GamsionLogger.ERROR));
			emotion = null;
		}
		return emotion;
	}

	/**
	 * Sets the emotion to the selected value.
	 * 
	 * @param emotionID
	 *            - emotionID of the emotion.
	 * @param value
	 *            - value to set the emotion.
	 */
	public void setEmotion(EmotionType emotionID, double value) {
		if (this.getEmotion(emotionID) == null)
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), emotionID + " was not found.",
					GamsionLogger.ERROR));
		this.getEmotion(emotionID).setValue(value);
	}

	/**
	 * @param emotionID
	 *            - emotionID of the emotion.
	 * @param value
	 *            - value to increment the emotion.
	 * @return - if the emotion is not found, returns false. Otherwise, true.
	 */
	public boolean incrementEmotion(EmotionType emotionID, double value) {
		if (this.getEmotion(emotionID) == null)
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), emotionID + " was not found",
					GamsionLogger.ERROR));
		this.getEmotion(emotionID).incrementValue(value);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(),
				emotionID + "was increased by " + value + ". New value: " + this.getEmotion(emotionID).getValue(),
				GamsionLogger.DEBUG));
		return true;
	}

	/**
	 * @param emotionID
	 *            - emotionID of the emotion. If null, will randomize all
	 *            emotions.
	 * @param factor
	 *            - Amount of Error you want to randomize it by.
	 *            <p>
	 *            <b>Example:</b>
	 *            </p>
	 *            <p>
	 *            if your emotion is 500000, and I do: randomize(emotionID,
	 *            200), the end value will be anywhere from 499900 - 500100.
	 *            </p>
	 */
	public void randomize(EmotionType emotionID, int factor) {
		if (emotionID == null) {
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), "Emotions were randomized.",
					GamsionLogger.DEBUG));

			incrementEmotion(EmotionType.admiration, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.amazement, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.grief, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.happiness, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.loathing, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.rage, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.terror, (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion(EmotionType.vigilance, (r.nextInt(factor + 1) - factor / 2));

		} else {
			incrementEmotion(emotionID, (r.nextInt(factor + 1) - factor / 2));
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(),
					emotionID + " was randomized.", GamsionLogger.DEBUG));
		}
	}

	@Override
	// So the name's Module. Emotion Module.
	public String getName() {
		return "Emotion Module";
	}

	@Override
	// Hey I'm unique, aren't I?
	public String getUName() {
		return "Emotion_Module";
	}

	@Override
	// This is a description of the getDescription() method: It's a description.
	public String getDescription() {
		return "This module allows the characters to have an instance of emotion that can be manipulated.";
	}

	@Override
	// Version for the conversion of this immersion feature. I just said
	// immersion because it will get this more downloads. #Skyrim references
	public String getVersion() {
		return "0.1 DEV ALPHA";
	}

	/**
	 * Returns the save_location.
	 * 
	 * @return save_location
	 */
	public String getSave_location() {
		logFile.add(new Log(null, getName(), "Save was gotten",
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
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()), getName(), String
				.format("Save was set to a new value. Old Value: %s New Value: %s", this.save_location, save_location),
				GamsionLogger.DEBUG));
		this.save_location = save_location;
	}

	@Override
	public void resetLog() {
		logFile.clear();

	}

	@Override
	public EmotionModule clone() {
		EmotionModule em;
		try {
			em = (EmotionModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		em.setSave_location(save_location);
		em.admiration = this.admiration.clone();
		em.amazement = this.amazement.clone();
		em.grief = this.grief.clone();
		em.happiness = this.happiness.clone();
		em.loathing = this.loathing.clone();
		em.rage = this.rage.clone();
		em.terror = this.terror.clone();
		em.vigilance = this.vigilance.clone();

		LogFile temp = this.logFile.clone();
		temp.addAll(em.logFile);
		em.logFile = temp;
		return em;
	}

}

// I would like to thank all of my nonexistant fans out there! YOU GUYS ROCK!
// Time to party in my head.
// Oh. and Sheogorath says hi.
