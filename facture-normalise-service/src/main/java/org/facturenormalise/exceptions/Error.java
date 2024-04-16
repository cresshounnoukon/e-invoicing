package org.facturenormalise.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String errorDesc;
    private String errorCode;
}
