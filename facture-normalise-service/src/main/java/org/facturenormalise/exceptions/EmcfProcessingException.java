package org.facturenormalise.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EmcfProcessingException extends  RuntimeException{
    private final   Error error;

}
