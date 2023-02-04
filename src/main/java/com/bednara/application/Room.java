package com.bednara.application;

import com.bednara.enums.ERoomCategory;
import com.bednara.enums.ERoomStatus;

public class Room {

    private double roomPrice = 0;
    private int roomNumber = 0;
    ERoomCategory roomCategory;
    ERoomStatus roomStatus;

    public Room(double roomPrice, int roomNumber) {
        this.roomPrice = roomPrice;
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
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

}
