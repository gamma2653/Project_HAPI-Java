package com.gamsion.chris.utility;

import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.EmotionModule.emotions.STDEmotion;

public class EmotionUtilities {
	public static void getDefaultEmotionMap(){
		Map<String, STDEmotion> em = new HashMap<String, STDEmotion>();
		for(STDEmotion e : Utilities.getClassesAsEmotions(Utilities.getFilesAsClass("C:/Users/John/git/Project HAPI/Project HAPI/bin/com/gamsion/chris/EmotionModule/emotions"))){
			em.put(e.getPentID(), e);
		}
	}
}
