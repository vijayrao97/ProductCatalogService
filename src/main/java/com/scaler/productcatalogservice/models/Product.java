package com.scaler.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String title;
    private String Description;
    private String ImageURL;
    private double amount;
    private Category category;
}
