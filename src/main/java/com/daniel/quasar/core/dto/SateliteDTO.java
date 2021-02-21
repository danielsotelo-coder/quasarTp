package com.daniel.quasar.core.dto;

import com.daniel.quasar.core.entity.Coordenada;
import com.daniel.quasar.core.enums.NombresSatelites;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class SateliteDTO {

    @Enumerated(EnumType.STRING)
    private NombresSatelites name;

    private Double distance;

    private List<String> message;

}
