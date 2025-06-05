package com.solo.gilded.rose.rest;

import com.solo.gilded.rose.entity.Item;
import com.solo.gilded.rose.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InventoryController {
    private final ItemService itemService;

    @Autowired
    public InventoryController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/items")
    public ResponseEntity<?> fetchAllItems() {
        return ResponseEntity.ok(itemService.fitchAllItems());
    }

    public List<Item> fitchAllItems() {
        return itemService.fitchAllItems();
    }

    public Item findItemByName(String name) {
        return itemService.findItemByName(name);
    }

    public Iterable<Item> saveAll(List<Item> items) {
        return itemService.saveAll(items);
    }

    public Optional<Item> save(Item item) {
        return itemService.save(item);
    }

    public Optional<ProductRecord> findById(Long id) {
        return itemService.findById(id);
    }

    public Optional<List<ProductRecord>> findItemsAsOfDate(LocalDate date) {
        return itemService.findItemsAsOfDate(date);
    }

    @PostMapping("/addItems")
    public ResponseEntity<?> addItems(@RequestBody List<ProductRecord> productRecords){

        List<Item> items = productRecords
                .stream()
                .map(p -> new Item(p.name(), p.sellIn(), p.quality()))
                .collect(Collectors.toList());

        Iterable<Item> savedList = itemService.saveAll(items);

        return ResponseEntity.ok(savedList);
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@RequestBody ProductRecord productRecord)  {
            Item item = new Item(productRecord.name(), productRecord.sellIn(), productRecord.quality());
            Optional<Item> savedItem = itemService.save(item);
            if (savedItem.isPresent()) {
                return ResponseEntity.ok(new ProductResult(savedItem.get().getId(), savedItem.get().getProduct().getName(), savedItem.get().getProduct().getPurchasedDate()));
            }
            return ResponseEntity.ok("Item already exist");
    }




    @GetMapping("/item/{id}")
    public ResponseEntity<?> findItemById(@PathVariable Long id) {
        Optional<ProductRecord> item = itemService.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Item does NOT exist.");
    }

    @GetMapping("/itemsAsOf")
    public ResponseEntity<?> getItemsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<List<ProductRecord>> records = itemService.findItemsAsOfDate(date);
        if (records.isPresent()) {
            return ResponseEntity.of(records);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Error during item retrieval.");
    }

    @GetMapping("/ping")
    public String ping() {
        return "Success...";
    }


}
