package com.scaler.productcatalogservice.repos;

import com.scaler.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findProductByAmountBetween(Double lower, Double upper);

    List<Product> findProductByIsPrimeSpecificTrue();

    Page<Product> findProductByTitleEquals(String title, Pageable pageable);

    @Query("SELECT p.Description from Product p where p.ID =?1")
    String findProductDescriptionByFromProductId(Long id);

    @Query("select c.name from Category c join Product p on p.category.ID = c.ID where p.ID=:id")
    String findCategoryNameFromProductId(Long id);

}
