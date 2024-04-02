package com.sourcmind.alumni.einvoicing.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxGroupRequest {
    private String code;
    private String name;
    private double rate;
}

