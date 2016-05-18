package com.gamsion.chris.utility.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamsion.chris.EmotionModule.emotions.STDEmotion;
import com.gamsion.chris.utility.Utilities;

public class SQLStatics {
	static final String JDBC_DRIVER = "org.sqlite.JDBC";
	static final String DB_URL = "jdbc:sqlite:saves//projecthapi.dl";

	public static void saveEmotions(Map<String, STDEmotion> emotions) {
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
				statement.execute("");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Map<String, STDEmotion> emotions = new HashMap<String, STDEmotion>();
		List<STDEmotion> emlist = Utilities
				.getClassesAsEmotions(Utilities
						.getFilesAsClass("C:/Users/John/git/Project HAPI/Project HAPI/bin/com/gamsion/chris/EmotionModule/emotions"));
		for(STDEmotion e : emlist){
			emotions.put(e.getName(), e);
		}
		SQLStatics.saveEmotions(emotions);
		String[] things = {"", ""};
		
	}
}
