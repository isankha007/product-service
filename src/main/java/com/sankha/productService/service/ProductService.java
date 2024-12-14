package com.sankha.productService.service;

import com.sankha.productService.dto.ProductRequest;
import com.sankha.productService.entities.Product;
import com.sankha.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void create(ProductRequest productRequest, String username, String token) {
        Product product=getProductFromRequest(productRequest);
        productRepository.save(product);
    }

    private Product getProductFromRequest(ProductRequest productRequest) {
        return Product.createProduct(
                productRequest.name(),
                productRequest.description(), productRequest.category(),
                productRequest.price(), productRequest.quantity());
    }
}
