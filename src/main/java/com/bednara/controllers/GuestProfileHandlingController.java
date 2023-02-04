package com.bednara.controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Guest;
import com.bednara.sql.GuestProfileHandlingSQLHandler;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class GuestProfileHandlingController implements Initializable {

    @FXML
    private MFXTextField iDNumberTextField;

    @FXML
    private MFXTextField lastNameTextField;

    @FXML
    private MFXTextField firstNameTextField;

    @FXML
    private MFXTextField streetNameTextField;

    @FXML
    private MFXTextField streetNumberTextField;

    @FXML
    private MFXTextField apartmentNumberTextField;

    @FXML
    private MFXTextField cityTextField;

    @FXML
    private MFXTextField zipCodeTextField;

    @FXML
    private MFXTextField countryTextField;

    @FXML
    private MFXTextField phoneNumberTextField;

    @FXML
    private MFXTextField mailAddressTextField;

    @FXML
    private MFXDatePicker birthDatePicker;

    @FXML
    private Button refreshButton;

    @FXML
    private MFXButton confirmButton;

    @FXML
    public void addGuest(ActionEvent event) {
        List<String> userInput = processUserInputForGuests();
        if (!userInput.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Sind Sie sicher, dass Sie den Gast anlegen möchten?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult().toString().contains("YES")) {
                GuestProfileHandlingSQLHandler.insertGuest(userInput);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                event.consume();
                stage.hide();
            }
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Eingabefelder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();
        }
    }

    @FXML
    public void clear() {
        iDNumberTextField.setText("");
        birthDatePicker.setText("");
        lastNameTextField.setText("");
        firstNameTextField.setText("");
        streetNameTextField.setText("");
        streetNumberTextField.setText("");
        apartmentNumberTextField.setText("");
        cityTextField.setText("");
        zipCodeTextField.setText("");
        countryTextField.setText("");
        phoneNumberTextField.setText("");
        mailAddressTextField.setText("");
    }

    public List<String> processUserInputForGuests() {

        List<String> userInput = new ArrayList<String>();

        String iDNumber = iDNumberTextField.getText();
        String birthDate = "";
        if (!birthDatePicker.getText().isEmpty()) { // Datenbankformat: 2022-10-15
            birthDate = Timestamp.valueOf(birthDatePicker.getValue().atStartOfDay()).toString().substring(0, 10);
        }
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String streetName = streetNameTextField.getText();
        String streetNumber = streetNumberTextField.getText();
        String apartmentNumber = apartmentNumberTextField.getText();
        String city = cityTextField.getText();
        String zipCode = zipCodeTextField.getText();
        String country = countryTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String mailAddress = mailAddressTextField.getText();

        if (!iDNumber.isBlank() && !lastName.isBlank() && !firstName.isBlank()
                && !mailAddress.isBlank()) {
            userInput.add(iDNumber);
            userInput.add(firstName);
            userInput.add(lastName);
            userInput.add(birthDate);
            userInput.add(phoneNumber);
            userInput.add(mailAddress);
            userInput.add(streetName);
            userInput.add(streetNumber);
            userInput.add(apartmentNumber);
            userInput.add(zipCode);
            userInput.add(city);
            userInput.add(country);

        }

        return userInput;
    }

    @FXML
    public void cancelOrRefresh(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        event.consume();
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (GeneralMethods.getCurrentStage().getUserData().getClass().getComponentType() == Guest.class) { 
            // fillGuestDetails nur für das Öffnen und Vorbefüllen der Textfelder
            fillGuestDetails();
            confirmButton.setText("Bearbeiten");
            confirmButton.setOnAction(modifyGuest);
            refreshButton.setVisible(false);
            iDNumberTextField.setEditable(false);
        }

        GeneralMethods.setButtonPicture(refreshButton, 16d, 16d, "RefreshPurple");

    }

    private void fillGuestDetails() {
        Object[] currentGuestObject = (Object[]) GeneralMethods.getCurrentStage().getUserData();
        Guest currentGuest = Guest.class.cast(currentGuestObject[0]);

        iDNumberTextField.setText(String.valueOf(currentGuest.getIDNumber()));
        birthDatePicker.setValue(currentGuest.getBirthDate().toLocalDateTime().toLocalDate());
        lastNameTextField.setText(currentGuest.getLastName());
        firstNameTextField.setText(currentGuest.getFirstName());
        streetNameTextField.setText(currentGuest.getStreetName());
        streetNumberTextField.setText(String.valueOf(currentGuest.getStreetNumber()));
        apartmentNumberTextField.setText(String.valueOf(currentGuest.getApartmentNumber()));
        cityTextField.setText(currentGuest.getCity());
        zipCodeTextField.setText(String.valueOf(currentGuest.getZipCode()));
        countryTextField.setText(currentGuest.getCountry());
        phoneNumberTextField.setText(currentGuest.getPhoneNumber());
        mailAddressTextField.setText(currentGuest.getMailAddress());
    }

    private EventHandler<ActionEvent> modifyGuest = new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            List<String> userInput = processUserInputForGuests();
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Sind Sie sicher, dass Sie den Gast bearbeiten möchten?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult().toString().contains("YES")) {
                GuestProfileHandlingSQLHandler.updateGuest(userInput);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                event.consume();
                stage.hide();
            }
        }
    };

}
