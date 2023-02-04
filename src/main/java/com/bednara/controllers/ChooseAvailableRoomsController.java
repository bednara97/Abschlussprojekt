package com.bednara.controllers;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.bednara.application.GeneralMethods;
import com.bednara.application.Room;
import com.bednara.enums.ERoomCategory;
import com.bednara.enums.ERoomStatus;
import com.bednara.sql.ChooseAvailableRoomsSQLHandler;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ChooseAvailableRoomsController implements Initializable {

	@FXML
	private MFXTableView<Room> availableRoomsTable;

	private ObservableList<Room> availableRoomsObservableList = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked") // Wegen des Filters
	public void setupAvailableRoomsTable(String arrivalDate, String departureDate) {

		MFXTableColumn<Room> roomNumberColumn = new MFXTableColumn<>("Zimmer", false,
				Comparator.comparing(Room::getRoomNumber));

		MFXTableColumn<Room> roomCategoryColumn = new MFXTableColumn<>("Kategorie", false,
				Comparator.comparing(Room::getRoomCategoryAbbreviation));

		MFXTableColumn<Room> roomStatusColumn = new MFXTableColumn<>("Status", false,
				Comparator.comparing(Room::getRoomStatusAbbreviation));

		MFXTableColumn<Room> roomPriceColumn = new MFXTableColumn<>("Preis", false,
				Comparator.comparing(Room::getRoomPrice));

		addRoomsToTableView(
				ChooseAvailableRoomsSQLHandler.getRoomDetailsForTableView(arrivalDate, departureDate));

		// Filter:
		availableRoomsTable.getFilters().addAll(new IntegerFilter<>("Zimmer",
				Room::getRoomNumber));
		availableRoomsTable.getFilters().addAll(new StringFilter<>("Kategorie",
				Room::getRoomCategoryAbbreviation));
		availableRoomsTable.getFilters().addAll(new StringFilter<>("Status",
				Room::getRoomStatusAbbreviation));
		availableRoomsTable.getFilters().addAll(new DoubleFilter<>("Preis",
				Room::getRoomPrice));

		// Set Columns:
		roomNumberColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Room::getRoomNumber));
		roomCategoryColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Room::getRoomCategoryAbbreviation));
		roomStatusColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Room::getRoomStatusAbbreviation));
		roomPriceColumn.setRowCellFactory(guest -> new MFXTableRowCell<>(Room::getRoomPrice));

		availableRoomsTable.getTableColumns().addAll(roomNumberColumn, roomCategoryColumn,
				roomStatusColumn, roomPriceColumn);

		availableRoomsTable.setItems(availableRoomsObservableList);
	}

	public void addRoomsToTableView(List<String> roomDetails) {
		for (int i = 0; i < roomDetails.size(); i++) {
			Room room = new Room(0.0, 0);
			room.setRoomNumber(Integer.parseInt(roomDetails.get(i)));
			room.setRoomCategory(ERoomCategory.valueOf(roomDetails.get(i + 1)));
			room.setRoomStatus(ERoomStatus.valueOf(roomDetails.get(i + 2)));
			room.setRoomPrice(Double.parseDouble(roomDetails.get(i + 3)));

			i += 3;

			availableRoomsObservableList.add(room);
		}
	}

	@FXML
	public void confirm(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		if (!availableRoomsTable.getSelectionModel().getSelectedValues().isEmpty()) {
			stage.setUserData(availableRoomsTable.getSelectionModel().getSelectedValues().get(0));
			event.consume();
			stage.hide();
		} else {
			Notifications.create()
					.title("Achtung")
					.text("Bitte Zimmer auswählen")
					.position(Pos.CENTER)
					.showWarning();
		}
	}

	@FXML
	public void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setUserData(null);
		event.consume();
		stage.hide();
	}

	@SuppressWarnings("unchecked") // Wegen des Objects
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Object[] currentReservationDatesObject = (Object[]) GeneralMethods.getCurrentStage().getUserData();
		List<String> currentReservationDates = List.class.cast(currentReservationDatesObject[0]);

		setupAvailableRoomsTable(currentReservationDates.get(0), currentReservationDates.get(1));

		GeneralMethods.getCurrentStage().setUserData(null);
	}

}
