package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

public class SulfurasSellInState implements SellInState {
    @Override
    public int calculateSellIn(Product product, int sellIn) {
        return calculateSellIn(product, sellIn, LocalDate.now());
    }

    @Override
    public int calculateSellIn(Product product, int sellIn, LocalDate date) {
        return sellIn;
    }
}
