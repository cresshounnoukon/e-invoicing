package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private UUID id;
    private String tradeNo;
    private String tin;

    @OneToMany
    private List<Product> products;

    @OneToMany
    private List<PointOfSale> pointOfSales;
}
