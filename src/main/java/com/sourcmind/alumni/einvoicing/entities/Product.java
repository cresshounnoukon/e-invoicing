package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private String name;
    private double price;

    @ManyToOne()
    private TaxGroup taxGroup;

    @ManyToOne()
    private Category category;

}
