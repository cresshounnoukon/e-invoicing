package com.sourcmind.alumni.einvoicing.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeInvoice {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private String name;

    @OneToOne
    private TypeInvoice reimbursement;

    @OneToMany
    private  List<Invoice> invoices= new ArrayList<>();

}
