package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private BigDecimal total;
    private String franquiciaId;

    // Campos para Facturación SAT
    private String nitCliente;
    private String nombreCliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venta_id")
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta() {
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
        this.nitCliente = "CF";
        this.nombreCliente = "CONSUMIDOR FINAL";
    }

    public Venta(String franquiciaId) {
        this();
        this.franquiciaId = franquiciaId;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getFranquiciaId() { return franquiciaId; }
    public void setFranquiciaId(String franquiciaId) { this.franquiciaId = franquiciaId; }

    public String getNitCliente() { return nitCliente; }
    public void setNitCliente(String nitCliente) { this.nitCliente = nitCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
}