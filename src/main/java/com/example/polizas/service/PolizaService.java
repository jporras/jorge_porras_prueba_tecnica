package com.example.polizas.service;

import com.example.polizas.domain.EstadoPoliza;
import com.example.polizas.domain.EstadoRiesgo;
import com.example.polizas.domain.Poliza;
import com.example.polizas.domain.Riesgo;
import com.example.polizas.domain.TipoPoliza;
import com.example.polizas.exception.BusinessException;
import com.example.polizas.exception.NotFoundException;
import com.example.polizas.repository.PolizaRepository;
import com.example.polizas.repository.RiesgoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;
    private final CoreIntegrationService coreIntegrationService;

    public PolizaService(PolizaRepository polizaRepository,
                         RiesgoRepository riesgoRepository,
                         CoreIntegrationService coreIntegrationService) {
        this.polizaRepository = polizaRepository;
        this.riesgoRepository = riesgoRepository;
        this.coreIntegrationService = coreIntegrationService;
    }

    public List<Poliza> listar(String tipo, String estado) {
        return polizaRepository.findAll().stream()
                .filter(p -> tipo == null || p.getTipo().name().equalsIgnoreCase(tipo))
                .filter(p -> estado == null || p.getEstado().name().equalsIgnoreCase(estado))
                .toList();
    }

    public List<Riesgo> obtenerRiesgos(Long polizaId) {
        validarPolizaExiste(polizaId);
        return riesgoRepository.findByPolizaId(polizaId);
    }

    public Poliza renovar(Long polizaId, BigDecimal ipc) {
        Poliza poliza = obtenerPoliza(polizaId);

        if (poliza.isCancelada()) {
            throw new BusinessException("No se puede renovar una póliza cancelada");
        }

        BigDecimal factor = BigDecimal.ONE.add(ipc);
        BigDecimal nuevoCanon = poliza.getCanonMensual().multiply(factor);
        BigDecimal nuevaPrima = nuevoCanon.multiply(BigDecimal.valueOf(poliza.getVigenciaMeses()));

        poliza.setCanonMensual(nuevoCanon);
        poliza.setPrima(nuevaPrima);
        poliza.setEstado(EstadoPoliza.RENOVADA);

        polizaRepository.save(poliza);
        coreIntegrationService.registrarActualizacion(polizaId);
        return poliza;
    }

    public Poliza cancelar(Long polizaId) {
        Poliza poliza = obtenerPoliza(polizaId);
        poliza.setEstado(EstadoPoliza.CANCELADA);

        List<Riesgo> riesgos = riesgoRepository.findByPolizaId(polizaId);
        riesgos.forEach(r -> {
            r.setEstado(EstadoRiesgo.CANCELADO);
            riesgoRepository.save(r);
        });

        polizaRepository.save(poliza);
        coreIntegrationService.registrarActualizacion(polizaId);
        return poliza;
    }

    public Riesgo agregarRiesgo(Long polizaId, Riesgo riesgo) {
        Poliza poliza = obtenerPoliza(polizaId);

        if (!TipoPoliza.COLECTIVA.equals(poliza.getTipo())) {
            throw new BusinessException("Solo una póliza colectiva puede agregar riesgos");
        }

        long cantidadRiesgos = riesgoRepository.findByPolizaId(polizaId).size();
        long nuevoId = cantidadRiesgos + 100L;

        riesgo.setId(riesgo.getId() != null ? riesgo.getId() : nuevoId);
        riesgo.setPolizaId(polizaId);
        riesgo.setEstado(EstadoRiesgo.ACTIVO);

        Riesgo saved = riesgoRepository.save(riesgo);

        poliza.getRiesgos().add(saved);
        polizaRepository.save(poliza);
        coreIntegrationService.registrarActualizacion(polizaId);
        return saved;
    }

    private Poliza obtenerPoliza(Long id) {
        return polizaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Póliza no encontrada"));
    }

    private void validarPolizaExiste(Long id) {
        obtenerPoliza(id);
    }
}