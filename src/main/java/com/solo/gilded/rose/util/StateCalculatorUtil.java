package com.solo.gilded.rose.util;

import com.solo.gilded.rose.entity.Operation;

public class StateCalculatorUtil {

    public static int calculate(Operation operation, int valueA, int valueB){
        switch (operation){
            case INCREASE -> {
                return Math.addExact(valueA, valueB);
            }
            case DECREASE -> {
                return Math.subtractExact(valueA,valueB);}
            default -> throw new UnsupportedOperationException("Only Increase and decrease is supported");
        }
    }
}
