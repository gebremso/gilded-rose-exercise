package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

public class SulfurasQualityState implements QualityState{
    @Override
    public int calculateQuality(Product product, int sellIn, int quality) {
        return calculateQuality(product,sellIn,quality,LocalDate.now());
    }

    @Override
    public int calculateQuality(Product product, int sellIn, int quality, LocalDate date) {
        return quality;
    }
}
