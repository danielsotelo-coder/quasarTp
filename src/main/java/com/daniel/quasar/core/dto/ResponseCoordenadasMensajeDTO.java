package com.daniel.quasar.core.dto;

import com.daniel.quasar.core.entity.Coordenada;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseCoordenadasMensajeDTO {

    private final Coordenada coordenada;
    private final String message;

}
