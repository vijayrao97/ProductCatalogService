package com.scaler.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class BaseModel {

    private long ID;

    private Date createdAt;

    private Date LastUpdatedAt;

    private State state;
}
