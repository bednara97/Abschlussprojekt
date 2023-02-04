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
import com.bednara.application.Guest;
import com.bednara.application.Main;
import com.bednara.sql.GuestProfilesSQLHandler;

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

public class GuestProfilesController implements Initializable {

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
    private Text userNameText;

    @FXML
    private MFXTextField iDNumberTextField;

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
    private MFXTableView<Guest> guestDataTable;

    private ObservableList<Guest> guestObservableList = FXCollections.observableArrayList();

    @FXML
    public void openDashboardWindow() {
        GeneralMethods.changeWindow("Dashboard");
    }

    @FXML
    public void openFrontDeskWindow() {
        GeneralMethods.changeWindow("FrontDesk");
    }

    @FXML
    public void openChangePasswordWindow() {
        GeneralMethods.openWindowAsNewStage("ChangePassword", "Passwort ändern");
    }

    @FXML
    public void search() {
        String iDNumber = iDNumberTextField.getText();
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();

        List<String> detailsToSearch = new ArrayList<String>();

        if (!lastName.isBlank() || !firstName.isBlank() || !iDNumber.isBlank()) {
            guestObservableList.clear();
            detailsToSearch = GuestProfilesSQLHandler.getSearchedGuestDetails(lastName.trim(), firstName.trim(),
                    iDNumber.trim());
            addGuestsToTableView(detailsToSearch);
            guestDataTable.setItems(guestObservableList);
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Suchfelder dürfen nicht leer sein!")
                    .position(Pos.CENTER)
                    .showWarning();
        }
    }

    public void addGuestsToTableView(List<String> guestDetails) {
        for (int i = 0; i < guestDetails.size(); i++) {
            Guest guest = new Guest(0, null, null, null, null);
            guest.setIDNumber(Long.parseLong(guestDetails.get(i)));
            guest.setLastName(guestDetails.get(i + 1));
            guest.setFirstName(guestDetails.get(i + 2));
            guest.setBirthDate(Timestamp.valueOf(guestDetails.get(i + 3)));
            guest.setPhoneNumber(guestDetails.get(i + 4));
            guest.setMailAddress(guestDetails.get(i + 5));
            guest.setStreetName(guestDetails.get(i + 6));
            guest.setStreetNumber(Integer.parseInt(guestDetails.get(i + 7)));
            guest.setApartmentNumber(Integer.parseInt(guestDetails.get(i + 8)));
            guest.setZipCode(guestDetails.get(i + 9));
            guest.setCity(guestDetails.get(i + 10));
            guest.setCountry(guestDetails.get(i + 11));

            i += 11;

            guestObservableList.add(guest);
        }
    }

    @FXML
    public void clear() {
        lastNameTextField.setText("");
        firstNameTextField.setText("");
        iDNumberTextField.setText("");
        guestObservableList.clear();
        addGuestsToTableView(GuestProfilesSQLHandler.getGuestDetailsForTableView());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameText.setText(Main.UserDataHolder.userName);
        setupGuestTableColumns();
        setupSidebarAnimation();
        setupSidebarIcons();
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

    @SuppressWarnings("unchecked") // Wegen des Filters
    public void setupGuestTableColumns() {

        MFXTableColumn<Guest> iDNumberColumn = new MFXTableColumn<>("Ausweis Nr.", false,
                Comparator.comparing(Guest::getIDNumber));

        MFXTableColumn<Guest> lastNameColumn = new MFXTableColumn<>("Nachname", false,
                Comparator.comparing(Guest::getLastName));

        MFXTableColumn<Guest> firstNameColumn = new MFXTableColumn<>("Vorname", false,
                Comparator.comparing(Guest::getFirstName));

        MFXTableColumn<Guest> cityColumn = new MFXTableColumn<>("Stadt", false,
                Comparator.comparing(Guest::getCity));

        MFXTableColumn<Guest> countryColumn = new MFXTableColumn<>("Land", false,
                Comparator.comparing(Guest::getCountry));

        addGuestsToTableView(GuestProfilesSQLHandler.getGuestDetailsForTableView());

         // Column Sizes ändern
         iDNumberColumn.setMinWidth(130);
         iDNumberColumn.setAlignment(Pos.CENTER_LEFT);
 
         lastNameColumn.setMinWidth(100);
         lastNameColumn.setAlignment(Pos.CENTER_LEFT);
 
         firstNameColumn.setMinWidth(100);
         firstNameColumn.setAlignment(Pos.CENTER_LEFT);
 
         cityColumn.setMinWidth(130);
         cityColumn.setAlignment(Pos.CENTER_LEFT);
 
         countryColumn.setMinWidth(190);
         countryColumn.setAlignment(Pos.CENTER_LEFT);

        // Filters:
        guestDataTable.getFilters().addAll(new StringFilter<>("Nachname",
                Guest::getLastName));
        guestDataTable.getFilters().addAll(new StringFilter<>("Vorname",
                Guest::getFirstName));
        guestDataTable.getFilters().addAll(new StringFilter<>("Stadt",
                Guest::getCity));
        guestDataTable.getFilters().addAll(new StringFilter<>("Land",
                Guest::getCountry));

        // Set Colums:
        iDNumberColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getIDNumber));
        lastNameColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getLastName));
        firstNameColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getFirstName));
        cityColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getCity));
        countryColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getCountry));

        guestDataTable.getTableColumns().addAll(iDNumberColumn, lastNameColumn,
                firstNameColumn, cityColumn, countryColumn);

        guestDataTable.setItems(guestObservableList);

    }

    @FXML
    public void createNewGuestWindow() {
        Stage newStage = GeneralMethods.openWindowAsNewStage("GuestProfileHandling", "Profil anlegen");
        newStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clear();
            }
        });
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

    @FXML
    public void openExistingGuestWindow() {
        if (!guestDataTable.getSelectionModel().getSelectedValues().isEmpty()) {
            Guest selectedGuest = guestDataTable.getSelectionModel().getSelectedValues().get(0);
            Stage newStage = GeneralMethods.openWindowAsNewStage("GuestProfileHandling",
                    "Gast bearbeiten: " + selectedGuest.getLastName() + " "
                            + selectedGuest.getFirstName(),
                    selectedGuest);

            newStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    clear();
                }
            });
        } else {
            Notifications.create()
                    .title("Achtung")
                    .text("Bitte Gast auswählen.")
                    .position(Pos.CENTER)
                    .showWarning();
        }

    }

}