package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private  ProductResponse product;
    private  int quantity;

}
