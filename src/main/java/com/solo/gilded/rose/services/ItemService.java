package com.solo.gilded.rose.services;

import com.solo.gilded.rose.entity.Item;
import com.solo.gilded.rose.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
