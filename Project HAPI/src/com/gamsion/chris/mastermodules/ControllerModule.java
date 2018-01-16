package com.gamsion.chris.mastermodules;

import java.text.DecimalFormat;

import com.gamsion.chris.EmotionModule.EmotionModule;
import com.gamsion.chris.PersonalityModule.PersonalityModule;
import com.gamsion.chris.utility.GamsionModule;
import com.gamsion.chris.utility.UniqueModule;
import com.gamsion.chris.utility.log.GamsionLogger;
import com.gamsion.chris.utility.log.LogFile;
import com.gamsion.chris.utility.log.LogUtilities;

/**
 * This class is used to create objects that contain all of the modules
 * necessary to have an entity act. It could also be called a Person module.
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 *
 */
public class ControllerModule implements GamsionModule, UniqueModule, Cloneable {
	protected String idName;
	protected EmotionModule emotion = new EmotionModule(idName, "C:\\Users\\John\\Desktop\\save\\example2.txt");
	protected PersonalityModule personality;
	protected LogFile logFile = new LogFile(getName(), null);

	public ControllerModule(String idName, double agreeableness, double conscientousness, double extraversion,
			double neuroticism, double openness) {
		this.idName = idName;
		this.personality = new PersonalityModule(idName, agreeableness, conscientousness, extraversion, neuroticism,
				openness);
	}

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
		emotion.shutDown();
		personality.shutDown();
		logFile.add(LogUtilities.getDefaultLogShutdown(getName()));
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public boolean hasLog() {
		return !logFile.isEmpty() || this.emotion.hasLog() || this.personality.hasLog();
	}

	@Override
	public LogFile readLog() {
		// Use com.gamsion.chris.utility.log.LogFile (extends ArrayList<String>)
		// to store logs.
		LogFile lf = new LogFile(getName(), null);
		lf.addAll(this.logFile);
		lf.addAll(emotion.readLog());
		lf.addAll(personality.readLog());
		this.resetLog();
		return lf;
	}

	@Override
	public void resetLog() {
		this.logFile.clear();
		this.emotion.resetLog();
		this.personality.resetLog();

	}

	/**
	 * @return - A reference to the emotionModule
	 */
	public EmotionModule getEmotion() {
		return this.emotion;
	}

	public PersonalityModule getPersonality() {
		return this.personality;
	}

	/**
	 * @param action
	 * @param magnitude
	 *            - Out of a scale of 100000
	 */
	public void process(Action action, double magnitude) {
		DecimalFormat df = new DecimalFormat("0.000");
		double personalityMod1;
		double personalityMod2;
		double personalityMod3;
		double personalityMod4;
		double personalityMod5;
		double personalityMod;
		double value;
		switch (action) {
		case COMPLIMENT:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .2);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .15);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .5);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .05);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 10);
			break;
		case ADMIRE:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * 0.2);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * 0.1);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * 0.4);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * 0.2);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 5);
			break;
		case CHEER:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .2);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .5);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .1);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 10);
			break;
		case CARE:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .2);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .15);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .3);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .25);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 10);
			break;
		case APPROVE:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.3);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .2);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .3);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .1);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 10);
			break;
		case NEUTRAL:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .05);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .15);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .2);
			personalityMod5 = (0.001 * personality.getTrait("openness").getNValue() * .5);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(-value / 2);
			emotion.vigilance.incrementValue(-value / 10);
			break;
		case DISREGARD:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * .1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .2);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .5);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .1);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(-value);
			emotion.admiration.incrementValue(-value / 2);
			emotion.rage.incrementValue(value / 2);
			emotion.vigilance.incrementValue(value / 10);
			break;
		case JOKE:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * 0.1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .3);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .25);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .25);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(value / 2);
			emotion.admiration.incrementValue(value / 2);
			emotion.rage.incrementValue(value / 50);
			emotion.vigilance.incrementValue(-value / 2);
			break;
		case LOOKDOWN:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * 0.1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.2);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .1);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .5);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .1);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(-value);
			emotion.admiration.incrementValue(-value / 2);
			emotion.rage.incrementValue(value / 10);
			emotion.vigilance.incrementValue(-value / 2);
			break;
		case OFFEND:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * 0.1);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.2);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .1);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .5);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .1);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(-value);
			emotion.admiration.incrementValue(-value / 2);
			emotion.rage.incrementValue(value / 10);
			emotion.vigilance.incrementValue(-value / 5);
			break;
		case DESPISE:
			personalityMod1 = (0.001 * personality.getTrait("agreeableness").getValue() * 0.05);
			personalityMod2 = (0.001 * personality.getTrait("conscientousness").getNValue() * 0.1);
			personalityMod3 = (0.001 * personality.getTrait("extraversion").getValue() * .1);
			personalityMod4 = (0.001 * personality.getTrait("neuroticism").getNValue() * .7);
			personalityMod5 = (0.001 * personality.getTrait("openness").getValue() * .05);
			personalityMod = (personalityMod1 + personalityMod2 + personalityMod3 + personalityMod4 + personalityMod5)
					/ 50;
			logFile.log(this, String.format(
					"New Process being processed by %s.\nmod1 = %s, mod2 = %s, mod3 = %s, mod4 = %s, mod5 = %s, mod = %s",
					this.idName, df.format(personalityMod1), df.format(personalityMod2), df.format(personalityMod3),
					df.format(personalityMod4), df.format(personalityMod5), df.format(personalityMod)),
					GamsionLogger.DEBUG);
			value = magnitude * personalityMod;
			emotion.ecstasy.incrementValue(-value);
			emotion.admiration.incrementValue(-value);
			emotion.rage.incrementValue(value / 2);
			emotion.vigilance.incrementValue(value / 2);
			break;
		default:
			break;
		}
	}

	@Override
	public ControllerModule clone() {
		ControllerModule cm;
		try {
			cm = (ControllerModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		cm.emotion = this.getEmotion().clone();
		cm.personality = this.getPersonality().clone();
		cm.logFile = this.logFile.clone();
		return cm;
	}

	public static void main(String[] args) {
		ControllerModule cm = new ControllerModule("Mirkelis", 25000, 70000, 45000, 45000, 40000);
		cm.process(Action.CHEER, 10000);
		System.out.println(cm.getEmotion().getEmotionMap());
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
