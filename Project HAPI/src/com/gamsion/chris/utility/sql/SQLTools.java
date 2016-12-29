package com.gamsion.chris.utility.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.EmotionModule.emotions.Emotion;
import com.gamsion.chris.EmotionModule.emotions.EmotionType;

public class SQLTools {
	static final String JDBC_DRIVER = "org.sqlite.JDBC";
	static final String DB_URL = "jdbc:sqlite:";
	Connection connection = null;
	Statement statement = null;
	public SQLTools(String location){
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL+location);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.execute(
					"create table if not exists emotions(ID integer primary key autoincrement, nameid varchar (255) not null, admir int, amaze int, grief int, happy int, loath int, grage int, terro int, vigil int);");
			connection.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean verify(String name){
		ResultSet result = null;
		try{
//			result = statement.executeQuery(String.format("select 1 from emotions where nameid=%s",name));
//			if(result == null){
//				return false;
//			}else if(!result.getBoolean(1)){
//				return false;
//			}
			result = statement.executeQuery("select 1 from emotions");
			System.out.println(result.getMetaData().getColumnLabel(1));
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return true;
	}

	/**
	 * <p>
	 * Saves a map of emotions into the database.
	 * </p>
	 * 
	 * @param name
	 * @param emotions
	 */
	public void saveEmotions(String name, Map<EmotionType, Emotion> emotions) {

		try {
			statement.execute(String.format(
					"insert into emotions (nameid, admir, amaze, grief, happy, loath, grage, terro, vigil) values ('%s', %f, %f, %f, %f, %f, %f, %f, %f);",
					name, emotions.get(EmotionType.admiration).getValue(),
					emotions.get(EmotionType.amazement).getValue(), emotions.get(EmotionType.grief).getValue(),
					emotions.get(EmotionType.happiness).getValue(), emotions.get(EmotionType.loathing).getValue(),
					emotions.get(EmotionType.rage).getValue(), emotions.get(EmotionType.terror).getValue(),
					emotions.get(EmotionType.vigilance).getValue()));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void saveEmotion(String name, AbstractMap.SimpleEntry<EmotionType, Emotion> emotion){
		try {
			statement.execute(String.format("UPDATE emotions SET %s = %f", enumToKey(emotion.getKey()), emotion.getValue().getValue()));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Returns all values from database.
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, Map<EmotionType, Emotion>> loadEmotions() {
		Map<String, Map<EmotionType, Emotion>> emotions = new HashMap<String, Map<EmotionType, Emotion>>();

		try {
			// Select all emotion values
			ResultSet rs = statement.executeQuery("select * from emotions");
			while (rs.next()) {

				int columns = rs.getMetaData().getColumnCount();
				Map<String, String> data = new HashMap<String, String>();
				for (int i = 0; i < columns; i++) {
					data.put(rs.getMetaData().getColumnName(i + 1), rs.getString(i + 1));
				}
				Map<EmotionType, Emotion> tempEm = new HashMap<EmotionType, Emotion>();
				// Get all emotions
				tempEm.put(EmotionType.admiration, new Emotion(EmotionType.admiration));
				tempEm.get(EmotionType.admiration).setValue(Integer.parseInt(data.get("admir")));
				tempEm.put(EmotionType.amazement, new Emotion(EmotionType.amazement));
				tempEm.get(EmotionType.amazement).setValue(Integer.parseInt(data.get("amaze")));
				tempEm.put(EmotionType.grief, new Emotion(EmotionType.grief));
				tempEm.get(EmotionType.grief).setValue(Integer.parseInt(data.get("grief")));
				tempEm.put(EmotionType.happiness, new Emotion(EmotionType.happiness));
				tempEm.get(EmotionType.happiness).setValue(Integer.parseInt(data.get("happy")));
				tempEm.put(EmotionType.loathing, new Emotion(EmotionType.loathing));
				tempEm.get(EmotionType.loathing).setValue(Integer.parseInt(data.get("loath")));
				tempEm.put(EmotionType.rage, new Emotion(EmotionType.rage));
				tempEm.get(EmotionType.rage).setValue(Integer.parseInt(data.get("grage")));
				tempEm.put(EmotionType.terror, new Emotion(EmotionType.terror));
				tempEm.get(EmotionType.terror).setValue(Integer.parseInt(data.get("terro")));
				tempEm.put(EmotionType.vigilance, new Emotion(EmotionType.vigilance));
				tempEm.get(EmotionType.vigilance).setValue(Integer.parseInt(data.get("vigil")));
				emotions.put(rs.getString(2), tempEm);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emotions;
	}

	public Map<EmotionType, Emotion> getEmotions(String name) {
		Map<EmotionType, Emotion> emotions = new HashMap<EmotionType, Emotion>();

		try {
			// Select all emotion values
			ResultSet rs = statement.executeQuery(String.format("select * from emotions", name));
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				if (rs.getString(2).equalsIgnoreCase(name)) {
					Map<String, String> data = new HashMap<String, String>();
					for (int i = 0; i < columns; i++) {
						data.put(rs.getMetaData().getColumnName(i + 1), rs.getString(i + 1));
					}
					// Get all emotions
					emotions.put(EmotionType.admiration, new Emotion(EmotionType.admiration));
					emotions.get(EmotionType.admiration).setValue(Integer.parseInt(data.get("admir")));
					emotions.put(EmotionType.amazement, new Emotion(EmotionType.amazement));
					emotions.get(EmotionType.amazement).setValue(Integer.parseInt(data.get("amaze")));
					emotions.put(EmotionType.grief, new Emotion(EmotionType.grief));
					emotions.get(EmotionType.grief).setValue(Integer.parseInt(data.get("grief")));
					emotions.put(EmotionType.happiness, new Emotion(EmotionType.happiness));
					emotions.get(EmotionType.happiness).setValue(Integer.parseInt(data.get("happy")));
					emotions.put(EmotionType.loathing, new Emotion(EmotionType.loathing));
					emotions.get(EmotionType.loathing).setValue(Integer.parseInt(data.get("loath")));
					emotions.put(EmotionType.rage, new Emotion(EmotionType.rage));
					emotions.get(EmotionType.rage).setValue(Integer.parseInt(data.get("grage")));
					emotions.put(EmotionType.terror, new Emotion(EmotionType.terror));
					emotions.get(EmotionType.terror).setValue(Integer.parseInt(data.get("terro")));
					emotions.put(EmotionType.vigilance, new Emotion(EmotionType.vigilance));
					emotions.get(EmotionType.vigilance).setValue(Integer.parseInt(data.get("vigil")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(emotions.isEmpty()){
			throw new RuntimeException(String.format("%s cannot be found in the save database.", name));
		}
		return emotions;
	}

	/**<p>
	 * Resets table.
	 * </p>
	 */
	public void clearData() {
		try {
			statement.execute("drop table emotions");
			statement.execute(
					"create table if not exists emotions(ID integer primary key autoincrement, nameid varchar (255) not null, admir int, amaze int, grief int, happy int, loath int, grage int, terro int, vigil int);");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Debug/Testing Method
	public static void main(String[] args) {
		SQLTools sql = new SQLTools("saves//projecthapi.db");
		sql.clearData();
		Map<EmotionType, Emotion> emotions = new HashMap<EmotionType, Emotion>();
		emotions.put(EmotionType.admiration, new Emotion(EmotionType.admiration));
		Emotion dif = new Emotion(EmotionType.amazement);
		dif.setValue(538296);
		emotions.put(EmotionType.amazement, dif);
		emotions.put(EmotionType.grief, new Emotion(EmotionType.grief));
		emotions.put(EmotionType.happiness, new Emotion(EmotionType.happiness));
		emotions.put(EmotionType.loathing, new Emotion(EmotionType.loathing));
		emotions.put(EmotionType.rage, new Emotion(EmotionType.rage));
		emotions.put(EmotionType.terror, new Emotion(EmotionType.terror));
		emotions.put(EmotionType.vigilance, new Emotion(EmotionType.vigilance));
		System.out.println(emotions);
		sql.saveEmotions("gamma", emotions);
		System.out.println(sql.loadEmotions());
		Emotion em = new Emotion(EmotionType.loathing);
		em.setValue(9001);
		sql.saveEmotion("gamma", new AbstractMap.SimpleEntry<EmotionType, Emotion>(em.getType(), em));
		System.out.println(sql.loadEmotions());
		System.out.println(sql.verify("gamma"));
		System.out.println(sql.verify("gamma2626"));

	}
	
	/**
	 * @param emT
	 * @return
	 */
	public static String enumToKey(EmotionType emT){
		String s = "";
		switch(emT){
		case admiration:
			s = "admir";
			break;
		case amazement:
			s = "amaze";
			break;
		case grief:
			s = "grief";
			break;
		case happiness:
			s = "happy";
			break;
		case loathing:
			s = "loath";
			break;
		case rage:
			s = "grage";
			break;
		case terror:
			s = "terro";
			break;
		case vigilance:
			s = "vigil";
			break;
		default:
			throw new RuntimeException("Unspecified enum.");
		}
		return s;
	}
	public static EmotionType keyToEnum(String emT){
		EmotionType s;
		switch(emT){
		case "admir":
			s = EmotionType.admiration;
			break;
		case "amaze":
			s = EmotionType.amazement;
			break;
		case "grief":
			s = EmotionType.grief;
			break;
		case "happy":
			s = EmotionType.happiness;
			break;
		case "loath":
			s = EmotionType.loathing;
			break;
		case "grage":
			s = EmotionType.rage;
			break;
		case "terro":
			s = EmotionType.terror;
			break;
		case "vigil":
			s = EmotionType.vigilance;
			break;
		default:
			throw new RuntimeException("Unspecified enum.");
		}
		return s;
	}
}
