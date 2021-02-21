package com.daniel.quasar.core.dto;

import com.daniel.quasar.core.entity.Satelite;
import com.daniel.quasar.core.enums.NombresSatelites;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.daniel.quasar.core.enums.NombresSatelites.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DistanciasSatelitesDTO {

    private Double distanciaAKenobi;
    private Double distanciaASato;
    private Double distanciaASkywalker;

    public List<Double> getListaOrdenadaDistancias(){
        return Arrays.asList(distanciaAKenobi, distanciaASato, distanciaASkywalker);
    }

    public static DistanciasSatelitesDTO crearDistanciasSatelitesDTO(RequestSatelitesDTO requestSatelitesDTO){

        return DistanciasSatelitesDTO.builder()
                .distanciaAKenobi(getDistanceFromStream(KENOBI,getSateliteStream(requestSatelitesDTO)))
                .distanciaASato(getDistanceFromStream(SATO,getSateliteStream(requestSatelitesDTO)))
                .distanciaASkywalker(getDistanceFromStream(SKYWALKER,getSateliteStream(requestSatelitesDTO)))
                .build();
    }

    private static Stream<SateliteDTO> getSateliteStream(RequestSatelitesDTO requestSatelitesDTO) {
        return requestSatelitesDTO.getSatellites().stream();
    }

    private static Double getDistanceFromStream(NombresSatelites nombreSatelite, Stream<SateliteDTO> streamSatelites) {
        return  streamSatelites
                .filter(s -> nombreSatelite.equals(s.getName()))
                .findAny()
                .orElseThrow(RuntimeException::new).getDistance();
    }


}
