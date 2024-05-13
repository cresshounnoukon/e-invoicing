package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private UUID id;
    List<ItemResponse> items;
    private TypeInvoiceResponse typeInvoice;
    private CompanyResponse company;
    private CompanyResponse customer;
    private boolean normalize;
}
