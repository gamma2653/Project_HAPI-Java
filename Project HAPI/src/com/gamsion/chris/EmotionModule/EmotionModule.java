package com.gamsion.chris.EmotionModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gamsion.chris.EmotionModule.emotions.STDEmotion;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.exceptions.EmotionModuleFileException;
import com.gamsion.chris.utility.exceptions.EmotionNotFoundInSaveException;
import com.gamsion.chris.utility.exceptions.EmotionValueOutOfBoundsException;
import com.gamsion.chris.utility.exceptions.SuccessfulSaveCreationException;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

/**
 * The purpose of this class is to create an instance of Emotions. From there
 * you are able to manipulate the emotions depending on your program. It is also
 * made to be as dynamic as possible
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 */
public class EmotionModule implements GamsionModule, Cloneable {

	private LogFile logFile = new LogFile(getName(), null);
	// Global Random generator object.
	public final Random r = new Random();
	// Here are all the emotions to be used
	STDEmotion admiration, amazement, grief, happiness, loathing, rage, terror,
			vigilance;
	// The save file
	private File savefile;
	// String of the save file's path
	private String save_location;

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
	 * Use this to verify that a save location is properly formated and
	 * reachable by the Emotion Module.
	 * 
	 * @throws EmotionModuleFileException
	 * @throws SuccessfulSaveCreationException
	 * @throws EmotionValueOutOfBoundsException
	 * @throws EmotionNotFoundInSaveException
	 */
	public void verifySave() {

	}

	/**
	 * Loads the save into the EmotionModule.
	 * 
	 * @throws EmotionNotFoundInSaveException
	 */
	public void loadSave() throws EmotionNotFoundInSaveException {

	}

	/**
	 * Saves the emotions into the save file.
	 */
	public void save() {

	}

	/**
	 * @param save_location
	 *            - What save to use. <b>Must be absolute.</b>
	 * @param emotion_folder
	 *            - directories of the emotions to use.
	 */
	public EmotionModule(String save_location) {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "EmotionModule is initializing.",
				GamsionLogger.DEBUG));
		this.setSave_location(save_location);

		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "EmotionModule has been initialized.",
				GamsionLogger.DEBUG));

	}

	// Why do the droids say this out loud in Star Wars Battlefront II during
	// space battles SO MUCH!?
	@Override
	public void shutDown() {
		save();
		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));
	}

	/**
	 * Writes the emotion along with it's value in the proper format.
	 * 
	 * @param f
	 *            - File to write to.
	 * 
	 * @param em
	 *            - Emotion you are writing.
	 * 
	 * @param value
	 *            - value to write. if it is less than 0, will use the value
	 *            within the emotion.
	 * 
	 * @param overwrite
	 *            - whether to overwrite an existing emotion or write a new one.
	 */
	public static void writeEmotion(File f, STDEmotion em, double value,
			boolean overwrite) {
		/**
		GamsionLogger.globalLogFileAdd(new Log(LogFile.getLogDateFormat()
				.format(new Date()), "Static Emotion Module", em.getPentID()
				+ " is being overwritten with the value " + value
				+ " to the file " + f.getAbsolutePath()
				+ " (overwrite is set to " + overwrite + ")",
				GamsionLogger.DEBUG));
		if (value < 0)
			value = em.getValue();
		if (overwrite) {
			StringBuilder fileData = new StringBuilder("");
			try {

				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null)
					fileData.append(line + System.lineSeparator());

				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] split = fileData.toString().split(System.lineSeparator());
			fileData = new StringBuilder("");
			for (String s : split) {
				if (s.contains(em.getPentID()))
					s = em.getPentID() + "=" + value;
				fileData.append(s + System.lineSeparator());
			}
			try {
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(fileData.toString());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			StringBuilder fileData = new StringBuilder("");
			try {

				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null)
					fileData.append(line + System.lineSeparator());
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(fileData.toString());
				bw.write(em.getPentID() + "=" + String.valueOf(value)
						+ System.lineSeparator());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		GamsionLogger.globalLogFileAdd(new Log(LogFile.getLogDateFormat()
				.format(new Date()), "Static Emotion Module", em.getPentID()
				+ " was successfully overwritten with the value " + value
				+ "to the file " + f.getAbsolutePath()
				+ " (overwrite was set to " + overwrite + ")",
				GamsionLogger.DEBUG));
**/
	}

	/**
	 * @return - returns the current Map within EmotionModule
	 */
	public Map<String, STDEmotion> getEmotionMap() {
		Map<String, STDEmotion> emotions = new HashMap<String, STDEmotion>();
		emotions.put("admiration", admiration);
		emotions.put("amazement", amazement);
		emotions.put("grief", grief);
		emotions.put("happiness", happiness);
		emotions.put("loathing", loathing);
		emotions.put("rage", rage);
		emotions.put("terror", terror);
		emotions.put("vigilance", vigilance);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions list was gotten.", GamsionLogger.DEBUG));
		return emotions;

	}

	public List<STDEmotion> getEmotionList() {
		List<STDEmotion> emotions = new ArrayList<STDEmotion>();
		emotions.add(admiration);
		emotions.add(amazement);
		emotions.add(grief);
		emotions.add(happiness);
		emotions.add(loathing);
		emotions.add(rage);
		emotions.add(terror);
		emotions.add(vigilance);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions list was gotten.", GamsionLogger.DEBUG));
		return emotions;

	}

	/**
	 * @param pentID
	 *            - pentID to find emotion
	 * @return - returns the emotion within the map you requested. Cast it into
	 *         the needed emotion.
	 */
	public STDEmotion getEmotion(String pentID) {
		STDEmotion emotion;
		if (pentID.equalsIgnoreCase("admiration")) {
			emotion = admiration;
		} else if (pentID.equalsIgnoreCase("amazement")) {
			emotion = amazement;
		} else if (pentID.equalsIgnoreCase("grief")) {
			emotion = grief;
		} else if (pentID.equalsIgnoreCase("happiness")) {
			emotion = happiness;
		} else if (pentID.equalsIgnoreCase("loathing")) {
			emotion = loathing;
		} else if (pentID.equalsIgnoreCase("rage")) {
			emotion = rage;
		} else if (pentID.equalsIgnoreCase("terror")) {
			emotion = terror;
		} else if (pentID.equalsIgnoreCase("vigilance")) {
			emotion = vigilance;
		} else {
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), pentID + " was not found.", GamsionLogger.ERROR));
			emotion = null;
		}
		return emotion;
	}

	/**
	 * Sets the emotion to the selected value.
	 * 
	 * @param pentID
	 *            - pentID of the emotion.
	 * @param value
	 *            - value to set the emotion.
	 * @return - if the emotion is not found, returns false. Otherwise, true.
	 */
	public void setEmotion(String pentID, double value) {
		if (this.getEmotion(pentID) == null)
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), pentID + " was not found", GamsionLogger.ERROR));
		this.getEmotion(pentID).setValue(value);
	}

	/**
	 * @param pentID
	 *            - pentID of the emotion.
	 * @param value
	 *            - value to increment the emotion.
	 * @return - if the emotion is not found, returns false. Otherwise, true.
	 */
	public boolean incrementEmotion(String pentID, double value) {
		if (this.getEmotion(pentID) == null)
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), pentID + " was not found", GamsionLogger.ERROR));
		this.getEmotion(pentID).incrementValue(value);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), pentID + "was increased by " + value
						+ ". New value: " + this.getEmotion(pentID).getValue(),
				GamsionLogger.DEBUG));
		return true;
	}

	/**
	 * @param name
	 *            - pentID of the emotion. If null, will randomize all emotions.
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
	public void randomize(String name, int factor) {
		if (name == null) {
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), "Emotions were randomized.", GamsionLogger.DEBUG));

			incrementEmotion("admiration", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("amazement", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("grief", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("happiness", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("loathing", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("rage", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("terror", (r.nextInt(factor + 1) - factor / 2));
			incrementEmotion("vigilance", (r.nextInt(factor + 1) - factor / 2));

		} else {
			incrementEmotion(name, (r.nextInt(factor + 1) - factor / 2));
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), name + " was randomized.", GamsionLogger.DEBUG));
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

	/**
	 * Sets the path to load emotions an loads it.
	 * 
	 * @param location
	 *            - Absolute path to folder.
	 */

}

// I would like to thank all of my nonexistant fans out there! YOU GUYS ROCK!
// Time to party in my head.
// Oh. and Sheogorath says hi.

