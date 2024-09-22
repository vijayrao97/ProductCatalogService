package com.scaler.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.*;

@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    private Long id;
    private String email;
}
