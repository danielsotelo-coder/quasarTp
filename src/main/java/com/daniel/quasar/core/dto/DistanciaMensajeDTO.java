package com.daniel.quasar.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DistanciaMensajeDTO {

    private Double distance;

    private List<String> message;
}
