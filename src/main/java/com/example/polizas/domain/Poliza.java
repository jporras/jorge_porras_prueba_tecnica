package com.example.polizas.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Poliza {

    private Long id;
    private TipoPoliza tipo;
    private EstadoPoliza estado;
    private BigDecimal canonMensual;
    private BigDecimal prima;
    private Integer vigenciaMeses;
    private List<Riesgo> riesgos = new ArrayList<>();

    public Poliza() {
    }

    public Poliza(Long id,
                  TipoPoliza tipo,
                  EstadoPoliza estado,
                  BigDecimal canonMensual,
                  BigDecimal prima,
                  Integer vigenciaMeses) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.canonMensual = canonMensual;
        this.prima = prima;
        this.vigenciaMeses = vigenciaMeses;
    }

    public boolean isCancelada() {
        return EstadoPoliza.CANCELADA.equals(this.estado);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPoliza getTipo() {
        return tipo;
    }

    public void setTipo(TipoPoliza tipo) {
        this.tipo = tipo;
    }

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }

    public BigDecimal getCanonMensual() {
        return canonMensual;
    }

    public void setCanonMensual(BigDecimal canonMensual) {
        this.canonMensual = canonMensual;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public Integer getVigenciaMeses() {
        return vigenciaMeses;
    }

    public void setVigenciaMeses(Integer vigenciaMeses) {
        this.vigenciaMeses = vigenciaMeses;
    }

    public List<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }
}