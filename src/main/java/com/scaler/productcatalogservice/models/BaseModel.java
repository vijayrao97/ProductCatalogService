package com.scaler.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private long ID;

    private Date createdAt;

    private Date LastUpdatedAt;

    private State state;
}
