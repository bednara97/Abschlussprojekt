package com.bednara.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.bednara.application.DBConnectionHandler;
import com.bednara.exceptions.UserNameNotFoundException;
import com.bednara.exceptions.UserNameOrPasswordWrongException;

public class LoginSQLHandler {

    private static Connection connection = DBConnectionHandler.getConnection();

    public static void checkUserNameInDataBase(String inputUserName)
            throws SQLException, UserNameNotFoundException {
        String sqlGetUserNameStatement = "SELECT USER_NAME FROM EMPLOYEE WHERE USER_NAME =?;";

        PreparedStatement myPreparedStatement = connection.prepareStatement(sqlGetUserNameStatement);
        myPreparedStatement.setString(1, inputUserName);
        ResultSet resultSet = myPreparedStatement.executeQuery();

        if (!resultSet.next()) {
            throw new UserNameNotFoundException("Username not found in database.");
        }

    }

    public static boolean checkPasswordIsCorrect(String inputUserName, String inputUserPassword)
            throws SQLException, UserNameOrPasswordWrongException {
        String sqlGetPasswordStatement = "SELECT USER_PASSWORD FROM EMPLOYEE WHERE USER_NAME =?;";

        PreparedStatement myPreparedStatement = connection.prepareStatement(sqlGetPasswordStatement);
        myPreparedStatement.setString(1, inputUserName);
        ResultSet resultSet = myPreparedStatement.executeQuery();

        while (resultSet.next()) {
            if (BCrypt.checkpw(inputUserPassword, resultSet.getString("USER_PASSWORD"))) {
                return true;
            } else
                throw new UserNameOrPasswordWrongException("Username or password wrong.");
        }
        return false;
    }

    public static boolean employeeLogin(String inputUserName, String inputUserPassword)
            throws IOException, SQLException, UserNameNotFoundException, UserNameOrPasswordWrongException {
        checkUserNameInDataBase(inputUserName);
        if (checkPasswordIsCorrect(inputUserName, inputUserPassword)) {
            return true;
        } else
            return false;
    }
}
