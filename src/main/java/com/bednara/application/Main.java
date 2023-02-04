package com.bednara.application;

import org.mindrot.jbcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene scene;

	public static class UserDataHolder {
		public static String userName = "";
		public static String userFirstName = "";
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("/com/bednara/windows/LoginWindow.fxml"));
			scene = new Scene(root);
			primaryStage.getIcons().add(new Image(getClass().getResource("/com/bednara/images/icon.png").toExternalForm()));
			primaryStage.setTitle("myHotel");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// fillDatabase Methode und Aufruf nur einkommentieren für das automatisierte
		// Hinzufügen von Datensätzen:
		// fillDatabase();
		
		launch(args);
	}

	// private static void fillDatabase() {
	// Alert alert = new Alert(AlertType.CONFIRMATION,
	// "Sind Sie sicher, dass Sie die Datenbank befüllen möchten?",
	// ButtonType.YES, ButtonType.NO);
	// alert.showAndWait();

	// if (alert.getResult().toString().contains("YES")) {
	// FakeDataInserter.insertPersons();
	// FakeDataInserter.insertEmployees();
	// FakeDataInserter.insertGuests();
	// FakeDataInserter.insertRooms();
	// FakeDataInserter.insertReservations();
	// }
	// }

	public static Scene getScene() {
		return scene;
	}

}