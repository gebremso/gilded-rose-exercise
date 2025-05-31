package com.solo.gilded.rose.entity;

import com.solo.gilded.rose.exceptions.UnsupportedProductException;

import java.util.Arrays;

public enum ProductType {
    NORMAL("Normal", Operation.DECREASE, 1),
    AGED_BRIE("Aged Brie", Operation.INCREASE, 1),
    SULFURAS("Sulfuras", Operation.OTHER,0),
    BACKSTAGE_PASSES("Backstage passes", Operation.INCREASE,1),
    CONJURED("Conjured", Operation.DECREASE,2);

    private final String name;
    private final int factor;
    private final Operation operation;

    ProductType(String name, Operation operation, int factor) {
        this.name = name;
        this.factor = factor;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public int getFactor() {
        return factor;
    }

    public Operation getOperation() {
        return operation;
    }


    public static ProductType valueFrom(String name) throws UnsupportedProductException {
        return Arrays.stream(ProductType.values())
                .filter(productType ->  productType.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()-> new UnsupportedProductException(name+" is INVALID product"));
    }


}
