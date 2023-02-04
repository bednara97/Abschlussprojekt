package com.bednara.controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Main;
import com.bednara.application.Reservation;
import com.bednara.enums.EPaymentType;
import com.bednara.enums.EReservationStatus;
import com.bednara.enums.ERoomCategory;
import com.bednara.enums.ERoomStatus;
import com.bednara.sql.FrontDeskSQLHandler;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FrontDeskController implements Initializable {

    @FXML
    private Text userNameText;

    @FXML
    private Text dashboardText;

    @FXML
    private Text frontDeskText;

    @FXML
    private Text guestProfilesText;

    @FXML
    private Text changePasswordText;

    @FXML
    private Text logoutText;

    @FXML
    private MFXTextField confirmationNumberTextField;

    @FXML
    private MFXTextField lastNameTextField;

    @FXML
    private MFXTextField firstNameTextField;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button frontDeskButton;

    @FXML
    private Button guestProfilesButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button userNameButton;

    @FXML
    private Button logoButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    private AnchorPane dashboardAnchorPane;

    @FXML
    private AnchorPane frontDeskAnchorPane;

    @FXML
    private AnchorPane guestProfilesAnchorPane;

    @FXML
    private AnchorPane changePasswordAnchorPane;

    @FXML
    private AnchorPane logoutAnchorPane;

    @FXML
    private MFXTableView<Reservation> reservationDataTable;

    private ObservableList<Reservation> reservationObservableList = FXCollections.observableArrayList();

    @FXML
    public void openDashboardWindow() {
        GeneralMethods.changeWindow("Dashboard");
    }

    @FXML
    public void openGuestProfilesWindow() {
        GeneralMethods.changeWindow("GuestProfiles");
    }

    @FXML
    public void openChangePasswordWindow() {
        GeneralMethods.openWindowAsNewStage("ChangePassword", "Passwort ändern");
    }

    @FXML
    public void createNewReservationWindow() {
        Stage newStage = GeneralMethods.openWindowAsNewStage("ReservationHandling", "Reservierung anlegen");
        newStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clear();
            }
        });
    }

    @FXML
    public void search() {
        String confirmationNumber = confirmationNumberTextField.getText();
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        List<String> detailsToSearch = new ArrayList<String>();

        if (!confirmationNumber.isBlank() || !lastName.isBlank() || !firstName.isBlank()) {
            reservationObservableList.clear();
            detailsToSearch = FrontDeskSQLHandler.getSearchedReservationDetails(confirmationNumber.trim(),
                    lastName.trim(),
                    firstName.trim());
            addReservationsToTableView(detailsToSearch);
            reservationDataTable.setItems(reservationObservableList);
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Suchfelder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();
        }
    }

    @SuppressWarnings("unchecked") // Wegen des Objects (UserData)
    @FXML
    public void openAdvancedSearchWindow() {
        Stage newStage = GeneralMethods.openWindowAsNewStage("AdvancedSearch", "Erweiterte Suche");
        // Listener:
        try {
            newStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

                    if (newStage.getUserData() != null) {
                        List<String> searchedAdvancedDetails = (List<String>) newStage.getUserData();
                        if (searchedAdvancedDetails != null && !searchedAdvancedDetails.isEmpty()) {
                            reservationObservableList.clear();
                            addReservationsToTableView(searchedAdvancedDetails);
                            reservationDataTable.setItems(reservationObservableList);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void clear() {
        confirmationNumberTextField.setText("");
        lastNameTextField.setText("");
        firstNameTextField.setText("");
        reservationObservableList.clear();
        addReservationsToTableView(FrontDeskSQLHandler.getReservationDetailsForTableView());
    }

    @FXML
    public void openExistingReservationWindow() {
        if (!reservationDataTable.getSelectionModel().getSelectedValues().isEmpty()) {
            Reservation selectedReservation = reservationDataTable.getSelectionModel().getSelectedValues().get(0);
            Stage newStage = GeneralMethods.openWindowAsNewStage("ReservationHandling",
                    "Reservierung bearbeiten: " + selectedReservation.getLastName() + " "
                            + selectedReservation.getFirstName(),
                    selectedReservation);

            newStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    clear();
                }
            });
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Bitte Reservierung auswählen.")
                    .position(Pos.CENTER)
                    .showWarning();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameText.setText(Main.UserDataHolder.userName);
        setupReservationDataTable();
        setupSidebarAnimation();
        setupSidebarIcons();
    }

    public void addReservationsToTableView(List<String> allReservationDetails) {
        for (int i = 0; i < allReservationDetails.size(); i++) {
            Reservation reservation = new Reservation(null, null, null, 0, null, null, null, null, null, null);
            reservation.setConfirmationNumber(allReservationDetails.get(i));
            reservation.setLastName(allReservationDetails.get(i + 1));
            reservation.setFirstName(allReservationDetails.get(i + 2));
            reservation.setRoomNumber(Integer.parseInt(allReservationDetails.get(i + 3)));
            reservation.setRoomCategory(ERoomCategory.valueOf(allReservationDetails.get(i + 4)));
            reservation.setRoomStatus(ERoomStatus.valueOf(allReservationDetails.get(i + 5)));
            reservation.setArrivalDate(Timestamp.valueOf(allReservationDetails.get(i + 6)));
            reservation.setDepartureDate(Timestamp.valueOf(allReservationDetails.get(i + 7)));
            reservation.setPaymentType(EPaymentType.valueOf(allReservationDetails.get(i + 8)));
            reservation.setReservationStatus(EReservationStatus.valueOf(allReservationDetails.get(i + 9)));

            i += 9;

            reservationObservableList.add(reservation);
        }
    }

    @SuppressWarnings("unchecked") // Wegen des Filters
    public void setupReservationDataTable() {

        MFXTableColumn<Reservation> confirmationNumberColumn = new MFXTableColumn<>("Buchungs Nr.", false,
                Comparator.comparing(Reservation::getConfirmationNumber));
        MFXTableColumn<Reservation> lastNameColumn = new MFXTableColumn<>("Nachname", true,
                Comparator.comparing(Reservation::getLastName));
        MFXTableColumn<Reservation> firstNameColumn = new MFXTableColumn<>("Vorname", true,
                Comparator.comparing(Reservation::getFirstName));
        MFXTableColumn<Reservation> roomNumberColumn = new MFXTableColumn<>("Zimmer", true,
                Comparator.comparing(Reservation::getRoomNumber));
        MFXTableColumn<Reservation> roomCategoryColumn = new MFXTableColumn<>("Kategorie", true,
                Comparator.comparing(Reservation::getRoomCategoryAbbreviation));
        MFXTableColumn<Reservation> roomStatusColumn = new MFXTableColumn<>("Status", true,
                Comparator.comparing(Reservation::getRoomStatusAbbreviation));
        MFXTableColumn<Reservation> reservationStatusColumn = new MFXTableColumn<>("C/O", true,
                Comparator.comparing(Reservation::getRoomStatusAbbreviation));
        MFXTableColumn<Reservation> arrivalDateColumn = new MFXTableColumn<>("Anreise", true,
                Comparator.comparing(Reservation::getFormattedArrivalDate));
        MFXTableColumn<Reservation> departureDateColumn = new MFXTableColumn<>("Abreise", true,
                Comparator.comparing(Reservation::getFormattedDepartureDate));
        addReservationsToTableView(FrontDeskSQLHandler.getReservationDetailsForTableView());

        // Column Sizes ändern
        confirmationNumberColumn.setMinWidth(150);
        confirmationNumberColumn.setAlignment(Pos.CENTER_LEFT);

        roomNumberColumn.setMinWidth(60);
        roomNumberColumn.setAlignment(Pos.CENTER_LEFT);

        roomCategoryColumn.setMinWidth(60);
        roomCategoryColumn.setAlignment(Pos.CENTER_LEFT);

        roomStatusColumn.setMinWidth(60);
        roomStatusColumn.setAlignment(Pos.CENTER_LEFT);

        reservationStatusColumn.setMinWidth(60);
        reservationStatusColumn.setAlignment(Pos.CENTER_LEFT);

        // Filters:
        reservationDataTable.getFilters().addAll(new StringFilter<>("Nachname", Reservation::getLastName));
        reservationDataTable.getFilters().addAll(new StringFilter<>("Vorname", Reservation::getFirstName));

        // Set Columns:
        confirmationNumberColumn
                .setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getConfirmationNumber));
        lastNameColumn.setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getLastName));
        firstNameColumn.setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getFirstName));
        roomNumberColumn.setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getRoomNumber));
        roomCategoryColumn
                .setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getRoomCategoryAbbreviation));
        roomStatusColumn
                .setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getRoomStatusAbbreviation));
        reservationStatusColumn
                .setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getReservationStatusAbbreviation));
        arrivalDateColumn.setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getFormattedArrivalDate));
        departureDateColumn
                .setRowCellFactory(reservation -> new MFXTableRowCell<>(Reservation::getFormattedDepartureDate));

        reservationDataTable.getTableColumns().addAll(confirmationNumberColumn, lastNameColumn, firstNameColumn,
                roomNumberColumn, roomCategoryColumn, roomStatusColumn, reservationStatusColumn, arrivalDateColumn,
                departureDateColumn);

        reservationDataTable.setItems(reservationObservableList);
    }

    private void setupSidebarAnimation() {
        dashboardAnchorPane.hoverProperty()
                .addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        dashboardAnchorPane.setStyle("-fx-background-color: #ffffff");
                        dashboardText.setFill(Paint.valueOf("#7a0ed9"));
                        GeneralMethods.setButtonPicture(dashboardButton, 24d, 24d, "DashboardPurple");
                    } else {
                        dashboardAnchorPane.setStyle("-fx-background-color: #7a0ed9");
                        dashboardText.setFill(Paint.valueOf("#ffffff"));
                        GeneralMethods.setButtonPicture(dashboardButton, 24d, 24d, "DashboardWhite");
                    }
                });

        frontDeskAnchorPane.hoverProperty()
                .addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        frontDeskAnchorPane.setStyle("-fx-background-color: #ffffff");
                        frontDeskText.setFill(Paint.valueOf("#7a0ed9"));
                        GeneralMethods.setButtonPicture(frontDeskButton, 24d, 24d, "HotelPurple");
                    } else {
                        frontDeskAnchorPane.setStyle("-fx-background-color: #7a0ed9");
                        frontDeskText.setFill(Paint.valueOf("#ffffff"));
                        GeneralMethods.setButtonPicture(frontDeskButton, 24d, 24d, "HotelWhite");
                    }
                });

        guestProfilesAnchorPane.hoverProperty()
                .addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        guestProfilesAnchorPane.setStyle("-fx-background-color: #ffffff");
                        guestProfilesText.setFill(Paint.valueOf("#7a0ed9"));
                        GeneralMethods.setButtonPicture(guestProfilesButton, 24d, 24d, "GuestProfilesPurple");
                    } else {
                        guestProfilesAnchorPane.setStyle("-fx-background-color: #7a0ed9");
                        guestProfilesText.setFill(Paint.valueOf("#ffffff"));
                        GeneralMethods.setButtonPicture(guestProfilesButton, 24d, 24d, "GuestProfilesWhite");
                    }
                });

        changePasswordAnchorPane.hoverProperty()
                .addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        changePasswordAnchorPane.setStyle("-fx-background-color: #ffffff");
                        changePasswordText.setFill(Paint.valueOf("#7a0ed9"));
                        GeneralMethods.setButtonPicture(changePasswordButton, 24d, 24d, "ChangePasswordPurple");
                    } else {
                        changePasswordAnchorPane.setStyle("-fx-background-color: #7a0ed9");
                        changePasswordText.setFill(Paint.valueOf("#ffffff"));
                        GeneralMethods.setButtonPicture(changePasswordButton, 24d, 24d, "ChangePasswordWhite");
                    }
                });

        logoutAnchorPane.hoverProperty()
                .addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        logoutAnchorPane.setStyle("-fx-background-color: #ffffff");
                        logoutText.setFill(Paint.valueOf("#7a0ed9"));
                        GeneralMethods.setButtonPicture(logoutButton, 24d, 24d, "LogoutPurple");
                    } else {
                        logoutAnchorPane.setStyle("-fx-background-color: #7a0ed9");
                        logoutText.setFill(Paint.valueOf("#ffffff"));
                        GeneralMethods.setButtonPicture(logoutButton, 24d, 24d, "LogoutWhite");
                    }
                });
    }

    private void setupSidebarIcons() {
        Map<Button, String> sidebarButtons = new HashMap<Button, String>();
        sidebarButtons.put(logoButton, "Logo");
        sidebarButtons.put(dashboardButton, "DashboardWhite");
        sidebarButtons.put(frontDeskButton, "HotelWhite");
        sidebarButtons.put(guestProfilesButton, "GuestProfilesWhite");
        sidebarButtons.put(changePasswordButton, "ChangePasswordWhite");
        sidebarButtons.put(logoutButton, "LogoutWhite");
        sidebarButtons.put(userNameButton, "User");
        GeneralMethods.setupSidebarIcons(sidebarButtons);

        GeneralMethods.setButtonPicture(searchButton, 24d, 24d, "SearchPurple");
        GeneralMethods.setButtonPicture(refreshButton, 16d, 16d, "RefreshPurple");
    }

    @FXML
    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Sind Sie sicher, dass Sie sich ausloggen möchten?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult().toString().contains("YES")) {
            GeneralMethods.changeWindow("Login");
        }
    }

}