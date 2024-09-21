package com.scaler.productcatalogservice.repos;

import com.scaler.productcatalogservice.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

}
