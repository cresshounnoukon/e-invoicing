package com.sourcmind.alumni.einvoicing.payloads.responses;

import lombok.Builder;
import lombok.Data;

import java.rmi.server.UID;
import java.util.UUID;

@Data
@Builder
public class CategoryResponse {
    private UUID id;
    private String code;
    private String name;
}

