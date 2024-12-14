package com.sankha.productService.builders;

import com.sankha.productService.entities.Product;

import java.util.UUID;

public class TestProductBuilder {
    private UUID productId;
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;

    public TestProductBuilder withId(UUID productId) {
        this.productId = productId;
        return this;
    }
    public TestProductBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public TestProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    public TestProductBuilder withCategory(String category) {
        this.category = category;
        return this;
    }
    public TestProductBuilder withPrice(double price) {
        this.price = price;
        return this;
    }
    public TestProductBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product build() {
        return Product.builder().name(name)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
