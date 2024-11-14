package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.dtos.UserDto;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

//    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

//    public List<Product> getProductsBetweenRange(Double low, Double high) {
//        return productRepo.findProductByAmountBetween(low, high);
//    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        Product product = productRepo.findById(productId).get();
//        RestTemplate restTemplate = restTemplateBuilder.build();
        UserDto userDto = restTemplate.getForEntity("http://userservice/user/{id}", UserDto.class,userId).getBody();
        if( userDto != null ){
            return product;
        }
        return null;
    }
}
