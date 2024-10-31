package com.scaler.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SortParam {
    private String attribute;
    private SortType sortType;
}
