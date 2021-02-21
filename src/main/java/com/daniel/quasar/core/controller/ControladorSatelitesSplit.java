package com.daniel.quasar.core.controller;


import com.daniel.quasar.core.dto.DistanciaMensajeDTO;
import com.daniel.quasar.core.dto.ResponseCoordenadasMensajeDTO;
import com.daniel.quasar.core.entity.Coordenada;
import com.daniel.quasar.core.enums.NombresSatelites;
import com.daniel.quasar.core.service.InterfazDeSatelites;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "topsecret", consumes = "application/json", produces = "application/json")
@RequestMapping("/topsecret_split")
public class ControladorSatelitesSplit {

    private final InterfazDeSatelites interfazDeSatelites;


    @ApiOperation(value = "Se guarda mensaje enviado a un satelite particular")
    @PostMapping("/{satelite_name}")
    @ResponseStatus(HttpStatus.OK)
    public void getMessageASatellite(@PathVariable(name = "satelite_name", required = true) NombresSatelites satellite_name,
                                     @RequestBody DistanciaMensajeDTO distanciaMensajeDTO){
        interfazDeSatelites.enviarMensajeYDistanciaSatelite(satellite_name,distanciaMensajeDTO.getDistance(),distanciaMensajeDTO.getMessage());
    }

    @ApiOperation(value = "Se obtiene posicion y mensaje de la nave")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Posicion y Mensaje obtenido con exito!", response = ResponseCoordenadasMensajeDTO.class),
    })
    @GetMapping
    public ResponseEntity<ResponseCoordenadasMensajeDTO> calcularCoordenadasYMensaje(){


        Coordenada coordenada = interfazDeSatelites.getLocation();
        String mensaje=interfazDeSatelites.getMessage();

        return ResponseEntity.ok(new ResponseCoordenadasMensajeDTO(coordenada,mensaje));

    }


}
