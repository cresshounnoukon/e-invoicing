package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeInvoiceResponse {
    private UUID id;
    private String code;
    private String name;
    private TypeInvoiceResponse reimbursement;

}

