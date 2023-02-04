package com.bednara.application;

import java.sql.Timestamp;

public class Guest extends Person {

    private long iDNumber = 0;
    private String confirmationNumber = "";

    public Guest(String firstName, String lastName, Timestamp birthDate, String phoneNumber, String mailAddress,
            String streetName, int streetNumber, int apartmentNumber, String zipCode, String city, String country,
            long iDNumber, String confirmationNumber) {
        super(firstName, lastName, birthDate, phoneNumber, mailAddress, streetName, streetNumber, apartmentNumber,
                zipCode, city, country);
        this.confirmationNumber = confirmationNumber;
        super.setLastName(lastName);
        super.setFirstName(firstName);
    }

    public Guest(long iDNumber, String lastName, String firstName, String city, String country) {
        this.iDNumber = iDNumber;
        super.setLastName(lastName);
        super.setFirstName(firstName);
        super.setCity(city);
        super.setCountry(country);
    }

    public Guest() {
        // Default
    }

    public long getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(long iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getLastName() {
        return super.getLastName();
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getCity() {
        return super.getCity();
    }

    public void setCity(String city) {
        super.setCity(city);
    }

    public String getCountry() {
        return super.getCountry();
    }

    public void setCountry(String country) {
        super.setCountry(country);
    }

}
