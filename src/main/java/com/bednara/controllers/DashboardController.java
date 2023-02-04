package com.bednara.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Main;
import com.bednara.sql.DashboardSQLHandler;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class DashboardController implements Initializable {

   @FXML
   private Text firstNameText;

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
   private MFXButton expectedArrivalsButton;

   @FXML
   private MFXButton expectedDeparturesButton;

   @FXML
   private MFXButton guestInhouseButton;

   @FXML
   private MFXButton cleanRoomsButton;

   @FXML
   private MFXButton availableRoomsButton;

   @FXML
   private MFXButton outOfOrderRoomsButton;

   @FXML
   private Button dashboardButton;

   @FXML
   private Button frontDeskButton;

   @FXML
   private Button guestProfilesButton;

   @FXML
   private Button changePasswordButton;

   @FXML
   private Button userNameButton;

   @FXML
   private Button logoutButton;

   @FXML
   private Button logoButton;

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
   public void openFrontDeskWindow() {
      GeneralMethods.changeWindow("FrontDesk");
   }

   @FXML
   public void openGuestProfilesWindow() {
      GeneralMethods.changeWindow("GuestProfiles");
   }

   @FXML
   public void openChangePasswordWindow() {
      GeneralMethods.openWindowAsNewStage("ChangePassword", "Passwort ändern");
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      setupSidebarIcons();
      setupSidebarAnimation();
      setupDashboardLiveButtons();

      firstNameText.setText("Willkommen, "
            + DashboardSQLHandler.getEmployeeFirstNameFromDatabase(Main.UserDataHolder.userName) + "!");
      userNameText.setText(Main.UserDataHolder.userName);
   }

   private void setupDashboardLiveButtons() {
      expectedArrivalsButton.setText(DashboardSQLHandler.getExpectedArrivalsFromDatabase());
      expectedDeparturesButton.setText(DashboardSQLHandler.getExpectedDeparturesFromDatabase());
      guestInhouseButton.setText(DashboardSQLHandler.getOccupiedRoomsFromDatabase());
      cleanRoomsButton.setText(DashboardSQLHandler.getCleanRoomsFromDatabase());
      availableRoomsButton.setText(DashboardSQLHandler.getAvailableRoomsFromDatabase());
      outOfOrderRoomsButton.setText(DashboardSQLHandler.getOutOfOrderRoomsFromDatabase());
   }

   private void setupSidebarAnimation() {
      dashboardAnchorPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
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

      frontDeskAnchorPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
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

      logoutAnchorPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
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