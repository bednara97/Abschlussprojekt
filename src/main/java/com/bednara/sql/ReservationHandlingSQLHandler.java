package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bednara.application.DBConnectionHandler;

public class ReservationHandlingSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static void insertReservation(List<String> userInput) {
        String sqlInsertReservationStatement = "INSERT INTO RESERVATION VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement reservationStmt = connection.prepareStatement(sqlInsertReservationStatement);

            reservationStmt.setString(1, userInput.get(0));
            reservationStmt.setTimestamp(2, new Timestamp(Timestamp.valueOf(userInput.get(1) + " 00:00:00").getTime()));
            reservationStmt.setTimestamp(3, new Timestamp(Timestamp.valueOf(userInput.get(2) + " 00:00:00").getTime()));
            reservationStmt.setInt(4, Integer.parseInt(userInput.get(3)));
            reservationStmt.setDouble(5, Double.parseDouble(userInput.get(4)));
            reservationStmt.setDouble(6, Double.parseDouble(userInput.get(5)));
            reservationStmt.setString(7, userInput.get(6));
            reservationStmt.setString(8, userInput.get(7));
            reservationStmt.setInt(9, Integer.parseInt(userInput.get(8)));
            reservationStmt.setInt(10, Integer.parseInt(userInput.get(9)));
            reservationStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void modifyReservationStatus(String confirmationNumber, String roomID, String roomStatus,
            String reservationStatus, String totalBalance) {
        // Für Check-In, Check-Out und Cancel
        String sqlModifyReservationStatusStatement = "UPDATE RESERVATION SET RESERVATION_STATUS = ?, TOTAL_BALANCE = ? WHERE CONFIRMATION_NUMBER = ?";
        String sqlUpdateRoomCategoryStatement = "UPDATE ROOM SET ROOM_STATUS = ? WHERE ROOM_ID = ?";

        try {
            PreparedStatement reservationStmt = connection.prepareStatement(sqlModifyReservationStatusStatement);
            PreparedStatement roomStmt = connection.prepareStatement(sqlUpdateRoomCategoryStatement);

            reservationStmt.setString(1, reservationStatus);
            reservationStmt.setString(2, totalBalance);
            reservationStmt.setString(3, confirmationNumber);

            roomStmt.setString(1, roomStatus);
            roomStmt.setString(2, roomID);

            reservationStmt.executeUpdate();
            roomStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateReservation(List<String> userInput) {
        String sqlUpdateReservationStatement = "UPDATE RESERVATION SET ARRIVAL_DATE = ?, DEPARTURE_DATE = ?, ROOM_NUMBER = ?, RESERVATION_PRICE = ?"
                +
                ", TOTAL_BALANCE = ?, PAYMENT_TYPE = ?, RESERVATION_STATUS = ?, GUEST_ID = ?, ROOM_ID = ? WHERE CONFIRMATION_NUMBER = ?";
        try {
            PreparedStatement reservationStmt = connection.prepareStatement(sqlUpdateReservationStatement);

            reservationStmt.setTimestamp(1, new Timestamp(Timestamp.valueOf(userInput.get(1) + " 00:00:00").getTime()));
            reservationStmt.setTimestamp(2, new Timestamp(Timestamp.valueOf(userInput.get(2) + " 00:00:00").getTime()));
            reservationStmt.setInt(3, Integer.parseInt(userInput.get(3)));
            reservationStmt.setDouble(4, Double.parseDouble(userInput.get(4)));
            reservationStmt.setDouble(5, Double.parseDouble(userInput.get(5)));
            reservationStmt.setString(6, userInput.get(6));
            reservationStmt.setString(7, userInput.get(7));
            reservationStmt.setInt(8, Integer.parseInt(userInput.get(8)));
            reservationStmt.setInt(9, Integer.parseInt(userInput.get(9)));
            reservationStmt.setString(10, userInput.get(0));
            reservationStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String> getGuestIDAndIDNumberAndRoomIDFromConfirmationNumber(String confirmationNumber) {
        List<String> guestDetails = new ArrayList<String>();

        String sqlGetGuestIDAndIDNumberStatement = "SELECT g.GUEST_ID, g.ID_NUMBER, r.ROOM_ID FROM RESERVATION r JOIN GUEST g ON g.GUEST_ID = r.GUEST_ID WHERE r.CONFIRMATION_NUMBER = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetGuestIDAndIDNumberStatement);
            stmt.setString(1, confirmationNumber);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                guestDetails.add(resultSet.getString("GUEST_ID"));
                guestDetails.add(resultSet.getString("ID_NUMBER"));
                guestDetails.add(resultSet.getString("ROOM_ID"));
            }

        } catch (Exception e) {

        }
        return guestDetails;
    }

    public static String getGuestIDFromIDNumber(String iDNumber) {
        String guestID = "";

        String sqlGetGuestIDAndIDNumberStatement = "SELECT g.GUEST_ID FROM GUEST g WHERE g.ID_NUMBER = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetGuestIDAndIDNumberStatement);
            stmt.setString(1, iDNumber);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                guestID = resultSet.getString("GUEST_ID");
            }

        } catch (Exception e) {

        }
        return guestID;
    }

    public static String getRoomIDFromRoomNumber(String roomNumber) {
        String roomID = "";

        String sqlGetRoomIDStatement = "SELECT ro.ROOM_ID FROM ROOM ro WHERE ro.ROOM_NUMBER = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetRoomIDStatement);
            stmt.setString(1, roomNumber);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                roomID = resultSet.getString("ROOM_ID");
            }

        } catch (Exception e) {

        }
        return roomID;
    }
}