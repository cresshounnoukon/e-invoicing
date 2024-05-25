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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean normalize;

    private String emcfInvoiceId;

    public double getTotal() {
        return items.stream()
                .mapToDouble(Item::getTotal)
                .sum();
    }
}

