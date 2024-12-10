package com.sankha.productService.dto;

import java.util.UUID;

public record ProductRequest(
        String name,
        String description,
        String category,
        double price,
        int quantity,
        UUID uuid) {
}
