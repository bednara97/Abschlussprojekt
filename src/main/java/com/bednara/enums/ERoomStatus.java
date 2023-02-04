package com.bednara.enums;

public enum ERoomStatus {
    CLEAN("CLN"),
    DIRTY("DIR"),
    OCCUPIED("OCC"),
    DUEOUT("DUE"),
    OUT_OF_ORDER("N/A");

    private final String statusAbbreviation;

    private ERoomStatus(String statusAbbreviation) {
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
