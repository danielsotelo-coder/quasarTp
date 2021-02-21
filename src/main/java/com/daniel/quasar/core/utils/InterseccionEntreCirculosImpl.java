package com.daniel.quasar.core.utils;

import com.daniel.quasar.core.entity.Coordenada;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;


import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InterseccionEntreCirculosImpl implements InterseccionEntreCirculos
{
    private Integer A;
    private Integer B;
    private Integer C;
    private Integer D;
    private Integer E;
    private Integer F;


    public Coordenada obtenerInterseccion(){
        return Coordenada.builder()
                .x(calcularCoordenadaX())
                .y(calcularCoordenadaY())
                .build();
    };

    private Integer calcularCoordenadaX(){
        return ((C*E - F*B) / (E*A - B*D));
    }
    private Integer calcularCoordenadaY(){
        return ((C*D - A*F) / (B*D - A*E));
    }

    public static InterseccionEntreCirculosImpl crearCalculadoraSegunSatelites(List<Coordenada> satelites, List<Double> distancias){
       Coordenada s1=satelites.get(0);
       Double d1=distancias.get(0);
       Coordenada s2= satelites.get(1);
       Double d2=distancias.get(1);
       Coordenada s3= satelites.get(2);
       Double d3=distancias.get(2);


        validarQueSatelitesFormanTriangulo(satelites);

        return InterseccionEntreCirculosImpl.builder()
                .A(2 * s2.getX() - 2 * s1.getX())
                .B(2 * s2.getY() - 2 * s1.getY())
                .C((int)(pow(d1,2) - pow(d2, 2) - pow(s1.getX(), 2) + pow(s2.getX(), 2) - pow(s1.getY(), 2) + pow(s2.getY(), 2)))
                .D(2 * s3.getX() - 2 * s2.getX())
                .E(2 * s3.getY() - 2 * s2.getY())
                .F((int)(pow(d2, 2) - pow(d3, 2) - pow(s2.getX(), 2) + pow(s3.getX(), 2) - pow(s2.getY(), 2) + pow(s3.getY(), 2)))
                .build();
    }

    private static void validarQueSatelitesFormanTriangulo(List<Coordenada> satelites) {
        Integer a = satelites.get(0).getX() * (satelites.get(1).getY()-satelites.get(2).getY()) +
                    satelites.get(1).getX() * (satelites.get(2).getY()-satelites.get(0).getY()) +
                    satelites.get(2).getX() * (satelites.get(0).getY()-satelites.get(1).getY());

        if(a.equals(0)) throw new RuntimeException("El area entre los tres puntos no forman un triangulo");

    }


    private static Double calcularDistanciaCoordenadas(Coordenada p1, Coordenada p2) {
        return sqrt(pow(p1.getX()-p2.getX(),2)+pow(p1.getY()-p2.getY(),2));
    }


}
