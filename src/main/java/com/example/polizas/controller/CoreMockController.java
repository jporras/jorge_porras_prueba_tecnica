package com.example.polizas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    private static final Logger log = LoggerFactory.getLogger(CoreMockController.class);

    @PostMapping("/evento")
    public ResponseEntity<Map<String, Object>> evento(@RequestBody Map<String, Object> body) {
        log.info("Mock CORE recibió evento: {}", body);
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "mensaje", "Evento registrado en logs"
        ));
    }
}