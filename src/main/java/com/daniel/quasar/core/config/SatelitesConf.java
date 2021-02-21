package com.daniel.quasar.core.config;


import com.daniel.quasar.core.entity.Satelite;
import com.daniel.quasar.core.entity.SatelitesOnline;
import com.daniel.quasar.core.entity.Coordenada;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class SatelitesConf {

    @Value("${kenobi.x:-500}")
    private Integer kenobix;

    @Value("${kenobi.y:-200}")
    private Integer kenobiy;

    @Value("${skywalker.x:100}")
    private Integer skywalkerx;

    @Value("${skywalker.y:-100}")
    private Integer skywalkery;

    @Value("${sato.x:500}")
    private Integer satox;

    @Value("${sato.y:100}")
    private Integer satoy;


    @Bean
    public SatelitesOnline SistemaDeSatelites(){

        SatelitesOnline satelites = new SatelitesOnline(Coordenada.builder().x(kenobix).y(kenobiy).build(),
                                            Coordenada.builder().x(skywalkerx).y(skywalkery).build(),
                                            Coordenada.builder().x(satox).y(satoy).build());

        return satelites;
    }

    @Bean
    public Set<Satelite> interaccionesSatelites() {
        return new HashSet();
    }
}
