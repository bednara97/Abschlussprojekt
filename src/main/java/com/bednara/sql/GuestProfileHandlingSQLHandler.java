package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import com.bednara.application.DBConnectionHandler;
import com.bednara.fakedatainserter.FakeDataInserter;

public class GuestProfileHandlingSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static void insertGuest(List<String> userInput) {
        String sqlInsertPersonStatement = "INSERT INTO PERSON VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String sqlInsertGuestStatement = "INSERT INTO GUEST VALUES(?,?)";

        try {
            PreparedStatement personStmt = connection.prepareStatement(sqlInsertPersonStatement);
            PreparedStatement guestStmt = connection.prepareStatement(sqlInsertGuestStatement);

            personStmt.setLong(1, Long.parseLong(userInput.get(0)));
            personStmt.setString(2, userInput.get(1));
            personStmt.setString(3, userInput.get(2));
            personStmt.setTimestamp(4, new Timestamp(Timestamp.valueOf(userInput.get(3) + " 00:00:00").getTime()));
            personStmt.setString(5, userInput.get(4));
            personStmt.setString(6, userInput.get(5));
            personStmt.setString(7, userInput.get(6));
            personStmt.setInt(8, Integer.parseInt(userInput.get(7)));
            personStmt.setInt(9, Integer.parseInt(userInput.get(8)));
            personStmt.setString(10, userInput.get(9));
            personStmt.setString(11, userInput.get(10));
            personStmt.setString(12, userInput.get(11));
            personStmt.executeUpdate();

            guestStmt.setLong(1, FakeDataInserter.generateNumbers(8));
            guestStmt.setLong(2, Long.parseLong(userInput.get(0)));
            guestStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateGuest(List<String> userInput) {
        String sqlUpdateReservationStatement = "UPDATE PERSON SET FIRST_NAME = ?, LAST_NAME = ?, BIRTH_DATE = ?"
                +
                ", PHONE_NUMBER = ?, MAIL_ADDRESS = ?, STREET_NAME = ?, STREET_NUMBER = ?, APARTMENT_NUMBER = ?, ZIP_CODE = ?, CITY = ?, COUNTRY = ? WHERE ID_NUMBER = ?";
        try {
            PreparedStatement personStmt = connection.prepareStatement(sqlUpdateReservationStatement);

            personStmt.setString(1, userInput.get(1));
            personStmt.setString(2, userInput.get(2));
            personStmt.setTimestamp(3, new Timestamp(Timestamp.valueOf(userInput.get(3) + " 00:00:00").getTime()));
            personStmt.setString(4, userInput.get(4));
            personStmt.setString(5, userInput.get(5));
            personStmt.setString(6, userInput.get(6));
            personStmt.setInt(7, Integer.parseInt(userInput.get(7)));
            personStmt.setInt(8, Integer.parseInt(userInput.get(8)));
            personStmt.setString(9, userInput.get(9));
            personStmt.setString(10, userInput.get(10));
            personStmt.setString(11, userInput.get(11));
            personStmt.setLong(12, Long.parseLong(userInput.get(0)));
            personStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
