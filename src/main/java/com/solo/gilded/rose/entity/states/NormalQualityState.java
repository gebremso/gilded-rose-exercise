package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

import static com.solo.gilded.rose.util.StateCalculatorUtil.calculate;

public class NormalQualityState implements QualityState {

    @Override
    public int calculateQuality(Product product, int sellIn, int quality) {
        return calculateQuality(product,sellIn,quality,LocalDate.now());
    }

    @Override
    public int calculateQuality(Product product, int sellIn, int quality, LocalDate date) {
        int multiplier = sellIn< 0 ? 2 : product.getProductType().getFactor();
        return Math.max(calculate(product.getProductType().getOperation(), quality,(product.daysPassed(date) * multiplier)), 0);
    }
}
