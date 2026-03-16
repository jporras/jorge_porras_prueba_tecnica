package com.example.polizas.controller;

import com.example.polizas.domain.Poliza;
import com.example.polizas.domain.Riesgo;
import com.example.polizas.service.PolizaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public List<Poliza> listar(@RequestParam(required = false) String tipo,
                               @RequestParam(required = false) String estado) {
        return polizaService.listar(tipo, estado);
    }

    @GetMapping("/{id}/riesgos")
    public List<Riesgo> obtenerRiesgos(@PathVariable Long id) {
        return polizaService.obtenerRiesgos(id);
    }

    @PostMapping("/{id}/renovar")
    public Poliza renovar(@PathVariable Long id,
                          @RequestParam(defaultValue = "0.05") BigDecimal ipc) {
        return polizaService.renovar(id, ipc);
    }

    @PostMapping("/{id}/cancelar")
    public Poliza cancelar(@PathVariable Long id) {
        return polizaService.cancelar(id);
    }

    @PostMapping("/{id}/riesgos")
    public Riesgo agregarRiesgo(@PathVariable Long id, @RequestBody Riesgo riesgo) {
        return polizaService.agregarRiesgo(id, riesgo);
    }
}