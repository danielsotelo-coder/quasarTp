package com.daniel.quasar;

import com.daniel.quasar.core.entity.Satelite;
import com.daniel.quasar.core.enums.NombresSatelites;
import com.daniel.quasar.core.service.InterfazDeSatelites;
import com.daniel.quasar.core.entity.SatelitesOnline;
import com.daniel.quasar.core.dto.DistanciasSatelitesDTO;
import com.daniel.quasar.core.dto.MensajesSatelitesDTO;
import com.daniel.quasar.core.entity.Coordenada;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InterfazDeSatelitesTest {


    @MockBean(name = "SistemaDeSatelites")
    private SatelitesOnline satelites;


    @MockBean(name="interaccionesSatelites")
    private HashSet satellites;


    @Test
    public void getLocationTrianguloTest(){

        SatelitesOnline satelites = getMockSatelitesPosicionTriangular();

        InterfazDeSatelites interfaz=new InterfazDeSatelites();
        interfaz.setSatelites(satelites);

        Coordenada coordenadaResultado =interfaz.getLocation(DistanciasSatelitesDTO.builder().distanciaAKenobi(2.0).distanciaASato(2.0).distanciaASkywalker(2.0).build());;

        assertEquals("Coordenadas de obtenidas no son iguales", Coordenada.builder().x(3).y(0).build() , coordenadaResultado);
    }

    @Test
    public void getLocationNoTriangularTest(){
        SatelitesOnline satelites = getMockSatelitesPosicionNoTriangular();

        InterfazDeSatelites interfaz=new InterfazDeSatelites();
        interfaz.setSatelites(satelites);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            interfaz.getLocation(DistanciasSatelitesDTO.builder().distanciaAKenobi(2.0).distanciaASato(2.0).distanciaASkywalker(2.0).build());;
        });

        assertTrue(exception.getMessage().contains("El area entre los tres puntos no forman un triangulo"));
    }


    @Test
    public void getMessageTest(){
        InterfazDeSatelites interfaz=new InterfazDeSatelites();
        MensajesSatelitesDTO mensajes= crearMensaje();
        String resultado = interfaz.getMessage(mensajes);
        System.out.println("Mensaje:"+resultado);

    }



    private MensajesSatelitesDTO crearMensaje() {
        List<String> mensajeKenobi = Arrays.asList("", "este","","un","mensaje");
        List<String> mensajeSato = Arrays.asList("este", "","un","mensaje");
        List<String> mensajeSkywalker = Arrays.asList("", "","es","","mensaje");

        return MensajesSatelitesDTO.builder()
                .mensajeAKenobi(mensajeKenobi)
                .mensajeASato(mensajeSato)
                .mensajeASkywalker(mensajeSkywalker).build();
    }

    private SatelitesOnline getMockSatelitesPosicionTriangular() {
        return new SatelitesOnline(Coordenada.builder().x(5).y(1).build(),
                Coordenada.builder().x(1).y(1).build(),
                Coordenada.builder().x(3).y(2).build());
    }

    private SatelitesOnline getMockSatelitesPosicionNoTriangular() {
        return new SatelitesOnline(Coordenada.builder().x(5).y(1).build(),
                Coordenada.builder().x(1).y(1).build(),
                Coordenada.builder().x(3).y(1).build());
    }

}

