package com.scaler.productcatalogservice.dtos;

import com.scaler.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CategoryDto {
    private long id;
    private String name;
    private String description;
}
