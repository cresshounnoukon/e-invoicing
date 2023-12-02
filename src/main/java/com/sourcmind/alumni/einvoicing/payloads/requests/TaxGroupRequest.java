package com.sourcmind.alumni.einvoicing.payloads.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxGroupRequest {
    private String code;
    private String name;
    private double rate;
}

