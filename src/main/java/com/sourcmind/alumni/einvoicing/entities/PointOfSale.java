package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointOfSale {
    @Id
    @GeneratedValue
    private UUID id;
    private String name; 
    private String address;
    private String email;
    private String phone;
    @ManyToOne
    private  Company company;

    @OneToMany
    private List<Invoice> invoices = new ArrayList<>();
}
