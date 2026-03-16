package com.example.polizas.controller;

import com.example.polizas.domain.Riesgo;
import com.example.polizas.service.RiesgoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riesgos")
public class RiesgoController {

    private final RiesgoService riesgoService;

    public RiesgoController(RiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    @PostMapping("/{id}/cancelar")
    public Riesgo cancelar(@PathVariable Long id) {
        return riesgoService.cancelar(id);
    }
}