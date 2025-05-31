package com.solo.gilded.rose.entity;

import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    //Z - Zero quality for normal Item remain Zero and sellIn will decrease
    @Test
    public void testZeroValueForNormalItemAfterOneDay() throws UnsupportedProductException {
        Item normalItem = new Item("Normal",4,0);
        int quality = normalItem.getQuality(LocalDate.now().plusDays(1));
        assertEquals(0, quality);
        int sellIn = normalItem.getSellIn(LocalDate.now().plusDays(1));
        assertEquals(3,sellIn);
    }

    //O  - Quality and Sell in value decrease by One for normal Item
    @Test
    public void testDecreaseByOneForNormalItemAfterOneDay() throws UnsupportedProductException {
        Item normalItem = new Item("Normal", 5, 3);
        int quality = normalItem.getQuality(LocalDate.now().plusDays(1));
        assertEquals(2, quality);
        int sellIn = normalItem.getSellIn(LocalDate.now().plusDays(1));
        assertEquals(4,sellIn);
    }

    //M Many product types return result based on specific state calculator
    @Test
    public void testManyProductTypeShouldReturnCorrectQualityAndSellIn() throws UnsupportedProductException {
        Item agedBrieItem = new Item("Aged Brie",-2,4);
        assertEquals(5,agedBrieItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(-3, agedBrieItem.getSellIn(LocalDate.now().plusDays(1)));

        Item backStageItem = new Item("Backstage passes",6,4);
        assertEquals(7,backStageItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(5,backStageItem.getSellIn(LocalDate.now().plusDays(1)));

        Item conjuredItem = new Item("Conjured",6,4);
        assertEquals(2,conjuredItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(5,conjuredItem.getSellIn(LocalDate.now().plusDays(1)));

        Item sulfurasItem = new Item("Sulfuras",6,4);
        assertEquals(4,sulfurasItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(6,sulfurasItem.getSellIn(LocalDate.now().plusDays(1)));

        Item normalItem = new Item("Normal",-3,4);
        assertEquals(2,normalItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(-4,normalItem.getSellIn(LocalDate.now().plusDays(1)));
    }

    //B - Test the boundaries of item quality to be between 0 to 50
    @Test
    public void testNormalItemQualityBoundaries() throws UnsupportedProductException {
        Item normailItem = new Item("Normal",-2,55);
        assertEquals(50,normailItem.getQuality(LocalDate.now().plusDays(1)));
        Item normalItem2 = new Item("Normal",-2,1);
        assertEquals(0,normalItem2.getQuality(LocalDate.now().plusDays(4)));
    }

    //B - Test the boundaries of backstage item return correct quality
    @Test
    public void testBackstageQualityBoundaries() throws UnsupportedProductException {
        Item normailItem = new Item("Backstage passes",12,7);
        //sellin > 10
        assertEquals(8,normailItem.getQuality(LocalDate.now().plusDays(1)));
        // sellIn <= 10 and sellIn >= 5
        assertEquals(13,normailItem.getQuality(LocalDate.now().plusDays(3)));
        //If (sellin <= 5 and sellin >= 0 ) then Quality increase by 3
        assertEquals(28,normailItem.getQuality(LocalDate.now().plusDays(7)));
        // sellin < 0
        assertEquals(0,normailItem.getQuality(LocalDate.now().plusDays(13)));
    }

    //I - Conjured items decrease in Quality  twice as fast
    @Test
    public void testConjuredItemShouldDecreaseTwice() throws UnsupportedProductException {
        Item conjuredItem = new Item("Conjured", 0,5);
        assertEquals(3,conjuredItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(1,conjuredItem.getQuality(LocalDate.now().plusDays(2)));
        assertEquals(0,conjuredItem.getQuality(LocalDate.now().plusDays(3)));
    }

    //I - Sulfuras never decrease in Quality
    @Test
    public void testSulfurasItemNeverDecreaseQualityOrSellIn() throws UnsupportedProductException {
        Item sulfurasItem = new Item("Sulfuras", 1,5);
        assertEquals(5,sulfurasItem.getQuality(LocalDate.now().plusDays(1)));
        assertEquals(1,sulfurasItem.getSellIn(LocalDate.now().plusDays(1)));
        assertEquals(5,sulfurasItem.getQuality(LocalDate.now().plusDays(2)));
        assertEquals(1,sulfurasItem.getSellIn(LocalDate.now().plusDays(2)));
        assertEquals(5,sulfurasItem.getQuality(LocalDate.now().plusDays(3)));
        assertEquals(1,sulfurasItem.getSellIn(LocalDate.now().plusDays(3)));
    }

    //I - Normal items for negative sellIn, the Quality degrades twice as fast
    @Test
    public void testNormalItemWithNegativeSellInShouldDecreaseTwice() throws UnsupportedProductException {
        Item normalItem = new Item("Normal", -3, 4);
        assertEquals(2,normalItem.getQuality(LocalDate.now().plusDays(1)));

    }

    //E - Exception
    @Test
    void testQualityBelowZeroThrowsException() {
        assertThrows(UnsupportedProductException.class, () -> new Item("Unsupported Item", 5, -1));
        assertThrows(IllegalArgumentException.class, () -> new Item("Normal", 5, -1));
    }


}