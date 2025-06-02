package com.solo.gilded.rose.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solo.gilded.rose.entity.states.QualityState;
import com.solo.gilded.rose.entity.states.SellInState;
import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import com.solo.gilded.rose.factory.ItemStateFactory;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    private int sellIn;
    private int quality;
    @JsonIgnore
    @Transient
    private QualityState qualityState;
    @JsonIgnore
    @Transient
    private SellInState sellInState;

    protected Item(){
    }

    public Item(String name, int sellIn, int quality) throws UnsupportedProductException {
        this(name,sellIn,quality,LocalDate.now());
    }

    public Item(String name, int sellIn, int quality, LocalDate purchaseDate) throws UnsupportedProductException {
        this.product = new Product(name,purchaseDate);
        this.sellIn = sellIn;
        setQuality(quality);
        initState();
    }

    public void initState(){
        this.qualityState = ItemStateFactory.createQualityState(this.product.getProductType());
        this.sellInState = ItemStateFactory.createSellInState(this.product.getProductType());
    }


    private int valuateQuality(LocalDate date){
        return qualityState.calculateQuality(this.product, valuateSellIn(date), this.quality, date);
    }

    private int valuateSellIn(){
        return valuateSellIn(LocalDate.now());
    }

    private int valuateSellIn(LocalDate date){
        return sellInState.calculateSellIn(this.product, this.sellIn, date);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSellIn() {
        return valuateSellIn();
    }

    public int getSellIn(LocalDate date) {
        return valuateSellIn(date);
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return getQuality(LocalDate.now());
    }

    public int getQuality(LocalDate date) {
        return Math.min( valuateQuality(date),50);
    }

    public void setQuality(int quality) {
        if(quality < 0)
            throw new IllegalArgumentException("Quality Can NOT be below Zero.");
        this.quality = quality;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
