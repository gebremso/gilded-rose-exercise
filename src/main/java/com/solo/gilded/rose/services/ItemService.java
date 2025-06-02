package com.solo.gilded.rose.services;

import com.solo.gilded.rose.entity.Item;
import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import com.solo.gilded.rose.repository.ItemRepository;
import com.solo.gilded.rose.rest.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "itemService")
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> fitchAllItems(){
        return (List<Item>) itemRepository.findAll();
    }

    public Item findItemByName(String name){
        return itemRepository.findByProduct_Name(name);
    }

    public Iterable<Item> saveAll(List<Item> items){
        return itemRepository.saveAll(items);
    }

    public Optional<Item> save(Item item){
        if(!itemRepository.existsByProduct_Name(item.getProduct().getName())){
            return Optional.of( itemRepository.save(item));
        }
        return Optional.empty();
    }

    public Optional<ProductRecord> findById(Long id) {
        if(itemRepository.existsById(id)){
            Item item = itemRepository.findById(id).get();
            item.initState();
            return Optional.of(new ProductRecord(item.getProduct().getName(),item.getSellIn(),item.getQuality()));
        }
        return Optional.empty();
    }

    public Optional<List<ProductRecord>> findItemsAsOfDate(LocalDate date) {
        List<Item> itemList  = fitchAllItems();
        List<ProductRecord> productRecords = new ArrayList<>();
        itemList.forEach( item -> {
            item.initState();
            ProductRecord record = new ProductRecord(item.getProduct().getName(),item.getSellIn(date),item.getQuality(date));
            productRecords.add(record);

        });

        return Optional.of(productRecords);
    }
}
