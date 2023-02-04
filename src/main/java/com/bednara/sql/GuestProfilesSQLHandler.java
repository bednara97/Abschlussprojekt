package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bednara.application.DBConnectionHandler;

public class GuestProfilesSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static List<String> getGuestDetailsForTableView() {
        String sqlGetAllGuestDetailsStatement = "SELECT g.ID_NUMBER, p.LAST_NAME, p.FIRST_NAME, p.CITY, p.COUNTRY, p.BIRTH_DATE, "
                + " p.STREET_NAME, p.STREET_NUMBER, p.APARTMENT_NUMBER, p.ZIP_CODE, p.PHONE_NUMBER, p.MAIL_ADDRESS FROM PERSON p"
                + " JOIN GUEST g ON g.ID_NUMBER = p.ID_NUMBER ORDER BY p.LAST_NAME";

        List<String> guestDetails = new ArrayList<String>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetAllGuestDetailsStatement);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                guestDetails.add(resultSet.getString("ID_NUMBER"));
                guestDetails.add(resultSet.getString("LAST_NAME"));
                guestDetails.add(resultSet.getString("FIRST_NAME"));
                guestDetails.add(resultSet.getString("BIRTH_DATE"));
                guestDetails.add(resultSet.getString("PHONE_NUMBER"));
                guestDetails.add(resultSet.getString("MAIL_ADDRESS"));
                guestDetails.add(resultSet.getString("STREET_NAME"));
                guestDetails.add(resultSet.getString("STREET_NUMBER"));
                guestDetails.add(resultSet.getString("APARTMENT_NUMBER"));
                guestDetails.add(resultSet.getString("ZIP_CODE"));
                guestDetails.add(resultSet.getString("CITY"));
                guestDetails.add(resultSet.getString("COUNTRY"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestDetails;

    }

    public static List<String> getSearchedGuestDetails(String lastName, String firstName, String iDNumber) {
        PreparedStatement myPreparedStatement = null;

        String sqlIDNumberStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER "
                +
                "WHERE g.ID_NUMBER = ?";

        String sqlFullNameStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER "
                +
                "WHERE p.LAST_NAME = ? AND p.FIRST_NAME = ?;";

        String sqlLastNameOrFirstNameStatement = "SELECT * FROM GUEST g JOIN PERSON p ON g.ID_NUMBER = p.ID_NUMBER "
                +
                "WHERE p.LAST_NAME = ? OR p.FIRST_NAME = ?;";

        List<String> guestDetails = new ArrayList<String>();

        try {
            if (!iDNumber.isBlank()) {
                myPreparedStatement = connection.prepareStatement(sqlIDNumberStatement);
                myPreparedStatement.setString(1, iDNumber);

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
                guestDetails.add(resultSet.getString("ID_NUMBER"));
                guestDetails.add(resultSet.getString("LAST_NAME"));
                guestDetails.add(resultSet.getString("FIRST_NAME"));
                guestDetails.add(resultSet.getString("BIRTH_DATE"));
                guestDetails.add(resultSet.getString("PHONE_NUMBER"));
                guestDetails.add(resultSet.getString("MAIL_ADDRESS"));
                guestDetails.add(resultSet.getString("STREET_NAME"));
                guestDetails.add(resultSet.getString("STREET_NUMBER"));
                guestDetails.add(resultSet.getString("APARTMENT_NUMBER"));
                guestDetails.add(resultSet.getString("ZIP_CODE"));
                guestDetails.add(resultSet.getString("CITY"));
                guestDetails.add(resultSet.getString("COUNTRY"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return guestDetails;
    }
}