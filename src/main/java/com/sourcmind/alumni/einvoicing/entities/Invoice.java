package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Company customer;

    @ManyToOne
    private PointOfSale pointOfSale;

    @OneToMany
    private List<Item> items;


}

