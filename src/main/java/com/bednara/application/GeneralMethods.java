package com.bednara.application;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneralMethods {

	public static Stage currentStage;

	public static void setupSidebarIcons(Map<Button, String> sidebarButtons) {
		for (var i : sidebarButtons.entrySet()) {
			if (i.getValue().equals("Logo")) {
				setButtonPicture(i.getKey(), 135d, 101d, i.getValue());
			} else {
				setButtonPicture(i.getKey(), 24d, 24d, i.getValue());
			}
		}
	}

	public static void setButtonPicture(Button button, double width, double height, String buttonPicture) {
		try {
			BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
			BackgroundImage backgroundImage = new BackgroundImage(
					new Image(GeneralMethods.class.getResource("/com/bednara/images/" + buttonPicture + ".png")
							.toExternalForm()),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
			Background background = new Background(backgroundImage);
			button.setBackground(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeWindow(String nextWindow) {
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			URL xmlUrl = GeneralMethods.class.getResource("/com/bednara/windows/" + nextWindow + "Window.fxml");
			loader.setLocation(xmlUrl);
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.getScene().setRoot(root);
	}

	@SafeVarargs // Für optionalen Parameter
	public static <T> Stage openWindowAsNewStage(String nextWindow, String windowTitle, T... object) {
		Stage newStage = new Stage();
		newStage.setUserData(object);

		setCurrentStage(newStage);
		try {
			Parent root = (Parent) FXMLLoader
					.load(GeneralMethods.class.getResource("/com/bednara/windows/" + nextWindow + "Window.fxml"));
			Scene scene = new Scene(root);
			newStage.setTitle(windowTitle);
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.getIcons()
					.add(new Image(GeneralMethods.class.getResource("/com/bednara/images/icon.png").toExternalForm()));
			newStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newStage;

	}

	public static Stage getCurrentStage() {
		return currentStage;
	}

	public static void setCurrentStage(Stage currentStage) {
		GeneralMethods.currentStage = currentStage;
	}

}
