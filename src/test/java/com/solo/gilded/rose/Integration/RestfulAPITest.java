package com.solo.gilded.rose.Integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solo.gilded.rose.rest.ProductRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestfulAPITest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLoadingBulkItems() throws Exception {
        String json = """
                    [
                      { "name": "Aged Brie", "sellIn": 1, "quality": 1 },
                      { "name": "Backstage passes", "sellIn": -1, "quality": 2 },
                      { "name": "Backstage passes", "sellIn": 9, "quality": 2 },
                      { "name": "Sulfuras", "sellIn": 2, "quality": 2 },
                      { "name": "Normal", "sellIn": -1, "quality": 55 },
                      { "name": "Normal", "sellIn": 2, "quality": 2 },
                      { "name": "Conjured", "sellIn": 2, "quality": 2 },
                      { "name": "Conjured", "sellIn": -1, "quality": 5 }
                    ]
                """;

        ObjectMapper mapper = new ObjectMapper();
        List<ProductRecord> productRecords = mapper.readValue(json, new TypeReference<List<ProductRecord>>() {
        });

        String apiUrl = "/api/addItems";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<ProductRecord>> request = new HttpEntity<>(productRecords, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }

}
