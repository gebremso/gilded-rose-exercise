package com.solo.gilded.rose.inventory;

import com.solo.gilded.rose.entity.Product;
import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    //Z -Zero when input date is the same as purchased date
    @Test
    public void testDatePassZero() throws UnsupportedProductException {
        LocalDate date = LocalDate.of(2025,5,25);
        Product product = new Product("Normal", LocalDate.of(2025,5,25));
        assertEquals(0, product.daysPassed(date));
    }

    //O -One  1 day before purchasing date
    @Test
    public void testOneDayAfterPurchaseDate() throws UnsupportedProductException {
        LocalDate date = LocalDate.of(2025, 5, 26);
        Product product = new Product("Normal", LocalDate.of(2025,5, 25));
        assertEquals(1, product.daysPassed(date));
    }

    //M - Many days pass the purchasing date
    @Test
    public void testManyDayPassPurchaseDate() throws UnsupportedProductException {
        LocalDate date = LocalDate.of(2025, 5, 26);
        Product product = new Product("Normal", LocalDate.of(2025,5, 20));
        assertEquals(6, product.daysPassed(date));
    }

    //B - Boundary after year has passed
    @Test
    public void testYearPassedPurchaseDate() throws UnsupportedProductException {
        LocalDate date = LocalDate.of(2025,5, 29);
        Product product = new Product("Normal", LocalDate.of(2024,5, 27));
        assertEquals(367,product.daysPassed(date));
    }

    //I - Interface valid interaction with the method
    @Test
    public void testCoupleOfDaysPassPurchaseDate() throws UnsupportedProductException {
        Product product = new Product("Normal");
        assertEquals(3, product.daysPassed(LocalDate.now().plusDays(3)));
    }

    //E - Exception validate when the input is null
    @Test
    public void testNullInputForDate() throws UnsupportedProductException {
        assertThrows(UnsupportedProductException.class,()->new Product("Phone"));
        Product product = new Product("Normal");
        assertThrows(IllegalArgumentException.class, ()->product.daysPassed(null));
    }

    //E - Exception validate when the input is before the purchase date
    @Test
    public void testInputBeforePurchaseDate() throws UnsupportedProductException {
        Product product = new Product("Normal", LocalDate.of(2025,5,26));
        assertThrows(IllegalArgumentException.class, ()->product.daysPassed(LocalDate.of(2025,5,25)));
    }

}