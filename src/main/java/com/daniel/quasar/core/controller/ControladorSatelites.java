package com.daniel.quasar.core.controller;

import com.daniel.quasar.core.dto.DistanciasSatelitesDTO;
import com.daniel.quasar.core.dto.MensajesSatelitesDTO;
import com.daniel.quasar.core.dto.RequestSatelitesDTO;
import com.daniel.quasar.core.dto.ResponseCoordenadasMensajeDTO;
import com.daniel.quasar.core.entity.Coordenada;
import com.daniel.quasar.core.service.InterfazDeSatelites;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "topsecret", consumes = "application/json", produces = "application/json")
@RequestMapping("/topsecret")
public class ControladorSatelites {

    private final InterfazDeSatelites interfazDeSatelites;

    @ApiOperation(value = "Se obtiene posicion y mensaje de nave")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Posicion y Mensaje obtenido con exito!", response = ResponseCoordenadasMensajeDTO.class),
            @ApiResponse(code=404, message = "datos incorrectos, no se pudo obtener respuesta valida",response = RuntimeException.class)
    })
    @PostMapping
    public ResponseEntity<ResponseCoordenadasMensajeDTO> obtenerCoordenadas(@RequestBody RequestSatelitesDTO requestSatelitesDTO) {

        Coordenada coordenada = interfazDeSatelites.getLocation(DistanciasSatelitesDTO.crearDistanciasSatelitesDTO(requestSatelitesDTO));
        String mensaje=interfazDeSatelites.getMessage(MensajesSatelitesDTO.crearMensajesSatelitesDTO(requestSatelitesDTO));

        return ResponseEntity.ok(new ResponseCoordenadasMensajeDTO(coordenada,mensaje));
    }

}
