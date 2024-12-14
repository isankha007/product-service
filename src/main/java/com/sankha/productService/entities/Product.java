package com.sankha.productService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;
    private String description;
    private String category;
    @DecimalMin(value = "0.00",message = "Price can not be negative")
    private double price;
    @Min(value = 0, message = "Minimum quantity should be 1")
    private int quantity;
    @CreationTimestamp
    @JsonIgnore
    private Date createdTime;

    @UpdateTimestamp
    @JsonIgnore
    private Date lastModifiedTime;

    public static Product createProduct(String name, String description, String category, double price, int quantity) {
        return Product.builder().name(name)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity).build();
    }
}
