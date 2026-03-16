package com.example.polizas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CoreIntegrationService {

    private static final Logger log = LoggerFactory.getLogger(CoreIntegrationService.class);

    public void registrarActualizacion(Long polizaId) {
        log.info("Intentando enviar evento al CORE. evento=ACTUALIZACION polizaId={}", polizaId);
    }
}