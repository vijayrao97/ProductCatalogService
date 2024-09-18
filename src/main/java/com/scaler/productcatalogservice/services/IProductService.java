package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);
}
