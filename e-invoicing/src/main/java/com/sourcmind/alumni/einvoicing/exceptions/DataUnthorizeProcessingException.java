package com.sourcmind.alumni.einvoicing.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DataUnthorizeProcessingException extends  RuntimeException{
    private final   Error error;

}
