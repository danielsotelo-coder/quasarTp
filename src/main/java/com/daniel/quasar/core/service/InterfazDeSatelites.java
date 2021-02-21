package com.daniel.quasar.core.service;

import com.daniel.quasar.core.entity.Coordenada;
import com.daniel.quasar.core.dto.DistanciasSatelitesDTO;
import com.daniel.quasar.core.dto.MensajesSatelitesDTO;
import com.daniel.quasar.core.entity.Satelite;
import com.daniel.quasar.core.entity.SatelitesOnline;
import com.daniel.quasar.core.enums.NombresSatelites;
import com.daniel.quasar.core.utils.InterseccionEntreCirculos;
import com.daniel.quasar.core.utils.InterseccionEntreCirculosImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
public class InterfazDeSatelites {

    @Qualifier("SistemaDeSatelites")
    @Autowired
    private SatelitesOnline satelites;

    public Coordenada getLocation(){
        validarQueLosSatelitesTenganDatos();
        return getInterseccion(satelites.getDistancias());
    }

    public Coordenada getLocation(DistanciasSatelitesDTO distancias) {
//        System.out.println("Satelites: "+satelites.toString());

        return getInterseccion(distancias.getListaOrdenadaDistancias());
    }


    public String getMessage(){
        return this.getMessage(MensajesSatelitesDTO.crearMensajesSatelitesDTO(satelites.getSatelites()));
    }

    public String getMessage(MensajesSatelitesDTO mensajes) {
        List<String> resultado=combinarMensajes(mensajes);
        return resultado.stream().collect(Collectors.joining(" "));
    }




    private void validarQueLosSatelitesTenganDatos() {
        if(satelites.getSatelites().values().stream()
                .filter(v->v.getDistance()!=null)
                .filter(v2->v2.getMessage()!=null || v2.getMessage().size()>0).count()<3)
            throw new RuntimeException("Los satelites no tienen datos suficientes");
    }

    private Coordenada getInterseccion(List<Double> distancias) {

        InterseccionEntreCirculos calculo = InterseccionEntreCirculosImpl
                .crearCalculadoraSegunSatelites(satelites.getCoordenadasSatelites(),distancias);

        return calculo.obtenerInterseccion();
    }

    public void enviarMensajeYDistanciaSatelite(NombresSatelites name,Double distancia,List<String> mensaje ){
        Satelite satelite=(satelites.getSatelites().get(name));
        satelite.setDistance(distancia);
        satelite.setMessage(mensaje);
    }

    private List<String> combinarMensajes(MensajesSatelitesDTO mensajes) {
        HashMap<String,Integer> puntuarFrases=new HashMap();
        puntuarFrases=puntuar(mensajes.getMensajeAKenobi(),puntuarFrases);
        puntuarFrases=puntuar(mensajes.getMensajeASkywalker(),puntuarFrases);
        puntuarFrases=puntuar(mensajes.getMensajeASato(),puntuarFrases);


        List<String> resultado=puntuarFrases.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return resultado;
    }

    private HashMap<String,Integer> puntuar(List<String> frase,HashMap<String,Integer> puntuar) {
        for(int i = 1; i<= frase.size(); i++){
            if(!frase.get(i-1).isEmpty()){
                if(puntuar.containsKey(frase.get(i-1)))
                    puntuar.put(frase.get(i-1),puntuar.get(frase.get(i-1))+i);
                else
                    puntuar.put(frase.get(i-1),i);
            }
        }
        return puntuar;
    }


}
