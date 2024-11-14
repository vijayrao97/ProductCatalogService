package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(long id, Product product);

    Product getProductBasedOnUserRole(Long productId, Long userId);
}
