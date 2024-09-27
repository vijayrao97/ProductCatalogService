package com.scaler.productcatalogservice.repos;

import com.scaler.productcatalogservice.models.Category;
import com.scaler.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes(){
        Category category = categoryRepo.findById(1L).get();
        for(Product product : category.getProducts()){
            System.out.println(product.getTitle());
        }
    }

    @Test
    @Transactional
    public void testFetchModeAndTypes(){
        Category category = categoryRepo.findById(1L).get();
//        for(Product product : category.getProducts()){
//            System.out.println(product.getTitle());
//        }
    }

}