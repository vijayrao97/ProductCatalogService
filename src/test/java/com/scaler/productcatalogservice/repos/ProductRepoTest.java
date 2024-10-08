package com.scaler.productcatalogservice.repos;

import com.scaler.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testQueries(){
//        List<Product> productList = productRepo.findProductByAmountBetween(100000.0, 200000.0);

//        List<Product> productList = productRepo.findProductByIsPrimeSpecificTrue();
//        for(Product product : productList){
//            System.out.println(product.getTitle()+" "+product.getAmount());
//        }

        System.out.println(productRepo.findProductDescriptionByFromProductId(4L));

        System.out.println(productRepo.findCategoryNameFromProductId(2L));

    }



}