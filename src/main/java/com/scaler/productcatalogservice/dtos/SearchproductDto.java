package com.scaler.productcatalogservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchproductDto {
    public String query;
    public int pageSize;
    public int pageNumber;
    private List<SortParam> sortParamList = new ArrayList<>();
}
