package com.scaler.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Product extends BaseModel {
    private String title;
    private String Description;
    private String ImageURL;
    private double amount;
//    @JsonBackReference
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Boolean isPrimeSpecific;
}
