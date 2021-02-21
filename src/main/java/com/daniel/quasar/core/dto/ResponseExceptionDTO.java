package com.daniel.quasar.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseExceptionDTO {
    private final int responseCode;
    private final String mensaje;
    private final String exception;

}
