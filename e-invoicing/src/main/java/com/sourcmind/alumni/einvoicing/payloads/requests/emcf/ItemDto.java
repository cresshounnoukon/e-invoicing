package com.sourcmind.alumni.einvoicing.payloads.requests.emcf;

import lombok.Builder;

@Builder
public class ItemDto {
    public String code;
    public String name;
    public int price;
    public int quantity;
    public String taxGroup;
    public int taxSpecific;
    public int originalPrice;
    public String priceModification;
}