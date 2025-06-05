package com.solo.gilded.rose.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solo.gilded.rose.entity.Item;
import com.solo.gilded.rose.services.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddItem() throws Exception {

        ProductRecord productRecord = new ProductRecord("Aged Brie", 10, 20);
        String json = objectMapper.writeValueAsString(productRecord);

        mockMvc.perform(post("/api/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.name").value("Aged Brie"));
    }

    @Test
    public void testAddDuplicateItem() throws Exception {
        ProductRecord record = new ProductRecord("Aged Brie", 10, 20);

        ItemService itemService = Mockito.mock(ItemService.class);
        Mockito.when(itemService.save(any(Item.class))).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Item already exist")));
    }

    @Test
    public void testUnsupportedProduct() throws Exception {
        ProductRecord record = new ProductRecord("Unknown", 5, 10);
        mockMvc.perform(post("/api/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Unsupported product type")));
    }

    @Test
    public void testInvalidQuality() throws Exception {
        ProductRecord record = new ProductRecord("Normal", 10, -88);

        mockMvc.perform(post("/api/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid input")));
    }

}