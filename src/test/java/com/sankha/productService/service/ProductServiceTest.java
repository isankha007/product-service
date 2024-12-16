package com.sankha.productService.service;

import com.sankha.productService.builders.TestProductBuilder;
import com.sankha.productService.dto.ProductRequest;
import com.sankha.productService.entities.Product;
import com.sankha.productService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProductServiceTest {
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    private TestProductBuilder testProductBuilder;
//    @Mock
//    private SecurityService securityService;

    @BeforeEach
    void setup() {
        testProductBuilder = new TestProductBuilder();
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldSaveProductToCatalog() {
        UUID uuid = UUID.randomUUID();
        ProductRequest productRequest = new ProductRequest("Apple", "description", "category",
                80.0, 9, uuid);
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        String token = "token";
        String username = "username";

        productService.create(productRequest, username, token);

        verify(productRepository).save(argumentCaptor.capture());
        Product actualProduct = argumentCaptor.getValue();
        Assertions.assertEquals(productRequest.name(), actualProduct.getName());
        Assertions.assertEquals(productRequest.description(), actualProduct.getDescription());

    }

}