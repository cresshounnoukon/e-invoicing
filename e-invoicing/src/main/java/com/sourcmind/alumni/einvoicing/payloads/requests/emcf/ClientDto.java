package com.sourcmind.alumni.einvoicing.payloads.requests.emcf;

import lombok.Builder;

@Builder
public class ClientDto {
    public String ifu;
    public String name;
    public String contact;
    public String address;
}