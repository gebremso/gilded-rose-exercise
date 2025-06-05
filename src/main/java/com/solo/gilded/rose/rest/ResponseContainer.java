package com.solo.gilded.rose.rest;

import java.util.List;

public record ResponseContainer(String status, List<ProductResult> results, String message) {
}
