package com.example.polizas.domain;

public class Riesgo {

    private Long id;
    private Long polizaId;
    private String descripcion;
    private EstadoRiesgo estado;

    public Riesgo() {
    }

    public Riesgo(Long id, Long polizaId, String descripcion, EstadoRiesgo estado) {
        this.id = id;
        this.polizaId = polizaId;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPolizaId() {
        return polizaId;
    }

    public void setPolizaId(Long polizaId) {
        this.polizaId = polizaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoRiesgo getEstado() {
        return estado;
    }

    public void setEstado(EstadoRiesgo estado) {
        this.estado = estado;
    }
}