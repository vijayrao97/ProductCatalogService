package com.scaler.productcatalogservice.controllers;

import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Product;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProduct(){
        return null;
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable("id") Long productId){
//        Product product = new Product();
//        product.setID(productId);
//        product.setTitle("Iphone16");
//        product.setDescription("Yet another same iphone :(");
//        product.setAmount(130000);
//        return product;
        return null;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody Product product){
        return null;
    }

}
