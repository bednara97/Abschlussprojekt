package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bednara.application.DBConnectionHandler;

public class FrontDeskSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static List<String> getSearchedReservationDetails(String confirmationNumber, String lastName,
            String firstName) {
        PreparedStatement myPreparedStatement = null;

        String sqlConfirmationNumberStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER JOIN RESERVATION r ON r.GUEST_ID = g.GUEST_ID "
                +
                "JOIN ROOM ro ON r.ROOM_ID = ro.ROOM_ID WHERE r.CONFIRMATION_NUMBER = ?";

        String sqlFullNameStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER JOIN RESERVATION r ON r.GUEST_ID = g.GUEST_ID "
                +
                "JOIN ROOM ro ON r.ROOM_ID = ro.ROOM_ID WHERE p.LAST_NAME = ? AND p.FIRST_NAME = ?;";

        String sqlLastNameOrFirstNameStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER JOIN RESERVATION r ON r.GUEST_ID = g.GUEST_ID "
                +
                "JOIN ROOM ro ON r.ROOM_ID = ro.ROOM_ID WHERE p.LAST_NAME = ? OR p.FIRST_NAME = ?;";

        List<String> reservationDetails = new ArrayList<String>();

        try {
            if (!confirmationNumber.isBlank()) {
                myPreparedStatement = connection.prepareStatement(sqlConfirmationNumberStatement);
                myPreparedStatement.setString(1, confirmationNumber);

            } else if (!lastName.isBlank() && !firstName.isBlank()) {
                myPreparedStatement = connection.prepareStatement(sqlFullNameStatement);
                myPreparedStatement.setString(1, lastName);
                myPreparedStatement.setString(2, firstName);

            } else {
                myPreparedStatement = connection.prepareStatement(sqlLastNameOrFirstNameStatement);
                myPreparedStatement.setString(1, lastName);
                myPreparedStatement.setString(2, firstName);
            }

            ResultSet resultSet = myPreparedStatement.executeQuery();

            while (resultSet.next()) {
                reservationDetails.add(resultSet.getString("CONFIRMATION_NUMBER"));
                reservationDetails.add(resultSet.getString("LAST_NAME"));
                reservationDetails.add(resultSet.getString("FIRST_NAME"));
                reservationDetails.add(resultSet.getString("ROOM_NUMBER"));
                reservationDetails.add(resultSet.getString("ROOM_CATEGORY"));
                reservationDetails.add(resultSet.getString("ROOM_STATUS"));
                reservationDetails.add(resultSet.getString("ARRIVAL_DATE"));
                reservationDetails.add(resultSet.getString("DEPARTURE_DATE"));
                reservationDetails.add(resultSet.getString("PAYMENT_TYPE"));
                reservationDetails.add(resultSet.getString("RESERVATION_STATUS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservationDetails;
    }

    public static List<String> getReservationDetailsForTableView() {
        String sqlGetAllReservationDetailsStatement = "SELECT r.CONFIRMATION_NUMBER, p.LAST_NAME, p.FIRST_NAME, r.ROOM_NUMBER, ro.ROOM_CATEGORY, ro.ROOM_STATUS, r.ARRIVAL_DATE, r.DEPARTURE_DATE, r.PAYMENT_TYPE, r.RESERVATION_STATUS "
                +
                "FROM PERSON p JOIN GUEST g ON g.ID_NUMBER = p.ID_NUMBER JOIN RESERVATION r ON r.GUEST_ID = g.GUEST_ID JOIN ROOM ro ON ro.ROOM_ID = r.ROOM_ID "
                +
                "WHERE (r.ARRIVAL_DATE >= CURDATE() AND r.DEPARTURE_DATE >= CURDATE()) ORDER BY r.ARRIVAL_DATE;";

        List<String> allReservationDetails = new ArrayList<String>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetAllReservationDetailsStatement);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                allReservationDetails.add(resultSet.getString("CONFIRMATION_NUMBER"));
                allReservationDetails.add(resultSet.getString("LAST_NAME"));
                allReservationDetails.add(resultSet.getString("FIRST_NAME"));
                allReservationDetails.add(resultSet.getString("ROOM_NUMBER"));
                allReservationDetails.add(resultSet.getString("ROOM_CATEGORY"));
                allReservationDetails.add(resultSet.getString("ROOM_STATUS"));
                allReservationDetails.add(resultSet.getString("ARRIVAL_DATE"));
                allReservationDetails.add(resultSet.getString("DEPARTURE_DATE"));
                allReservationDetails.add(resultSet.getString("PAYMENT_TYPE"));
                allReservationDetails.add(resultSet.getString("RESERVATION_STATUS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allReservationDetails;

    }

}
