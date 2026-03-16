package com.example.polizas.util;

import com.example.polizas.domain.EstadoPoliza;
import com.example.polizas.domain.EstadoRiesgo;
import com.example.polizas.domain.Poliza;
import com.example.polizas.domain.Riesgo;
import com.example.polizas.domain.TipoPoliza;
import com.example.polizas.repository.PolizaRepository;
import com.example.polizas.repository.RiesgoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;

    public DataLoader(PolizaRepository polizaRepository,
                      RiesgoRepository riesgoRepository) {
        this.polizaRepository = polizaRepository;
        this.riesgoRepository = riesgoRepository;
    }

    @Override
    public void run(String... args) {
        Poliza poliza1 = new Poliza(
                1L,
                TipoPoliza.COLECTIVA,
                EstadoPoliza.ACTIVA,
                new BigDecimal("1200000"),
                new BigDecimal("14400000"),
                12
        );

        Poliza poliza2 = new Poliza(
                2L,
                TipoPoliza.INDIVIDUAL,
                EstadoPoliza.ACTIVA,
                new BigDecimal("800000"),
                new BigDecimal("9600000"),
                12
        );

        Riesgo riesgo1 = new Riesgo(10L, 1L, "Apartamento Bogotá", EstadoRiesgo.ACTIVO);
        Riesgo riesgo2 = new Riesgo(11L, 1L, "Casa Medellín", EstadoRiesgo.ACTIVO);
        Riesgo riesgo3 = new Riesgo(12L, 2L, "Apartamento Cali", EstadoRiesgo.ACTIVO);

        poliza1.setRiesgos(List.of(riesgo1, riesgo2));
        poliza2.setRiesgos(List.of(riesgo3));

        polizaRepository.save(poliza1);
        polizaRepository.save(poliza2);

        riesgoRepository.save(riesgo1);
        riesgoRepository.save(riesgo2);
        riesgoRepository.save(riesgo3);
    }
}