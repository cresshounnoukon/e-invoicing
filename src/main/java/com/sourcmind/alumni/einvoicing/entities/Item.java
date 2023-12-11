package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue
    private UUID id;
    private double quantity;
    private double price;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Invoice invoice;


    public double getTotal() {
     return  price*quantity;
    }
}
