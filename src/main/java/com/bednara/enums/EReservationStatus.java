package com.bednara.enums;

public enum EReservationStatus {
    CHECKED_IN("IN"),
    CHECKED_OUT("OUT"),
    CANCELED("CNL"),
    DUE_OUT("DUE"),
    UNKNOWN("UNK");

    private final String statusAbbreviation;

    private EReservationStatus(String statusAbbreviation) {
        this.statusAbbreviation = statusAbbreviation;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public String getStatusAbbreviation() {
        return statusAbbreviation;
    }

}
