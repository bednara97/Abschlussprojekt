package com.bednara.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bednara.application.DBConnectionHandler;
import com.bednara.application.Main;

public class ChangePasswordSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static String getCurrentPassword() {

        String sqlGetCurrentPasswordStatement = "SELECT USER_PASSWORD FROM EMPLOYEE e WHERE USER_NAME = " + "'"
                + Main.UserDataHolder.userName + "';";
        String currentPassword = "";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetCurrentPasswordStatement);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                currentPassword = resultSet.getString("USER_PASSWORD");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentPassword;
    }

    public static void updatePassword(String hashedPassword) {
        String sqlUpdatePasswordStatement = "UPDATE EMPLOYEE SET USER_PASSWORD = ? WHERE USER_NAME = " + "'"
                + Main.UserDataHolder.userName + "';";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlUpdatePasswordStatement);
            stmt.setString(1, hashedPassword);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
