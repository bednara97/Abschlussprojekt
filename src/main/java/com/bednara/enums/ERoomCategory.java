package com.bednara.enums;

public enum ERoomCategory {
    REGULAR(250.00, "Regular"),
    DELUXE(380.00, "Deluxe"),
    SUITE(510.00, "Suite");

    private final double categoryPrice;
    private final String categoryName;

    private ERoomCategory(double categoryPrice, String categoryName) {
        this.categoryPrice = categoryPrice;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public double getCategoryPrice() {
        return categoryPrice;
    }

    public String getCategoryAbbreviation() {
        return this.name().substring(0, 3);
    }

    public String getCategoryName() {
        return categoryName;
    }

}
