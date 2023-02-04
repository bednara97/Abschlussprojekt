package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bednara.application.DBConnectionHandler;
import com.bednara.application.Main;

public class DashboardSQLHandler {

	private static Connection connection = DBConnectionHandler.getConnection();

	public static String getEmployeeFirstNameFromDatabase(String userName) {
		String sqlGetFirstNameStatement = "SELECT FIRST_NAME FROM EMPLOYEE e JOIN PERSON p ON p.ID_NUMBER = e.ID_NUMBER WHERE e.USER_NAME = ?";
		String firstName = "";

		try {
			PreparedStatement myPreparedStatement = connection.prepareStatement(sqlGetFirstNameStatement);
			myPreparedStatement.setString(1, userName);
			ResultSet resultSet = myPreparedStatement.executeQuery();

			while (resultSet.next()) {
				firstName = (Main.UserDataHolder.userFirstName = resultSet.getString("FIRST_NAME"));
			}

			return Main.UserDataHolder.userFirstName;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return firstName;

	}

	public static String getExpectedArrivalsFromDatabase() {
		String expectedArrivals = "";
		String sqlGetExpectedArrivalsStatement = "SELECT COUNT(DATE_FORMAT(r.ARRIVAL_DATE, '%Y-%m-%d')) AS DATUM FROM reservation r  WHERE DATE_FORMAT(r.ARRIVAL_DATE, '%Y-%m-%d') LIKE DATE_FORMAT(NOW(), '%Y-%m-%d');";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetExpectedArrivalsStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				expectedArrivals = resultSet.getString("DATUM");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return expectedArrivals;
	}

	public static String getExpectedDeparturesFromDatabase() {
		String expectedDepartures = "";
		String sqlGetExpectedDeparturesStatement = "SELECT COUNT(DATE_FORMAT(r.DEPARTURE_DATE, '%Y-%m-%d')) AS DATUM FROM reservation r  WHERE DATE_FORMAT(r.DEPARTURE_DATE, '%Y-%m-%d') LIKE DATE_FORMAT(NOW(), '%Y-%m-%d');";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetExpectedDeparturesStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				expectedDepartures = resultSet.getString("DATUM");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return expectedDepartures;
	}

	public static String getOccupiedRoomsFromDatabase() {
		String occupiedRooms = "";
		String sqlGetOccupiedRoomsStatement = "SELECT COUNT(ROOM_STATUS) AS ROOM_STATUS	FROM ROOM ro JOIN RESERVATION r ON r.ROOM_ID = ro.ROOM_ID "
				+
				"WHERE ro.ROOM_STATUS = 'OCCUPIED' AND DATE(CURRENT_DATE) BETWEEN DATE(r.ARRIVAL_DATE) AND DATE(r.DEPARTURE_DATE);";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetOccupiedRoomsStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				occupiedRooms = resultSet.getString("ROOM_STATUS");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return occupiedRooms;
	}

	public static String getCleanRoomsFromDatabase() {
		String cleanRooms = "";
		String sqlGetCleanRoomsStatement = "SELECT COUNT(ROOM_STATUS) AS ROOM_STATUS FROM ROOM ro WHERE ro.ROOM_STATUS = 'CLEAN'";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetCleanRoomsStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				cleanRooms = resultSet.getString("ROOM_STATUS");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cleanRooms;
	}

	public static String getAvailableRoomsFromDatabase() {
		String availableRooms = "";
		String sqlGetAvailableRoomsStatement = "SELECT COUNT(ROOM_STATUS) AS ROOM_STATUS FROM ROOM ro LEFT JOIN RESERVATION r ON r.ROOM_ID = ro.ROOM_ID "
				+
				"WHERE ro.ROOM_STATUS = 'CLEAN' AND r.CONFIRMATION_NUMBER IS NULL;";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetAvailableRoomsStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				availableRooms = resultSet.getString("ROOM_STATUS");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return availableRooms;
	}

	public static String getOutOfOrderRoomsFromDatabase() {
		String outOfOrderRooms = "";
		String sqlGetOutOfOrderRoomsStatement = "SELECT COUNT(ROOM_STATUS) AS ROOM_STATUS FROM ROOM ro WHERE ro.ROOM_STATUS = 'OUT_OF_ORDER'";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetOutOfOrderRoomsStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				outOfOrderRooms = resultSet.getString("ROOM_STATUS");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return outOfOrderRooms;
	}

}