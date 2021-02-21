package com.daniel.quasar.core.entity;

import com.daniel.quasar.core.enums.NombresSatelites;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.daniel.quasar.core.enums.NombresSatelites.*;

@Data
@NoArgsConstructor
@ToString
public class SatelitesOnline {

    private HashMap<NombresSatelites,Satelite> satelites;

    public SatelitesOnline(Coordenada kenobi, Coordenada sato, Coordenada skywalker) {
        this.satelites=new HashMap();
        satelites.put(KENOBI,Satelite.builder().coordenada(kenobi).build());
        satelites.put(SATO,Satelite.builder().coordenada(sato).build());
        satelites.put(SKYWALKER,Satelite.builder().coordenada(skywalker).build());

    }

    public HashMap<NombresSatelites,Satelite> getSatelites(){
        return  satelites;
    }

    public List<Coordenada> getCoordenadasSatelites(){
        return Arrays.asList(satelites.get(KENOBI).getCoordenada(),
                            satelites.get(SATO).getCoordenada(),
                            satelites.get(SKYWALKER).getCoordenada());
    }

    public List<Double> getDistancias(){
        return Arrays.asList(satelites.get(KENOBI).getDistance(),
                satelites.get(SATO).getDistance(),
                satelites.get(SKYWALKER).getDistance());
    }


}
