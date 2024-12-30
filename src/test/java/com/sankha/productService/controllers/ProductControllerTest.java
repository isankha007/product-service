package com.sankha.productService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankha.productService.dto.ProductRequest;
import com.sankha.productService.service.ProductService;
import com.sankha.productService.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SecurityService securityService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        UUID uuid=UUID.randomUUID();
        ProductRequest expectedProductRequest = new ProductRequest("Orange", "description", "category",
                60.0, 10, uuid);
        String jsonProductRequest = objectMapper.writeValueAsString(expectedProductRequest);
        ArgumentCaptor<ProductRequest> argumentCaptor = ArgumentCaptor.forClass(ProductRequest.class);
        String token ="token";
        String username="username";
        mockMvc.perform(post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("username", username)
                .header("token", token)
                .content(jsonProductRequest)
                )
                .andExpect(status().isCreated());

        verify(productServiceMock).create(argumentCaptor.capture(),anyString(),anyString());
        ProductRequest actualProductrequest = argumentCaptor.getValue();
        Assertions.assertEquals(expectedProductRequest.name(),actualProductrequest.name());
    }
}