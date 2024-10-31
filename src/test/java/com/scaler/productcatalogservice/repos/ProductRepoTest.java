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

//    @Test
//    @Transactional
    public void testQueries(){
//        List<Product> productList = productRepo.findProductByAmountBetween(100000.0, 200000.0);

//        List<Product> productList = productRepo.findProductByIsPrimeSpecificTrue();
//        for(Product product : productList){
//            System.out.println(product.getTitle()+" "+product.getAmount());
//        }

        System.out.println(productRepo.findProductDescriptionByFromProductId(4L));
        System.out.println(productRepo.findCategoryNameFromProductId(2L));
    }

    @Test
    public void saveProduct(){
        Product product1 = new Product();
        product1.setTitle("Iphone");
        product1.setID(100);
        product1.setDescription("Latest Iphone");
        productRepo.save(product1);

        Product product2 = new Product();
        product2.setTitle("MacBook");
        product2.setID(101);
        product2.setDescription("Latest Mac");
        productRepo.save(product2);

    }

}