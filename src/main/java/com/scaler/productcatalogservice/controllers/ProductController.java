package com.scaler.productcatalogservice.controllers;

import com.scaler.productcatalogservice.dtos.CategoryDto;
import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Category;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getProduct(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        if( products!=null && !products.isEmpty() ){
            for(Product product : products){
                ProductDto productDto = from(product);
                productDtos.add(productDto);
            }
            return productDtos;
        }
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        try{
            if( productId < 1 ){
                throw new RuntimeException("Product not found.");
            }
            Product product = productService.getProductById(productId);
            if( product == null ){
                return null;
            }
            return new ResponseEntity<>(from(product), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            throw exception;
        }
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return null;
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        Product product = productService.replaceProduct(id,from(productDto));
        return from(product);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    // Mapper function
    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setID(product.getID());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setAmount(product.getAmount());
        productDto.setImageURL(product.getImageURL());
        if( product.getCategory() != null ) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getID());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    // Mapper function
    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setID(productDto.getID());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setAmount(productDto.getAmount());
        product.setImageURL(productDto.getImageURL());
        if( productDto.getCategory() != null ) {
            Category category = new Category();
            category.setID(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }

}
