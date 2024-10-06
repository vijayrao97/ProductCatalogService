package com.scaler.productcatalogservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProducts_RunSucessfully() throws Exception {
        Product product = new Product();
        product.setID(1L);
        product.setTitle("MacBookPro");

        Product product2 = new Product();
        product2.setID(2L);
        product2.setTitle("Iphone");

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        when(productService.getAllProducts())
                .thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("MacBookPro"))
                .andExpect(jsonPath("$[1].id").value("2"));
    }

    @Test
    public void Test_CreateProduct_RunSucessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setID(101L);
        product.setTitle("Notebook");

        ProductDto productDto = new ProductDto();
        productDto.setID(101L);
        productDto.setTitle("Notebook");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        //Act
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.id").value(101L))
                .andExpect(jsonPath("$.title").value("Notebook"));

    }

}
