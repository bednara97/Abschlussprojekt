package com.bednara.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionHandler {

	private static Connection connection = null;

	public static void connectToServer() throws IOException, SQLException {
		List<String> loginList = readLoginInfo();

		// jdbc:mysql://localhost:3306/abschlussprojekt, root, password
		connection = DriverManager.getConnection(loginList.get(0), loginList.get(1), loginList.get(2));
	}

	public static List<String> readLoginInfo() throws IOException {
		InputStream inputStream = DBConnectionHandler.class.getResource("/com/bednara/dbconnection.properties")
				.openStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		List<String> myList = new ArrayList<String>();

		try (BufferedReader myReader = new BufferedReader(inputStreamReader)) {
			String line = null;
			while ((line = myReader.readLine()) != null) {
				String[] value = line.split("=");
				String dbInfo = value[1];
				myList.add(dbInfo);
			}
		}

		return myList;
	}

	public static Connection getConnection() {
		return connection;
	}

}
