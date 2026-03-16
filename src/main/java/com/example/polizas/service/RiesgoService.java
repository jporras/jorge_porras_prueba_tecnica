package com.example.polizas.service;

import com.example.polizas.domain.EstadoRiesgo;
import com.example.polizas.domain.Riesgo;
import com.example.polizas.exception.NotFoundException;
import com.example.polizas.repository.RiesgoRepository;
import org.springframework.stereotype.Service;

@Service
public class RiesgoService {

    private final RiesgoRepository riesgoRepository;
    private final CoreIntegrationService coreIntegrationService;

    public RiesgoService(RiesgoRepository riesgoRepository,
                         CoreIntegrationService coreIntegrationService) {
        this.riesgoRepository = riesgoRepository;
        this.coreIntegrationService = coreIntegrationService;
    }

    public Riesgo cancelar(Long riesgoId) {
        Riesgo riesgo = riesgoRepository.findById(riesgoId)
                .orElseThrow(() -> new NotFoundException("Riesgo no encontrado"));

        riesgo.setEstado(EstadoRiesgo.CANCELADO);
        riesgoRepository.save(riesgo);
        coreIntegrationService.registrarActualizacion(riesgo.getPolizaId());
        return riesgo;
    }
}