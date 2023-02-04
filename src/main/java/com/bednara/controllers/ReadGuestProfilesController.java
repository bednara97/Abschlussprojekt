package com.bednara.controllers;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Guest;
import com.bednara.sql.GuestProfilesSQLHandler;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ReadGuestProfilesController implements Initializable {

	@FXML
	private MFXTableView<Guest> readGuestDataTable;

	private ObservableList<Guest> guestObservableList = FXCollections.observableArrayList();

	public void addGuestsToTableView(List<String> guestDetails) {
		for (int i = 0; i < guestDetails.size(); i++) {
			Guest guest = new Guest(0, null, null, null, null);
			guest.setIDNumber(Long.parseLong(guestDetails.get(i)));
			guest.setLastName(guestDetails.get(i + 1));
			guest.setFirstName(guestDetails.get(i + 2));

			i += 11;

			guestObservableList.add(guest);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupGuestTableColumns();
		GeneralMethods.getCurrentStage().setUserData(null);
	}

	@SuppressWarnings("unchecked") // Wegen des Filters
	public void setupGuestTableColumns() {

		MFXTableColumn<Guest> iDNumberColumn = new MFXTableColumn<>("ID-Nummer", false,
				Comparator.comparing(Guest::getIDNumber));

		MFXTableColumn<Guest> lastNameColumn = new MFXTableColumn<>("Nachname", false,
				Comparator.comparing(Guest::getLastName));

		MFXTableColumn<Guest> firstNameColumn = new MFXTableColumn<>("Vorname", false,
				Comparator.comparing(Guest::getFirstName));

		addGuestsToTableView(GuestProfilesSQLHandler.getGuestDetailsForTableView());

		// Filters:
		readGuestDataTable.getFilters().addAll(new LongFilter<>("ID-Nummer",
				Guest::getIDNumber));
		readGuestDataTable.getFilters().addAll(new StringFilter<>("Nachname",
				Guest::getLastName));
		readGuestDataTable.getFilters().addAll(new StringFilter<>("Vorname",
				Guest::getFirstName));

		// Set Columns:
		iDNumberColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getIDNumber));
		lastNameColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getLastName));
		firstNameColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Guest::getFirstName));

		readGuestDataTable.getTableColumns().addAll(iDNumberColumn, lastNameColumn,
				firstNameColumn);

		readGuestDataTable.setItems(guestObservableList);
	}

	@FXML
	public void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setUserData(null);
		event.consume();
		stage.hide();
	}

	@FXML
	public void confirm(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		if (!readGuestDataTable.getSelectionModel().getSelectedValues().isEmpty()) {
			stage.setUserData(readGuestDataTable.getSelectionModel().getSelectedValues().get(0).getIDNumber());
			event.consume();
			stage.hide();
		} else {
			Notifications.create()
					.title("Achtung")
					.text("Bitte Gast auswählen!")
					.position(Pos.CENTER)
					.showWarning();
		}
	}

}
