package com.solo.gilded.rose.rest;

import com.solo.gilded.rose.entity.Item;
import com.solo.gilded.rose.exceptions.UnsupportedProductException;
import com.solo.gilded.rose.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InventoryController {
    private final ItemService itemService;

    @Autowired
    public InventoryController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/items")
    public ResponseEntity<?> fetchAllItems(){
        return ResponseEntity.ok(itemService.fitchAllItems());
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@RequestBody ProductRecord productRecord) {
        try {
            Item item = new Item(productRecord.name(), productRecord.sellIn(), productRecord.quality());
            Optional<Item> savedItem = itemService.save(item);
            if(savedItem.isPresent()){
                return ResponseEntity.ok(new ProductResult(savedItem.get().getId(),savedItem.get().getProduct().getName(),savedItem.get().getProduct().getPurchasedDate()));
            }
            return ResponseEntity.ok( "Item already exist" );
        } catch (UnsupportedProductException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Unsupported product type: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    @GetMapping("/ping")
    public String ping(){
        return "Success...";
    }


}
