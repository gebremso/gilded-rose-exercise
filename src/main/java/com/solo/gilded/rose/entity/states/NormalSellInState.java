package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Operation;
import com.solo.gilded.rose.entity.Product;
import com.solo.gilded.rose.entity.ProductType;

import java.time.LocalDate;

import static com.solo.gilded.rose.util.StateCalculatorUtil.calculate;

public class NormalSellInState implements SellInState {
    @Override
    public int calculateSellIn(Product product, int sellIn) {
        return calculateSellIn(product, sellIn, LocalDate.now());
    }

    @Override
    public int calculateSellIn(Product product, int sellIn, LocalDate date) {
        return calculate(
                Operation.DECREASE,
                sellIn,
                (product.daysPassed(date) * ProductType.NORMAL.getFactor())
        );
    }
}
