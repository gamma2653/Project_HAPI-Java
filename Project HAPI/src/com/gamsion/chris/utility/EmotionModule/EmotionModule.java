package com.gamsion.chris.utility.EmotionModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gamsion.chris.resources.exceptions.EmotionModule.EmotionModuleFileException;
import com.gamsion.chris.resources.exceptions.EmotionModule.EmotionNotFoundInSaveException;
import com.gamsion.chris.resources.exceptions.EmotionModule.EmotionValueOutOfBoundsException;
import com.gamsion.chris.resources.exceptions.EmotionModule.SuccessfulSaveCreationException;
import com.gamsion.chris.utility.EmotionModule.emotions.STDEmotion;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.module.GamsionModule;

;

/**
 * The purpose of this class is to create an instance of Emotions. From there
 * you are able to manipulate the emotions depending on your program. It is also
 * made to be as dynamic as possible
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 */
public class EmotionModule implements GamsionModule {
	private List<String> myLog = new ArrayList<String>();
	// Global Random generator object.
	public final Random r = new Random();
	// Here are all the emotions to be used
	private Map<String, STDEmotion> emotions = null;

	private File savefile;
	private String save_location;
	@Override
	public boolean hasLog(){
		if(myLog.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public List<String> readLog(){
		ArrayList<String> theList= new ArrayList<String>();
		theList.addAll(myLog);
		myLog.clear();
		return theList;
	}
	
	
	
	
	
	
	/**
	 * This loads the emotions into the Module. May be invoked after initial
	 * instantiation to update the emotion list.
	 */
	public void loadEmotions() {
		List<STDEmotion> list = new ArrayList<STDEmotion>();
		list = LoadEmotions
				.loadEmotions("C:/Users/John/Documents/School/Programming/Game Design/workspace/Judgement/bin/com/gamsion/chris/utility/EmotionModule/emotions");
		Iterator<STDEmotion> it = list.iterator();
		emotions = new HashMap<String, STDEmotion>();
		while (it.hasNext()) {
			STDEmotion em = it.next();
			emotions.put(em.getPentID(), em);

		}
		myLog.add(GamsionLogger.DEBUG + "Emotions were loaded.");

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
		}myLog.add(GamsionLogger.DEBUG + "Verified Save.");
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
		myLog.add(GamsionLogger.DEBUG + "Save was loaded.");

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
		myLog.add(GamsionLogger.DEBUG + "Emotions were saved.");
	}

	/**
	 * @param save_location
	 *            - What save to use. <b>Must be absolute.</b>
	 */
	public EmotionModule(String save_location) {
		myLog.add(GamsionLogger.DEBUG + "EmotionModule is initializing.");
		this.setSave_location(save_location);
		loadEmotions();

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
		myLog.add(GamsionLogger.DEBUG + "EmotionModule has been initialized.");

	}

	// Why do the droids say this out loud in Star Wars Battlefront II during
	// space battles SO MUCH!?
	@Override
	public void shutDown() {
		save();
		myLog.add(GamsionLogger.DEBUG + "Emotions were shutdown.");
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

	}

	/**
	 * @return - returns the current Map within EmotionModule
	 */
	public Map<String, STDEmotion> getEmotionList() {
		myLog.add(GamsionLogger.DEBUG + "Emotions list was gotten.");
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
		myLog.add(GamsionLogger.DEBUG + "Unsafe Emotion was gotten: " + pentID);
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
		myLog.add(GamsionLogger.DEBUG + "Safe Emotion was gotten: " + pentID);
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
		myLog.add(GamsionLogger.DEBUG + em.getName() + " was set to " + em.getValue());
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
		myLog.add(GamsionLogger.DEBUG + em.getName() + "was increased by " + value + " new value: " + em.getValue());
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
			myLog.add(GamsionLogger.DEBUG + "Emotions were randomized.");
			Iterator<String> it = emotions.keySet().iterator();
			while (it.hasNext()) {
				String pentID = it.next();
				incrementEmotion(pentID, (r.nextInt(factor + 1) - factor / 2));
			}
		} else {
			incrementEmotion(pentIDp, (r.nextInt(factor + 1) - factor / 2));
			myLog.add(GamsionLogger.DEBUG + pentIDp + "was randomized.");
		}
	}

	public static void main(String[] args) {
		EmotionModule emotionlist = new EmotionModule(
				"C:\\Users\\John\\Desktop\\save\\example2.txt");
		
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
		myLog.add(GamsionLogger.DEBUG + "Save was gotten.");
		return save_location;
	}

	/**
	 * Sets the save_location to the desired path. (Automatically updates
	 * savefile).
	 * 
	 * @param save_location
	 */
	public void setSave_location(String save_location) {
		myLog.add(GamsionLogger.DEBUG + "Save was set to a new value. Old Value: " + this.save_location + " New Value: " + save_location);
		this.save_location = save_location;
		savefile = new File(save_location);
	}
}

// I would like to thank all of my nonexistant fans out there! YOU GUYS ROCK!
// Time to party in my head.
// Oh. and Sheogorath says hi.

