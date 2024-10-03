package com.scaler.productcatalogservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.scaler.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private long ID;
    private String title;
    private String Description;
    private String ImageURL;
    private double amount;
    private CategoryDto category;
}
