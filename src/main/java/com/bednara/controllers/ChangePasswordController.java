package com.bednara.controllers;

import org.mindrot.jbcrypt.BCrypt;

import com.bednara.application.GeneralMethods;
import com.bednara.sql.ChangePasswordSQLHandler;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ChangePasswordController {

    @FXML
    private MFXPasswordField oldPasswordTextField;

    @FXML
    private MFXPasswordField newPasswordTextField;

    @FXML
    private MFXPasswordField confirmNewPasswordTextField;

    @FXML
    public void changePassword() {

        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmNewPassword = confirmNewPasswordTextField.getText();

        String currentPassword = ChangePasswordSQLHandler.getCurrentPassword();

        if (BCrypt.checkpw(oldPassword, currentPassword)) {
            if (newPassword.equals(confirmNewPassword)) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));

                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Sind Sie sicher, dass Sie das Passwort ändern möchten?",
                        ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult().toString().contains("YES")) {
                    ChangePasswordSQLHandler.updatePassword(hashedPassword);
                    GeneralMethods.getCurrentStage().hide();
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING,
                        "Passwörter stimmen nicht überein", ButtonType.OK);
                alert.setTitle("myHotel");
                alert.setHeaderText("Fehlermeldung");
                alert.show();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING,
                    "Altes Passwort nicht korrekt", ButtonType.OK);
            alert.setTitle("myHotel");
            alert.setHeaderText("Fehlermeldung");
            alert.show();
        }

    }

    @FXML
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        event.consume();
        stage.hide();
    }

}
