package com.sourcmind.alumni.einvoicing.payloads.requests.emcf;

import lombok.Builder;

@Builder
public class PaymentDto {
    public String name;
    public double amount;
}
