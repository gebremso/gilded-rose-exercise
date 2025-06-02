package com.solo.gilded.rose.entity.states;

import com.solo.gilded.rose.entity.Product;

import java.time.LocalDate;

public interface QualityState {
    int calculateQuality(Product product, int sellIn, int quality);

    int calculateQuality(Product product, int sellIn, int quality, LocalDate date);
}
