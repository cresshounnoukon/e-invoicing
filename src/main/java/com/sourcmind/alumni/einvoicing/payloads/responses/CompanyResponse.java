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
public class CompanyResponse {
    private UUID id;
    private String name;
    private String tradeNo;
    private String tin;
}

