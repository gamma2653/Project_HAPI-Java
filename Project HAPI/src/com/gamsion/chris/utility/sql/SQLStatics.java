package com.gamsion.chris.utility.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.gamsion.chris.EmotionModule.emotions.Amazement;
import com.gamsion.chris.EmotionModule.emotions.STDEmotion;

public class SQLStatics {
	static final String JDBC_DRIVER = "org.sqlite.JDBC";
	static final String DB_URL = "jdbc:sqlite:saves//projecthapi.dl";

	public static void saveEmotions(String name,
			Map<String, STDEmotion> emotions) {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			for (String s : emotions.keySet()) {
				statement
						.execute("create table if not exists emotions(ID int not null auto_increment, nameid varchar (255) not null, admir int, amaze int, grief int, happy int, loath int, grage int, terro int, vigil int, primary key(id)");

				statement
						.execute(String
								.format("insert into employees (nameid, admir, amaze, grief, happy, loath, grage, terro, vigil) values (%s, %d, %d, %d, %d, %d, %d, %d, %d);",
										name, emotions.get("admir"),
										emotions.get("amaze"),
										emotions.get("grief"),
										emotions.get("happy"),
										emotions.get("loath"),
										emotions.get("grage"),
										emotions.get("terro"),
										emotions.get("vigil")));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		STDEmotion standard = new Amazement(500000);
		Map<String, Emotion> emotions = new HashMap<String, Emotion>();
		emotions.put("admir", standard.clone());
		emotions.put("amaze", standard.clone());
		emotions.put("grief", standard.clone());
		emotions.put("happy", standard.clone());
		emotions.put("loath", standard.clone());
		emotions.put("grage", standard.clone());
		emotions.put("terro", standard.clone());
		emotions.put("vigil", standard.clone());
		

	}
}
