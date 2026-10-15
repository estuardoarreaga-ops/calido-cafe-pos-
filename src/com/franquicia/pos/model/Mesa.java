package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mesas")
public class Mesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long franquiciaId;

    @Column(nullable = false, length = 50)
    private String numeroMesa; // Ej: "Mesa 1", "Barra 2", "Terraza 5"

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false, length = 20)
    private String estado; // "LIBRE", "OCUPADA", "CUENTA_PEDIDA", "MANTENIMIENTO"

    public Mesa() {}

    public Mesa(Long franquiciaId, String numeroMesa, Integer capacidad, String estado) {
        this.franquiciaId = franquiciaId;
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFranquiciaId() {
        return franquiciaId;
    }

    public void setFranquiciaId(Long franquiciaId) {
        this.franquiciaId = franquiciaId;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}