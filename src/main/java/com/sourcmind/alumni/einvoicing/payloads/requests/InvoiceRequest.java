package com.sourcmind.alumni.einvoicing.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
    private UUID typeInvoiceId;
    private UUID companyId;
    private UUID customerId;
    private UUID reference;
    List<ItemRequest> items= new ArrayList<>();
}
