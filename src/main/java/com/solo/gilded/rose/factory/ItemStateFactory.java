package com.solo.gilded.rose.factory;

import com.solo.gilded.rose.entity.ProductType;
import com.solo.gilded.rose.entity.states.*;

import java.util.Objects;

import static com.solo.gilded.rose.entity.ProductType.NORMAL;

public class ItemStateFactory {

    public static QualityState createQualityState(ProductType productType){
        QualityState qualityState = null;
        switch (Objects.requireNonNullElse(productType,NORMAL)){
            case AGED_BRIE -> qualityState = new AgedBrieQualityState();
            case SULFURAS -> qualityState= new SulfurasQualityState();
            case BACKSTAGE_PASSES -> qualityState = new BackStageQualityState();
            case CONJURED -> qualityState = new ConjuredQualityState();
            default -> qualityState = new NormalQualityState();
        }
        return qualityState;
    }

    public static SellInState createSellInState(ProductType productType){
        SellInState sellInState = null;
        switch (Objects.requireNonNullElse(productType,NORMAL)){
            case SULFURAS -> sellInState = new SulfurasSellInState();
            default -> sellInState = new NormalSellInState();
        }
        return sellInState;
    }
}
