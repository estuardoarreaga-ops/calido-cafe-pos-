package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mermas")
public class Merma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long franquiciaId;

    @Column(nullable = false)
    private String tipoItem; // "PRODUCTO" o "INSUMO"

    @Column(nullable = false)
    private Long itemId; // ID de Producto o Insumo

    @Column(nullable = false)
    private String concepto; // "Caducidad", "Dañado", "Preparación incorrecta", etc.

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    private String usuarioResponsable;

    public Merma() {}

    public Merma(Long franquiciaId, String tipoItem, Long itemId, String concepto, BigDecimal cantidad, String usuarioResponsable) {
        this.franquiciaId = franquiciaId;
        this.tipoItem = tipoItem;
        this.itemId = itemId;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.usuarioResponsable = usuarioResponsable;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFranquiciaId() { return franquiciaId; }
    public void setFranquiciaId(Long franquiciaId) { this.franquiciaId = franquiciaId; }

    public String getTipoItem() { return tipoItem; }
    public void setTipoItem(String tipoItem) { this.tipoItem = tipoItem; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getUsuarioResponsable() { return usuarioResponsable; }
    public void setUsuarioResponsable(String usuarioResponsable) { this.usuarioResponsable = usuarioResponsable; }
}