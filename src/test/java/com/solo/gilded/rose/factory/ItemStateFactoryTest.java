package com.solo.gilded.rose.factory;

import com.solo.gilded.rose.entity.ProductType;
import com.solo.gilded.rose.entity.states.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ItemStateFactoryTest {

    //Z - Zero Normal  item created using factory method
    @Test
    public void testShouldReturnNormalOnNullInput(){
        assertInstanceOf(NormalQualityState.class,ItemStateFactory.createQualityState(null));
    }

    //O - One input should return correct instance of quality state
    @Test
    public void testOneInputShouldReturnCorrectState(){
        assertInstanceOf(NormalQualityState.class, ItemStateFactory.createQualityState(ProductType.AGED_BRIE));
    }

    //M - Many input should return correct instances of state
    @Test
    public void testManyInputShouldReturnCorrectStateItem(){
        assertInstanceOf(NormalQualityState.class, ItemStateFactory.createQualityState(ProductType.NORMAL));
        assertInstanceOf(AgedBrieQualityState.class, ItemStateFactory.createQualityState(ProductType.AGED_BRIE));
        assertInstanceOf(SulfurasQualityState.class, ItemStateFactory.createQualityState(ProductType.SULFURAS));
        assertInstanceOf(BackStageQualityState.class, ItemStateFactory.createQualityState(ProductType.BACKSTAGE_PASSES));
        assertInstanceOf(ConjuredQualityState.class, ItemStateFactory.createQualityState(ProductType.CONJURED));
    }

    // E: Exception - Not expected in this method; ensure no exception thrown
    @Test
    void givenValidInputs_shouldNotThrowAnyExceptions() {
        assertDoesNotThrow(() -> ItemStateFactory.createQualityState(ProductType.AGED_BRIE));
        assertDoesNotThrow(() -> ItemStateFactory.createQualityState(ProductType.SULFURAS));
        assertDoesNotThrow(() -> ItemStateFactory.createQualityState(null)); // default case
    }

    //O

}