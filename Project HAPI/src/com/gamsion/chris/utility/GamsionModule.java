package com.gamsion.chris.utility;

import com.gamsion.chris.utility.log.LogFile;

/**
 * This is the standard interface for all Gamsion modules. Things may update as
 * they should become standardized.
 * 
 * @author <b>gamma2626</b> a.k.a. Christopher De Jesus
 *
 */
public interface GamsionModule{
	/**
	 * The display name.
	 * 
	 * @return - Of course as a <b>String</b> object.
	 */
	public String getName();

	/**
	 * <p>
	 * This is the unique name. Kind of like an ID so that it doesn't get mixed
	 * up with other modules with similar names.
	 * </p>
	 * <p>
	 * Try not to have this name change much.
	 * </p>
	 * 
	 * @return - Of course as a <b>String</b> object.
	 */
	public String getUName();

	/**
	 * This is the description... If you have information about your mod, it's
	 * here.
	 * 
	 * @return - Of course as a <b>String</b> object.
	 */
	public String getDescription();

	/**
	 * Shuts down the Module.
	 * <p>
	 * Every module should have a built in shutdown protocol. Saving, etc,
	 * should be here too.
	 * </p>
	 */
	public void shutDown();

	/**
	 * Every module should also state it's version.
	 * 
	 * @return - Of course as a <b>String</b> object.
	 */
	public String getVersion();

	/**
	 * Every module must have a method that tells whether it's LogFile buffer is
	 * full.
	 * 
	 * @return A boolean value, true if there are any items in the List.
	 */
	public boolean hasLog();

	/**
	 * <p>
	 * Every module should have a LogFile. The LogFile is to be the Log which
	 * will be read by the Logger. When read by the Logger, it may be deleted
	 * immediately after read. (Only a suggestion, not enforced by API).
	 * </p>
	 * 
	 * @return - Should be a LogFile.
	 */
	public LogFile readLog();
	/**
	 * You should reset the logFile, preferably by calling the .clear() method of the object.
	 */
	public void resetLog();

	public GamsionModule clone();
}
