package com.gamsion.chris.utility.EmotionModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gamsion.chris.utility.EmotionModule.emotions.*;

/**
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 *
 */
public class LoadEmotions {
	/**
	 * Loads all the emotions into one List of the STDEmotion superclass.
	 * @param dir - the file path to the folder with the emotions
	 * @return
	 */
	public static List<STDEmotion> loadEmotions(String dir){
		List<File> fileA = null;
		List<STDEmotion> em = new ArrayList<STDEmotion>();
		fileA = new ArrayList<File>(Arrays.asList(new File(dir).listFiles()));
		fileA.remove(new File(dir+"/STDEmotion.class"));
		

		//cycle through the files and add the emotions to the List
		for(File f : fileA){
			
			String strb = f.getAbsolutePath();
			strb = strb.substring(strb.indexOf("com\\gamsion\\chris\\utility\\EmotionModule\\emotions"));
			strb = strb.replaceAll("\\\\", ".");
			strb = strb.replaceAll(".class", "");
			
			try {
				em.add((STDEmotion)Class.forName(strb).newInstance());
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		
		return em;
	}public static void main(String[] args){
		List<STDEmotion> emotions = loadEmotions("C:\\Users\\John\\Documents\\School\\Programming\\Game Design\\workspace\\Judgement\\bin\\com\\gamsion\\chris\\utility\\Emotion\\emotions");
		for(int i = 0; i<emotions.size(); i++){
            System.out.println(emotions.get(i).getPentID());
		}
		
	}
}

