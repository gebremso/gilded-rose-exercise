package com.solo.gilded.rose.inventory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    private final String name;
    private final LocalDate purchasedDate;

    public Product(String name) {
        this.name = name;
        this.purchasedDate = LocalDate.now();
    }

    public Product(String name, LocalDate purchasedDate) {
        this.name = name;
        this.purchasedDate = purchasedDate;
    }

    public int daysPassed(LocalDate date){
        if(date == null || date.isBefore(purchasedDate))
            throw new IllegalArgumentException("The wrong date format is provided.");

        return (int) ChronoUnit.DAYS.between(purchasedDate, date);
    }
}
