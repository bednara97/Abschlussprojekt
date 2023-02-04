package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bednara.application.DBConnectionHandler;

public class AdvancedSearchSQLHandler {

	private static Connection connection = DBConnectionHandler.getConnection();

	public static List<String> searchForAdvancedDetails(List<String> detailsForAdvancedSearch) {

		String sqlGetDetailsForAdvancedSearchStatement = "SELECT r.CONFIRMATION_NUMBER, p.LAST_NAME, p.FIRST_NAME, r.ROOM_NUMBER, ro.ROOM_CATEGORY, ro.ROOM_STATUS, r.ARRIVAL_DATE, r.DEPARTURE_DATE, r.PAYMENT_TYPE, r.RESERVATION_STATUS "
				+
				"FROM PERSON p JOIN GUEST g ON p.ID_NUMBER = g.ID_NUMBER JOIN RESERVATION r ON r.GUEST_ID = g.GUEST_ID JOIN ROOM ro ON ro.ROOM_ID = r.ROOM_ID "
				+
				"WHERE 1=1";
		List<String> allSearchedDetails = new ArrayList<String>();

		if (!detailsForAdvancedSearch.get(0).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND r.ARRIVAL_DATE LIKE '" + detailsForAdvancedSearch.get(0)
					+ "%'";
		}

		if (!detailsForAdvancedSearch.get(1).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND r.DEPARTURE_DATE LIKE '" + detailsForAdvancedSearch.get(1)
					+ "%'";
		}

		if (!detailsForAdvancedSearch.get(2).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND g.ID_NUMBER = '" + detailsForAdvancedSearch.get(2)
					+ "'";
		}

		if (!detailsForAdvancedSearch.get(3).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND ro.ROOM_NUMBER = '" + detailsForAdvancedSearch.get(3)
					+ "'";
		}

		if (!detailsForAdvancedSearch.get(4).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND p.CITY = '" + detailsForAdvancedSearch.get(4) + "'";
		}

		if (!detailsForAdvancedSearch.get(5).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND p.COUNTRY = '" + detailsForAdvancedSearch.get(5) + "'";
		}

		if (!detailsForAdvancedSearch.get(6).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND p.PHONE_NUMBER = '" + detailsForAdvancedSearch.get(6)
					+ "'";
		}

		if (!detailsForAdvancedSearch.get(7).isEmpty()) {
			sqlGetDetailsForAdvancedSearchStatement += " AND p.MAIL_ADDRESS = '" + detailsForAdvancedSearch.get(7)
					+ "'";
		}

		sqlGetDetailsForAdvancedSearchStatement += " ORDER BY p.LAST_NAME;";

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlGetDetailsForAdvancedSearchStatement);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				allSearchedDetails.add(resultSet.getString("CONFIRMATION_NUMBER"));
				allSearchedDetails.add(resultSet.getString("LAST_NAME"));
				allSearchedDetails.add(resultSet.getString("FIRST_NAME"));
				allSearchedDetails.add(resultSet.getString("ROOM_NUMBER"));
				allSearchedDetails.add(resultSet.getString("ROOM_CATEGORY"));
				allSearchedDetails.add(resultSet.getString("ROOM_STATUS"));
				allSearchedDetails.add(resultSet.getString("ARRIVAL_DATE"));
				allSearchedDetails.add(resultSet.getString("DEPARTURE_DATE"));
				allSearchedDetails.add(resultSet.getString("PAYMENT_TYPE"));
				allSearchedDetails.add(resultSet.getString("RESERVATION_STATUS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allSearchedDetails;

	}

}
