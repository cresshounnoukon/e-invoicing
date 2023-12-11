package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private  TypeInvoice typeInvoice;
    @ManyToOne
    private Company customer;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Invoice parent;

    @OneToMany(mappedBy = "parent")
    private List<Invoice> children;



    @OneToMany
    private List<Item> items;

    public double getTotal() {
        return items.stream()
                .mapToDouble(Item::getTotal)
                .sum();
    }


}

