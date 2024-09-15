package com.scaler.productcatalogservice.controllers;

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
    public Product getProduct(@PathVariable("id") Long productId){
        Product product = new Product();
        product.setID(productId);
        product.setTitle("Iphone16");
        product.setDescription("Yet another same iphone :(");
        product.setAmount(130000);
        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return null;
    }

}
