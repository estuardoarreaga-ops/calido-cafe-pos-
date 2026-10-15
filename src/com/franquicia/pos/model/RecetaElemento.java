package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "recetas_elementos")
public class RecetaElemento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id", nullable = false)
    private Insumo insumo;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal cantidadRequerida; // Ej: 0.250 para 250ml de leche

    public RecetaElemento() {}

    public RecetaElemento(Producto producto, Insumo insumo, BigDecimal cantidadRequerida) {
        this.producto = producto;
        this.insumo = insumo;
        this.cantidadRequerida = cantidadRequerida;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Insumo getInsumo() { return insumo; }
    public void setInsumo(Insumo insumo) { this.insumo = insumo; }

    public BigDecimal getCantidadRequerida() { return cantidadRequerida; }
    public void setCantidadRequerida(BigDecimal cantidadRequerida) { this.cantidadRequerida = cantidadRequerida; }
}