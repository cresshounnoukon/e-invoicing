package com.sourcmind.alumni.einvoicing.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeInvoiceRequest {
    private String code;
    private String name;
    private UUID reimbursementId;

}

