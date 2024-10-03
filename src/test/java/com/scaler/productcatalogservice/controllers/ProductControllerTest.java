package com.scaler.productcatalogservice.controllers;

import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void Test_GetProductById_WithValidProductId_RunSuccessfully() {
        //Arrange
        Long productId = 2L;
        Product product = new Product();
        product.setID(productId);
        product.setTitle("Iphone 16");

        when(productService.getProductById(productId))
                .thenReturn(product);

        // Act
        ResponseEntity<ProductDto> response = productController.getProductById(productId);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(productId, response.getBody().getID());
        assertEquals("Iphone 16", response.getBody().getTitle());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void TestGetProductById_WithInvalid_ResultsInRuntimeException() {
        RuntimeException e =
        assertThrows(RuntimeException.class, () -> productController.getProductById(-1L));
        assertEquals("Product not found.",e.getMessage());
    }

    @Test
    public void TestGetProductById_WhenProductServiceThrowsException() {
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("Code is broken"));

        assertThrows(RuntimeException.class,
                () -> productController.getProductById(1000L));
    }

}