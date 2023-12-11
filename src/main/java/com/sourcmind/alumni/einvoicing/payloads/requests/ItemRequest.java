package com.sourcmind.alumni.einvoicing.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {
    private UUID productId;
    private Integer quantity;
    private double price;
}
