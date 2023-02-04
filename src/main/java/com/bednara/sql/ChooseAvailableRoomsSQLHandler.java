package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bednara.application.DBConnectionHandler;

public class ChooseAvailableRoomsSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static List<String> getRoomDetailsForTableView(String arrivalDate, String departureDate) {
        List<String> allAvailableRooms = new ArrayList<String>();
        String sqlGetRoomNumberAndRoomCategoryStatement = "SELECT ro.ROOM_NUMBER, ro.ROOM_CATEGORY, ro.ROOM_STATUS, ro.ROOM_PRICE FROM ROOM ro JOIN RESERVATION r ON ro.ROOM_ID = r.ROOM_ID "
                + " WHERE ro.ROOM_STATUS IN('CLEAN','DIRTY') " +
                " AND ? NOT BETWEEN r.ARRIVAL_DATE AND r.DEPARTURE_DATE " +
                " AND ? NOT BETWEEN r.ARRIVAL_DATE AND r.DEPARTURE_DATE " +
                " AND ro.ROOM_STATUS != 'OUT_OF_ORDER';";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetRoomNumberAndRoomCategoryStatement);
            stmt.setString(1, arrivalDate);
            stmt.setString(2, departureDate);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                allAvailableRooms.add(resultSet.getString("ROOM_NUMBER"));
                allAvailableRooms.add(resultSet.getString("ROOM_CATEGORY"));
                allAvailableRooms.add(resultSet.getString("ROOM_STATUS"));
                allAvailableRooms.add(resultSet.getString("ROOM_PRICE"));
            }

        } catch (Exception e) {

        }
        return allAvailableRooms;
    }
}
