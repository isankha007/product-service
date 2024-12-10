package com.sankha.productService.controllers;

import com.sankha.productService.dto.ProductRequest;
import com.sankha.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated ProductRequest productRequest,
                       @RequestHeader("username") String username,
                       @RequestHeader("token") String token
    ) {
        productService.create(productRequest,username,token);
    }
}
