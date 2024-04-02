package com.sourcmind.alumni.einvoicing.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private  String type;
    private String message;
    private Object value;
    private Object field;
}
