package com.bednara.application;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.bednara.enums.EPaymentType;
import com.bednara.enums.EReservationStatus;
import com.bednara.enums.ERoomCategory;
import com.bednara.enums.ERoomStatus;

public class Reservation {

    private Timestamp arrivalDate;
    private Timestamp departureDate;
    private String confirmationNumber = "";
    private String lastName = "";
    private String firstName = "";
    private int roomNumber = 0;
    private ERoomCategory roomCategory = ERoomCategory.REGULAR;
    private ERoomStatus roomStatus = ERoomStatus.CLEAN;
    private EPaymentType paymentType = EPaymentType.CREDITCARD;
    private EReservationStatus reservationStatus = EReservationStatus.UNKNOWN;

    public Reservation(String confirmationNumber, String lastName, String firstName, int roomNumber,
            ERoomCategory roomCategory, ERoomStatus roomStatus, Timestamp arrivalDate, Timestamp departureDate,
            EPaymentType paymentType, EReservationStatus reservationStatus) {
        this.confirmationNumber = confirmationNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.roomStatus = roomStatus;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.paymentType = paymentType;
        this.reservationStatus = reservationStatus;
    }

    public Reservation() {
        // Default
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getFormattedArrivalDate() {
        return arrivalDate.toLocalDateTime().toLocalDate();
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public LocalDate getFormattedDepartureDate() {
        return departureDate.toLocalDateTime().toLocalDate();
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ERoomCategory getRoomCategory() {
        return roomCategory;
    }

    public String getRoomCategoryAbbreviation() {
        return roomCategory.getCategoryAbbreviation();
    }

    public void setRoomCategory(ERoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public ERoomStatus getRoomStatus() {
        return roomStatus;
    }

    public String getRoomStatusAbbreviation() {
        return roomStatus.getStatusAbbreviation();
    }

    public void setRoomStatus(ERoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public EPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(EPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public EReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public String getReservationStatusAbbreviation() {
        return reservationStatus.getStatusAbbreviation();
    }

    public void setReservationStatus(EReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

}
