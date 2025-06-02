package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

public interface SellInState {
    int calculateSellIn(Product product, int sellIn);

    int calculateSellIn(Product product, int sellIn, LocalDate date);

}
