package com.scaler.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User{
    private double hours;
}
