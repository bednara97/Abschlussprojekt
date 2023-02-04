package com.bednara.controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Reservation;
import com.bednara.application.Room;
import com.bednara.enums.EPaymentType;
import com.bednara.enums.EReservationStatus;
import com.bednara.enums.ERoomCategory;
import com.bednara.fakedatainserter.FakeDataInserter;
import com.bednara.sql.ReservationHandlingSQLHandler;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ReservationHandlingController implements Initializable {

    @FXML
    private MFXDatePicker arrivalDatePicker;

    @FXML
    private MFXDatePicker departureDatePicker;

    @FXML
    private MFXTextField iDNumberTextField;

    @FXML
    private MFXComboBox<String> paymentTypeComboBox;

    @FXML
    private MFXTextField cardNumberTextField;

    @FXML
    private MFXTextField roomCategoryTextField;

    @FXML
    private MFXTextField roomNumberTextField;

    @FXML
    private Text roomPriceText;

    @FXML
    private Button addProfileButton;

    @FXML
    private Button addRoomButton;

    @FXML
    private Button refreshButton;

    @FXML
    private MFXButton confirmButton;

    @FXML
    private MFXButton cancelReservationButton;

    @FXML
    private MFXButton checkInOrOutButton;

    private Reservation globalReservation = new Reservation();
    private String guestID = "";
    private String roomID = "";

    @FXML
    public void addReservation(ActionEvent event) {
        List<String> userInput = processUserInputForReservations();
        if (!userInput.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Sind Sie sicher, dass Sie die Reservierung anlegen möchten?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult().toString().contains("YES")) {
                ReservationHandlingSQLHandler.insertReservation(userInput); // Hier wird die Reservierung erstellt
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
        arrivalDatePicker.setText("");
        departureDatePicker.setText("");
        iDNumberTextField.setText("");
        paymentTypeComboBox.setValue("");
        cardNumberTextField.setText("");
        roomCategoryTextField.setText("");
        roomNumberTextField.setText("");
    }

    public List<String> processUserInputForReservations() {
        // Für das Anlegen und Bearbeiten
        List<String> userInput = new ArrayList<String>();

        String confirmationNumber = "";
        String arrivalDate = "";
        String departureDate = "";
        String reservationPrice = "";
        String roomNumber = "";
        String totalBalance = "";
        String roomCategory = "";
        String paymentType = "";
        String reservationStatus = "";

        if (!arrivalDatePicker.getText().isEmpty()) { // Datenbankformat: 2022-10-15
            arrivalDate = Timestamp.valueOf(arrivalDatePicker.getValue().atStartOfDay()).toString().substring(0, 10);
        }
        if (!departureDatePicker.getText().isEmpty()) {
            departureDate = Timestamp.valueOf(departureDatePicker.getValue().atStartOfDay()).toString().substring(0,
                    10);
        }
        if (!roomNumberTextField.getText().isEmpty()) { // Datenbankformat: 2022-10-15
            roomNumber = String.valueOf(roomNumberTextField.getText());
        }
        if (globalReservation.getReservationStatus() == EReservationStatus.UNKNOWN) {
            reservationStatus = EReservationStatus.UNKNOWN.toString();
        } else {
            reservationStatus = globalReservation.getReservationStatus().toString();
        }

        String iDNumber = iDNumberTextField.getText();

        if (paymentTypeComboBox.getValue() != null) {
            paymentType = EPaymentType.getEPaymentTypeFromValue(paymentTypeComboBox.getValue());
        }

        if (guestID.isBlank()) {
            guestID = ReservationHandlingSQLHandler.getGuestIDFromIDNumber(iDNumber);
        }
        if (roomID.isBlank()) {
            roomID = ReservationHandlingSQLHandler.getRoomIDFromRoomNumber(roomNumber);
        }

        if (globalReservation.getConfirmationNumber().isBlank()) {
            confirmationNumber = FakeDataInserter.generateConfirmationNumbers().get(0);
        } else {
            confirmationNumber = globalReservation.getConfirmationNumber();
        }

        if (roomCategoryTextField.getText() != null) {
            roomCategory = roomCategoryTextField.getText().toUpperCase();
            reservationPrice = roomPriceText.getText();
            totalBalance = reservationPrice;
        }

        if (!iDNumber.isBlank() && !paymentType.isBlank() && !roomCategory.isBlank()
                && !reservationPrice.isBlank() && !roomNumber.isBlank()) {
            userInput.add(confirmationNumber);
            userInput.add(arrivalDate);
            userInput.add(departureDate);
            userInput.add(roomNumber);
            userInput.add(reservationPrice);
            userInput.add(totalBalance);
            userInput.add(paymentType);
            userInput.add(reservationStatus);
            userInput.add(guestID);
            userInput.add(roomID);
        }
        return userInput;
    }

    private EventHandler<ActionEvent> modifyReservation = new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            List<String> userInput = processUserInputForReservations();
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Sind Sie sicher, dass Sie die Reservierung bearbeiten möchten?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult().toString().contains("YES")) {
                ReservationHandlingSQLHandler.updateReservation(userInput);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                event.consume();
                stage.hide();
            }
        }
    };

    private EventHandler<ActionEvent> checkOutReservation = new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Sind Sie sicher, dass Sie die Reservierung auschecken möchten?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult().toString().contains("YES")) {
                ReservationHandlingSQLHandler.modifyReservationStatus(globalReservation.getConfirmationNumber(),
                        roomID,
                        "DIRTY",
                        "CHECKED_OUT",
                        String.valueOf(0));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                event.consume();
                stage.hide();
            }
        }
    };

    @FXML
    public void cancelReservation(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Sind Sie sicher, dass Sie die Reservierung stornieren möchten?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult().toString().contains("YES")) {
            ReservationHandlingSQLHandler.modifyReservationStatus(globalReservation.getConfirmationNumber(),
                    roomID,
                    "DIRTY",
                    "CANCELED",
                    String.valueOf(0));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            event.consume();
            stage.hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (GeneralMethods.getCurrentStage().getUserData().getClass().getComponentType() == Reservation.class) {
            // fillReservationDetails nur für das Öffnen und Vorbefüllen der Textfelder
            fillReservationDetails();
            addProfileButton.setVisible(false);
            refreshButton.setVisible(false);
            confirmButton.setText("Bearbeiten");
            confirmButton.setOnAction(modifyReservation);
            if (globalReservation.getReservationStatus() == EReservationStatus.CHECKED_IN) {
                checkInOrOutButton.setText("Check-Out");
                checkInOrOutButton.setOnAction(checkOutReservation);
                cancelReservationButton.setVisible(false);
            } else if (globalReservation.getReservationStatus() == EReservationStatus.CHECKED_OUT) {
                cancelReservationButton.setVisible(false);
            }
        } else {
            checkInOrOutButton.setVisible(false);
            cancelReservationButton.setVisible(false);

        }

        GeneralMethods.setButtonPicture(addProfileButton, 24d, 24d, "AddProfilePurple");
        GeneralMethods.setButtonPicture(addRoomButton, 24d, 24d, "AvailableRoomsPurple");
        GeneralMethods.setButtonPicture(refreshButton, 16d, 16d, "RefreshPurple");

        setupPaymentTypeComboBox();

        // Listener verändert den Preis entsprechend der Kategorie
        roomCategoryTextField.textProperty().addListener((options, oldValue, newValue) -> {
            String roomCategory = roomCategoryTextField.getText().toUpperCase();
            if (!roomCategory.isEmpty())
                roomPriceText.setText(String.valueOf(ERoomCategory.valueOf(roomCategory).getCategoryPrice()));
            else
                roomPriceText.setText("0.0");
        });

        roomCategoryTextField.setOnMouseClicked(event -> Notifications.create()
                .title("Achtung")
                .text("Bitte wählen Sie aus den verfügbaren Zimmern über das Symbol rechts!")
                .position(Pos.CENTER)
                .showWarning());
        roomNumberTextField.setOnMouseClicked(event -> Notifications.create()
                .title("Achtung")
                .text("Bitte wählen Sie aus den verfügbaren Zimmern über das Symbol rechts!")
                .position(Pos.CENTER)
                .showWarning());
    }

    @FXML
    public void openChooseAvailableRoomsWindow() {
        List<String> reservationDates = new ArrayList<String>();
        if (arrivalDatePicker.getValue() != null && departureDatePicker.getValue() != null) {
            reservationDates
                    .add(Timestamp.valueOf(arrivalDatePicker.getValue().atStartOfDay()).toString().substring(0, 10));
            reservationDates
                    .add(Timestamp.valueOf(departureDatePicker.getValue().atStartOfDay()).toString().substring(0, 10));
            Stage newStage = GeneralMethods.openWindowAsNewStage("ChooseAvailableRooms", "Verfügbare Zimmer",
                    reservationDates);
            try {
                newStage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {

                        if (newStage.getUserData() != null) {
                            Room chosenRoom = (Room) newStage.getUserData();
                            if (chosenRoom != null) {
                                roomCategoryTextField.setText(chosenRoom.getRoomCategory().getCategoryName());
                                roomNumberTextField.setText(String.valueOf(chosenRoom.getRoomNumber()));
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Anreise- und Abreisefelder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();
        }

    }

    @FXML
    private void checkInReservation(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Sind Sie sicher, dass Sie die Reservierung einchecken wollen?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult().toString().contains("YES")) {
            ReservationHandlingSQLHandler.modifyReservationStatus(globalReservation.getConfirmationNumber(),
                    roomID,
                    "OCCUPIED",
                    "CHECKED_IN",
                    roomPriceText.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            event.consume();
            stage.hide();
        }
    }

    private void fillReservationDetails() {
        Object[] currentReservationObject = (Object[]) GeneralMethods.getCurrentStage().getUserData();
        Reservation currentReservation = Reservation.class.cast(currentReservationObject[0]);
        globalReservation = currentReservation;

        List<String> guestDetails = ReservationHandlingSQLHandler
                .getGuestIDAndIDNumberAndRoomIDFromConfirmationNumber(currentReservation.getConfirmationNumber());

        arrivalDatePicker.setText(currentReservation.getArrivalDate().toLocalDateTime().toLocalDate().toString());
        arrivalDatePicker.setValue(currentReservation.getArrivalDate().toLocalDateTime().toLocalDate());

        departureDatePicker.setText(currentReservation.getDepartureDate().toLocalDateTime().toLocalDate().toString());
        departureDatePicker.setValue(currentReservation.getDepartureDate().toLocalDateTime().toLocalDate());

        iDNumberTextField.setText(guestDetails.get(1));

        globalReservation.setConfirmationNumber(currentReservation.getConfirmationNumber());

        paymentTypeComboBox.setValue(currentReservation.getPaymentType().getPaymentType());
        paymentTypeComboBox.setText(currentReservation.getPaymentType().getPaymentType());

        roomCategoryTextField.setText(currentReservation.getRoomCategory().getCategoryName());

        roomNumberTextField.setText(String.valueOf(currentReservation.getRoomNumber()));

        roomPriceText.setText(String.valueOf(
                ERoomCategory.valueOf(roomCategoryTextField.getText().toUpperCase()).getCategoryPrice()));

        // Wird hier für später gespeichert
        guestID = guestDetails.get(0);
        roomID = guestDetails.get(2);
    }

    private void setupPaymentTypeComboBox() {
        ObservableList<String> paymentTypeObservableList = FXCollections.observableArrayList(
                EPaymentType.CREDITCARD.getPaymentType(),
                EPaymentType.DEBITCARD.getPaymentType(),
                EPaymentType.PREPAYMENT.getPaymentType());

        paymentTypeComboBox.setItems(paymentTypeObservableList);
    }

    @FXML
    public void openExistingProfileWindow() {
        iDNumberTextField.setText("");
        Stage newStage = GeneralMethods.openWindowAsNewStage("ReadGuestProfiles", "Profile durchsuchen");
        try {
            newStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (newStage.getUserData() != null) {
                        Long guestIDNumber = (Long) newStage.getUserData();
                        if (guestIDNumber != null) {
                            iDNumberTextField.setText(String.valueOf(guestIDNumber));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelOrRefresh(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        event.consume();
        stage.hide();
    }

}