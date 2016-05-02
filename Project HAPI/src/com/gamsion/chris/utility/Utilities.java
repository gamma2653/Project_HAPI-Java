package com.gamsion.chris.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.gamsion.chris.EmotionModule.emotions.STDEmotion;
import com.gamsion.chris.PersonalityModule.traits.STDTrait;

public class Utilities {
	public static List<Class<?>> getFilesAsClass(String path) {
		File filePath = new File(path);
		List<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			for (File f : filePath.listFiles()) {
				if (!f.getName().substring(0, 3).equalsIgnoreCase("STD")) {

					String workingPath = f.getAbsolutePath().replaceAll(
							Matcher.quoteReplacement(File.separator), ".");
					classes.add(Class.forName(workingPath.substring(
							workingPath.indexOf("com"),
							workingPath.indexOf("class") - 1)));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return classes;
	}

	public static List<STDEmotion> getClassesAsEmotions(List<Class<?>> classes) {
		List<STDEmotion> emotions = new ArrayList<STDEmotion>();
		try {
			for (Class<?> c : classes) {
				if (!c.getName().substring(0, 3).equalsIgnoreCase("STD")) {
					emotions.add((STDEmotion) c.newInstance());
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return emotions;
	}

	public static List<STDTrait> getClassesAsTraits(List<Class<?>> classes) {
		List<STDTrait> traits = new ArrayList<STDTrait>();
		try {
			for (Class<?> c : classes) {
				traits.add((STDTrait) c.newInstance());
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return traits;
	}
}
