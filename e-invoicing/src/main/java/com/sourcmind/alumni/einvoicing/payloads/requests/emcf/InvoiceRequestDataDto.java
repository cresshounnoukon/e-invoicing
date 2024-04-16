package com.sourcmind.alumni.einvoicing.payloads.requests.emcf;

import java.util.ArrayList;

import lombok.Builder;

@Builder
public class InvoiceRequestDataDto {
    public String ifu;
    public String aib;
    public String type;
    public ArrayList<ItemDto> items;
    public ClientDto client;
    public OperatorDto operator;
    public ArrayList<PaymentDto> payment;
}
