package com.daniel.quasar.core.dto;

import com.daniel.quasar.core.entity.Satelite;
import com.daniel.quasar.core.entity.SatelitesOnline;
import com.daniel.quasar.core.enums.NombresSatelites;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static com.daniel.quasar.core.enums.NombresSatelites.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MensajesSatelitesDTO {

    private List<String> mensajeAKenobi;
    private List<String> mensajeASkywalker;
    private List<String> mensajeASato;


    public static MensajesSatelitesDTO crearMensajesSatelitesDTO(HashMap<NombresSatelites,Satelite> satelites){

        return MensajesSatelitesDTO.builder()
                .mensajeAKenobi(satelites.get(KENOBI).getMessage())
                .mensajeASkywalker(satelites.get(SKYWALKER).getMessage())
                .mensajeASato(satelites.get(SATO).getMessage())
                .build();
    }

    public static MensajesSatelitesDTO crearMensajesSatelitesDTO(RequestSatelitesDTO requestSatelitesDTO){

        return MensajesSatelitesDTO.builder()
                .mensajeAKenobi(getMesage(KENOBI,getSateliteStream(requestSatelitesDTO)))
                .mensajeASkywalker(getMesage(SKYWALKER,getSateliteStream(requestSatelitesDTO)))
                .mensajeASato(getMesage(SATO,getSateliteStream(requestSatelitesDTO)))
                .build();
    }



    private static Stream<SateliteDTO> getSateliteStream(RequestSatelitesDTO requestSatelitesDTO) {
        return requestSatelitesDTO.getSatellites().stream();
    }

    private static List<String> getMesage(NombresSatelites nombreSatelite, Stream<SateliteDTO> streamSatelites) {
        return  streamSatelites
                .filter(s -> nombreSatelite.equals(s.getName()))
                .findAny()
                .orElseThrow(RuntimeException::new).getMessage();
    }
}
