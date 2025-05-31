package com.solo.gilded.rose.entity;

import com.solo.gilded.rose.exceptions.UnsupportedProductException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    private final String name;
    private final LocalDate purchasedDate;
    private final ProductType productType;

    public Product(String name) throws UnsupportedProductException {
        this(name,ProductType.valueFrom(name),LocalDate.now());
    }

    public Product(String name, ProductType productType) {
        this(name,productType,LocalDate.now());
    }

    public Product(String name, LocalDate purchasedDate) throws UnsupportedProductException {
      this(name,ProductType.valueFrom(name),purchasedDate);
    }

    public Product(String name,ProductType productType, LocalDate purchasedDate) {
        this.name = name;
        this.purchasedDate = purchasedDate;
        this.productType = productType;
    }

    public int daysPassed(LocalDate date){
        if(date == null || date.isBefore(purchasedDate))
            throw new IllegalArgumentException("The wrong date format is provided.");

        return (int) ChronoUnit.DAYS.between(purchasedDate, date);
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }
}
