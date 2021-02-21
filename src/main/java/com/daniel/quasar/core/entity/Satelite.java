package com.daniel.quasar.core.entity;

import com.daniel.quasar.core.enums.NombresSatelites;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Satelite {

    private Double distance;

    private List<String> message;

    private Coordenada coordenada;

}
