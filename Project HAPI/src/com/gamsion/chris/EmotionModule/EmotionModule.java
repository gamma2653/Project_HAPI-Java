package com.gamsion.chris.EmotionModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;

import com.gamsion.chris.EmotionModule.emotions.STDEmotion;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.exceptions.EmotionModuleFileException;
import com.gamsion.chris.utility.exceptions.EmotionNotFoundInSaveException;
import com.gamsion.chris.utility.exceptions.EmotionValueOutOfBoundsException;
import com.gamsion.chris.utility.exceptions.SuccessfulSaveCreationException;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.Log;
import com.gamsion.chris.utility.log.LogFile;

;

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
	private Map<String, STDEmotion> emotions = null;
	// The save file
	private File savefile;
	// String of the save file's path
	private String save_location;

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

	/**
	 * Loads all the emotions into one List of the STDEmotion superclass.
	 * 
	 * @param dir
	 *            - the file path to the folder with the emotions
	 * @return - a list of the found emotions within the directory
	 */
	public static List<STDEmotion> loadEmotions(String dir) {
		List<File> fileA = null;
		List<STDEmotion> em = new ArrayList<STDEmotion>();
		fileA = new ArrayList<File>(Arrays.asList(new File(dir).listFiles()));
		fileA.remove(new File(dir, "STDEmotion.class"));

		// cycle through the files and add the emotions to the List
		for (File f : fileA) {
			String stbr = f.getAbsolutePath();
			String strb;
			stbr = stbr.substring(stbr.indexOf("com" + File.separator
					+ "gamsion" + File.separator + "chris" + File.separator
					+ "EmotionModule" + File.separator + "emotions"));
			stbr = stbr.replaceAll(Matcher.quoteReplacement(File.separator),
					".");
			strb = stbr.replaceAll(".class", "").toString();

			try {
				em.add((STDEmotion) Class.forName(strb).newInstance());

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		return em;
	}

	/**
	 * This loads the emotions into the Module. May be invoked after initial
	 * instantiation to update the emotion list.
	 */
	public void loadEmotions() {
		List<STDEmotion> list = new ArrayList<STDEmotion>();
		list = loadEmotions("C:/Users/John/git/Project HAPI/Project HAPI/bin/com/gamsion/chris/EmotionModule/emotions");
		Iterator<STDEmotion> it = list.iterator();
		emotions = new HashMap<String, STDEmotion>();
		while (it.hasNext()) {
			STDEmotion em = it.next();
			emotions.put(em.getPentID(), em);

		}
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions were loaded.", GamsionLogger.DEBUG));

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
	public void verifySave() throws EmotionModuleFileException,
			SuccessfulSaveCreationException, EmotionValueOutOfBoundsException,
			EmotionNotFoundInSaveException {
		try {
			// Here I check to see if the file exists and if not writes to it.
			if (!savefile.exists()) {
				savefile.createNewFile();
				if (!savefile.exists())
					throw new EmotionModuleFileException(
							EmotionModuleFileException.FILE_CAUSE);

				// Here are default values
				Iterator<STDEmotion> it = emotions.values().iterator();

				while (it.hasNext()) {
					STDEmotion em = it.next();
					writeEmotion(savefile, em, -1, false);
				}
				throw new SuccessfulSaveCreationException(
						SuccessfulSaveCreationException.STANDARD_CAUSE);
			}
			FileReader fr = new FileReader(savefile);
			BufferedReader br = new BufferedReader(fr);
			// Here I will check each value to see if it is valid
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if ((!line.equals("")) && !line.equals(System.lineSeparator())) {
					Iterator<STDEmotion> it = emotions.values().iterator();
					boolean found = false;
					while (it.hasNext()) {
						String name = line.substring(0, 5);
						String value = line.substring(6);
						STDEmotion em = it.next();
						if (name.equalsIgnoreCase(em.getPentID()))
							found = true;
						if (Integer.valueOf(value) > 1000000) {
							br.close();
							throw new EmotionValueOutOfBoundsException(
									EmotionValueOutOfBoundsException.STANDARD_CAUSE
											+ " value greater than 1000000.");
						}
						if (Integer.valueOf(value) < 0) {
							br.close();
							throw new EmotionValueOutOfBoundsException(
									EmotionValueOutOfBoundsException.STANDARD_CAUSE
											+ " value less than 0.");
						}
					}
					if (!found) {
						br.close();
						throw new EmotionNotFoundInSaveException(
								EmotionNotFoundInSaveException.STANDARD_CAUSE
										+ line);
					}
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Verified Save.", GamsionLogger.DEBUG));
	}

	/**
	 * Loads the save into the EmotionModule.
	 * 
	 * @throws EmotionNotFoundInSaveException
	 */
	public void loadSave() throws EmotionNotFoundInSaveException {
		try {
			FileReader fr = new FileReader(savefile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			boolean found = false;
			// Iterate the save file
			while ((line = br.readLine()) != null) {
				if ((!line.equals("")) && !line.equals(System.lineSeparator())) {
					String subinfo = line.substring(0, line.indexOf("="));
					String info = line.substring(line.indexOf("=") + 1);
					Collection<STDEmotion> c = emotions.values();
					Iterator<STDEmotion> it = c.iterator();
					while (it.hasNext()) {
						STDEmotion emotion = it.next();
						if (emotion.getPentID().equals(subinfo)) {
							emotion.setValue(Integer.parseInt(info));
							emotions.put(emotion.getPentID(), emotion);
							found = true;
						}

					}
					if (!found) {
						br.close();
						throw new EmotionNotFoundInSaveException(
								EmotionNotFoundInSaveException.STANDARD_CAUSE
										+ line);
					}
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Saves were loaded.", GamsionLogger.DEBUG));

	}

	/**
	 * Saves the emotions into the save file.
	 */
	public void save() {
		Iterator<String> it = emotions.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			writeEmotion(savefile, emotions.get(key), -1,
					emotions.containsKey(key));
		}
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions were saved.", GamsionLogger.DEBUG));
	}

	/**
	 * @param save_location
	 *            - What save to use. <b>Must be absolute.</b>
	 */
	public EmotionModule(String save_location) {
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "EmotionModule is initializing.",
				GamsionLogger.DEBUG));
		this.setSave_location(save_location);
		loadEmotions();

		try {
			verifySave();
			loadSave();
			System.out.println("save loaded");

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

	// Why do the droids say this out loud in Star Wars Battlefront II during
	// space battles SO MUCH!?
	@Override
	public void shutDown() {
		save();
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), getName() + " has been shutdown.",
				GamsionLogger.DEBUG));
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
	public static void writeEmotion(File f, STDEmotion em, int value,
			boolean overwrite) {
		GamsionLogger.globalLogFileAdd(new Log(LogFile.getLogDateFormat()
				.format(new Date()), "Static Emotion Module", em.getPentID()
				+ " is being overwritten with the value " + value
				+ "to the file " + f.getAbsolutePath()
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

	}

	/**
	 * @return - returns the current Map within EmotionModule
	 */
	public Map<String, STDEmotion> getEmotionList() {

		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Emotions list was gotten.", GamsionLogger.DEBUG));
		return emotions;

	}

	/**
	 * @param pentID
	 *            - pentID to find the emotion
	 * @return - returns the emotion within the map you requested. It uses
	 *         generic so you do not need to cast. <b>Safe casting is not
	 *         guaranteed.</b>
	 */
	public <T extends STDEmotion> T getEmotionUnsafe(String pentID) {
		@SuppressWarnings("unchecked")
		T emotion = (T) emotions.get(pentID);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Unsafe Emotion was taken:", GamsionLogger.DEBUG));
		return emotion;
	}

	/**
	 * @param pentID
	 *            - pentID to find emotion
	 * @return - returns the emotion within the map you requested. Cast it into
	 *         the needed emotion.
	 */
	public STDEmotion getEmotionSafe(String pentID) {
		STDEmotion emotion = emotions.get(pentID);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), "Safe Emotion was gotten: " + pentID,
				GamsionLogger.DEBUG));
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
	public boolean setEmotion(String pentID, int value) {
		if (!emotions.containsKey(pentID))
			return false;
		STDEmotion em = emotions.get(pentID);
		em.setValue(value);
		emotions.put(pentID, em);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), em.getName() + " was set to " + em.getValue(),
				GamsionLogger.DEBUG));
		return true;
	}

	/**
	 * @param pentID
	 *            - pentID of the emotion.
	 * @param value
	 *            - value to increment the emotion.
	 * @return - if the emotion is not found, returns false. Otherwise, true.
	 */
	public boolean incrementEmotion(String pentID, int value) {
		if (!emotions.containsKey(pentID))
			return false;
		STDEmotion em = emotions.get(pentID);
		em.incrementValue(value);
		emotions.put(pentID, em);
		logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
				getName(), em.getName() + "was increased by " + value
						+ " new value: " + em.getValue(), GamsionLogger.DEBUG));
		return true;
	}

	/**
	 * @param pentIDp
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
	public void randomize(String pentIDp, int factor) {
		if (pentIDp == null) {
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), "Emotions were randomized.", GamsionLogger.DEBUG));
			Iterator<String> it = emotions.keySet().iterator();
			while (it.hasNext()) {
				String pentID = it.next();
				incrementEmotion(pentID, (r.nextInt(factor + 1) - factor / 2));
			}
		} else {
			incrementEmotion(pentIDp, (r.nextInt(factor + 1) - factor / 2));
			logFile.add(new Log(LogFile.getLogDateFormat().format(new Date()),
					getName(), pentIDp + "was randomized.", GamsionLogger.DEBUG));
		}
	}

	public void setEmotionList(Map<String, STDEmotion> eml) {
		this.emotions = eml;
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
		EmotionModule em = new EmotionModule(this.save_location);
		HashMap<String, STDEmotion> tempEmotionList = new HashMap<String, STDEmotion>();
		tempEmotionList.putAll(emotions);
		em.emotions = tempEmotionList;
		em.logFile = this.readLog();

		return em;
	}

}

// I would like to thank all of my nonexistant fans out there! YOU GUYS ROCK!
// Time to party in my head.
// Oh. and Sheogorath says hi.

