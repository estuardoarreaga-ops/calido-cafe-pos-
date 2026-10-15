package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "caja_turnos")
public class CajaTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String franquiciaId;

    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;

    private BigDecimal fondoInicial;
    private BigDecimal ventasEfectivo;
    private BigDecimal totalEsperado;
    private BigDecimal totalDeclarado;
    private BigDecimal diferencia;

    private String estado; // "ABIERTO", "CERRADO"

    public CajaTurno() {}

    public CajaTurno(String usuario, String franquiciaId, BigDecimal fondoInicial) {
        this.usuario = usuario;
        this.franquiciaId = franquiciaId;
        this.fondoInicial = fondoInicial;
        this.fechaApertura = LocalDateTime.now();
        this.ventasEfectivo = BigDecimal.ZERO;
        this.totalEsperado = fondoInicial;
        this.totalDeclarado = BigDecimal.ZERO;
        this.diferencia = BigDecimal.ZERO;
        this.estado = "ABIERTO";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getFranquiciaId() { return franquiciaId; }
    public void setFranquiciaId(String franquiciaId) { this.franquiciaId = franquiciaId; }

    public LocalDateTime getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDateTime fechaApertura) { this.fechaApertura = fechaApertura; }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }

    public BigDecimal getFondoInicial() { return fondoInicial; }
    public void setFondoInicial(BigDecimal fondoInicial) { this.fondoInicial = fondoInicial; }

    public BigDecimal getVentasEfectivo() { return ventasEfectivo; }
    public void setVentasEfectivo(BigDecimal ventasEfectivo) { this.ventasEfectivo = ventasEfectivo; }

    public BigDecimal getTotalEsperado() { return totalEsperado; }
    public void setTotalEsperado(BigDecimal totalEsperado) { this.totalEsperado = totalEsperado; }

    public BigDecimal getTotalDeclarado() { return totalDeclarado; }
    public void setTotalDeclarado(BigDecimal totalDeclarado) { this.totalDeclarado = totalDeclarado; }

    public BigDecimal getDiferencia() { return diferencia; }
    public void setDiferencia(BigDecimal diferencia) { this.diferencia = diferencia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}