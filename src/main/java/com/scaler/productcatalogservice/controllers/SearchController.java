package com.scaler.productcatalogservice.controllers;


import com.scaler.productcatalogservice.dtos.SearchproductDto;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchproductDto searchproductDto ) {
        return searchService.searchProducts(searchproductDto.getQuery()
                ,searchproductDto.getPageNumber(), searchproductDto.getPageSize(),
                searchproductDto.getSortParamList());
    }

}
