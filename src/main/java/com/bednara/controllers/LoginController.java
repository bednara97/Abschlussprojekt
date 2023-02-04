package com.bednara.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.DBConnectionHandler;
import com.bednara.application.GeneralMethods;
import com.bednara.application.Main;
import com.bednara.exceptions.UserNameNotFoundException;
import com.bednara.exceptions.UserNameOrPasswordWrongException;
import com.bednara.sql.LoginSQLHandler;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class LoginController implements Initializable {

    @FXML
    private MFXTextField userNameTextField;

    @FXML
    private MFXPasswordField userPasswordTextField;

    @FXML
    private Button logoButton;

    @FXML
    public void login() {

        String inputUserName = userNameTextField.getText().trim();
        String inputUserPassword = userPasswordTextField.getText();
        boolean loginSuccess = false;
        boolean exceptionThrown = false;

        if (inputUserName.isBlank() || inputUserPassword.isBlank()) {
            Notifications.create()
                    .title("Achtung")
                    .text("Felder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();

            return;
        }

        try {
            if (DBConnectionHandler.getConnection() == null) {
                DBConnectionHandler.connectToServer();
            }
            loginSuccess = LoginSQLHandler.employeeLogin(inputUserName, inputUserPassword);
        } catch (IOException | SQLException | UserNameNotFoundException | UserNameOrPasswordWrongException ex) {
            if (ex instanceof UserNameNotFoundException) {
                Alert alert = new Alert(AlertType.WARNING,
                        "Username " + inputUserName + " nicht in der Datenbank gefunden.", ButtonType.OK);
                alert.setTitle("myHotel");
                alert.setHeaderText("Datenbank-Fehler");
                alert.show();
                exceptionThrown = true;
            }
            if (ex instanceof UserNameOrPasswordWrongException) {
                Alert alert = new Alert(AlertType.ERROR, "Username oder Passwort falsch. Bitte neu versuchen!",
                        ButtonType.OK);
                alert.setTitle("myHotel");
                alert.setHeaderText("Datenbank-Fehler");
                alert.show();
                userPasswordTextField.setText("");
                exceptionThrown = true;
            }
            // SQL alerts:
            if (ex instanceof SQLException) {
                Alert alert = new Alert(AlertType.ERROR, ex.toString(), ButtonType.OK);
                alert.setTitle("myHotel");
                alert.setHeaderText("Verbindungs-Fehler");
                alert.show();
                exceptionThrown = true;
            }
            if (ex instanceof IOException) {
                Alert alert = new Alert(AlertType.ERROR, ex.toString(), ButtonType.OK);
                alert.setTitle("myHotel");
                alert.setHeaderText("Datenbank-Fehler");
                alert.show();
                exceptionThrown = true;
            }
        }
        if (loginSuccess) {
            Main.UserDataHolder.userName = inputUserName;
            GeneralMethods.changeWindow("Dashboard");
        } else if (!exceptionThrown) { // Fehler, der NIE auftauchen wird, nur Absicherung
            Alert alert = new Alert(AlertType.NONE, "Ups, da scheint ein Fehler vorgefallen zu sein", ButtonType.OK);
            alert.setTitle("myHotel");
            alert.setHeaderText("Fehler");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GeneralMethods.setButtonPicture(logoButton, 52d, 50d, "LoginLogo");
    }

}