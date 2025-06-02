package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

import static com.solo.gilded.rose.util.StateCalculatorUtil.calculate;

public class ConjuredQualityState implements QualityState {


    @Override
    public int calculateQuality(Product product, int sellIn, int quality) {
        return 0;
    }

    @Override
    public int calculateQuality(Product product, int sellIn, int quality, LocalDate date) {
        int multiplier = product.getProductType().getFactor();
        return Math.max(calculate(product.getProductType().getOperation(), quality, (product.daysPassed(date) * multiplier)), 0);
    }
}
