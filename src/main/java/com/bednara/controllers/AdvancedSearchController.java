package com.bednara.controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.sql.AdvancedSearchSQLHandler;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AdvancedSearchController implements Initializable {

    @FXML
    private MFXDatePicker arrivalDatePicker;

    @FXML
    private MFXDatePicker departureDatePicker;

    @FXML
    private MFXTextField guestIDNumberTextField;

    @FXML
    private MFXTextField roomNumberTextField;

    @FXML
    private MFXTextField phoneNumberTextField;

    @FXML
    private MFXTextField mailAddressTextField;

    @FXML
    private MFXTextField cityTextField;

    @FXML
    private MFXTextField countryTextField;

    @FXML
    public void advancedSearch(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String arrivalDate = "";
        String departureDate = "";

        if (!arrivalDatePicker.getText().isEmpty()) { // Datenbankformat: 2022-10-15
            arrivalDate = Timestamp.valueOf(arrivalDatePicker.getValue().atStartOfDay()).toString().substring(0, 11);
        }
        if (!departureDatePicker.getText().isEmpty()) {
            departureDate = Timestamp.valueOf(departureDatePicker.getValue().atStartOfDay()).toString().substring(0,
                    11);
        }

        String guestIDNumber = guestIDNumberTextField.getText();
        String roomNumber = roomNumberTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String mailAdress = mailAddressTextField.getText();

        List<String> detailsForAdvancedSearch = new ArrayList<String>();
        detailsForAdvancedSearch.add(arrivalDate);
        detailsForAdvancedSearch.add(departureDate);
        detailsForAdvancedSearch.add(guestIDNumber);
        detailsForAdvancedSearch.add(roomNumber);
        detailsForAdvancedSearch.add(city);
        detailsForAdvancedSearch.add(country);
        detailsForAdvancedSearch.add(phoneNumber);
        detailsForAdvancedSearch.add(mailAdress);

        if (!detailsForAdvancedSearch.stream().allMatch(f -> f.isBlank())) {
            List<String> searchedAdvancedDetails = new ArrayList<String>();

            searchedAdvancedDetails = AdvancedSearchSQLHandler.searchForAdvancedDetails(detailsForAdvancedSearch);
            if (!searchedAdvancedDetails.isEmpty()) {
                stage.setUserData(searchedAdvancedDetails);
                event.consume();
                stage.close();
            } else {
                Notifications.create()
                        .title("Achtung")
                        .text("Suche ergab keine Treffer!")
                        .position(Pos.CENTER)
                        .showWarning();
            }
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Suchfelder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();
        }

    }

    @FXML
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        event.consume();
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GeneralMethods.getCurrentStage().setUserData(null);
    }

}