package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TaxGroupResponse {
    private UUID id;
    private String code;
    private String name;
    private double rate;
}

