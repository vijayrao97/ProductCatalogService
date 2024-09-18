package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.dtos.FakeStoreProductDto;
import com.scaler.productcatalogservice.models.Category;
import com.scaler.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {


    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    // Mapper function
    public Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setID(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setAmount(fakeStoreProductDto.getPrice());
        product.setImageURL(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
