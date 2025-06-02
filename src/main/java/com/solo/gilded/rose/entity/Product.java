package com.solo.gilded.rose.entity;

import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate purchasedDate;
    private ProductType productType;

    protected Product() {
    }

    public Product(String name) throws UnsupportedProductException {
        this(name, ProductType.valueFrom(name), LocalDate.now());
    }

    public Product(String name, ProductType productType) {
        this(name, productType, LocalDate.now());
    }

    public Product(String name, LocalDate purchasedDate) throws UnsupportedProductException {
        this(name, ProductType.valueFrom(name), purchasedDate);
    }

    public Product(String name, ProductType productType, LocalDate purchasedDate) {
        this.name = name;
        this.purchasedDate = purchasedDate;
        this.productType = productType;
    }

    public int daysPassed(LocalDate date) throws IllegalArgumentException{
        if (date == null || date.isBefore(purchasedDate))
            throw new IllegalArgumentException("Date provided: '"+date+"' is null or is before the purchase date.");

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
