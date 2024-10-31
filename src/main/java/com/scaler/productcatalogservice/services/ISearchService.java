package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.dtos.SortParam;
import com.scaler.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams);
}
