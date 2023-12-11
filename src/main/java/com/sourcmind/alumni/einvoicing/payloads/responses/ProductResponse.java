package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private UUID id;
    private String code;
    private String name;
    private double price;
    private CategoryResponse category;
    private TaxGroupResponse taxGroup;
}
