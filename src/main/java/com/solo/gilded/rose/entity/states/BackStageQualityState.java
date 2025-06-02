package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

import static com.solo.gilded.rose.util.StateCalculatorUtil.calculate;

public class BackStageQualityState implements QualityState {
    @Override
    public int calculateQuality(Product product, int sellIn, int quality) {
        return calculateQuality(product, sellIn, quality, LocalDate.now());
    }

    @Override
    public int calculateQuality(Product product, int sellIn, int quality, LocalDate date) {
        int multiplier = product.getProductType().getFactor();
        if (sellIn <= 10 && sellIn > 5) {
            multiplier = 2;
        } else if (sellIn <= 5 && sellIn >= 0) {
            multiplier = 3;
        } else if (sellIn < 0) {
            return 0;
        }
        return Math.max(calculate(product.getProductType().getOperation(), quality, (product.daysPassed(date) * multiplier)), 0);

    }
}
