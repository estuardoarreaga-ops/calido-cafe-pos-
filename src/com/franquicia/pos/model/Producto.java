package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long franquiciaId;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 50)
    private String codigoBarras;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(precision = 10, scale = 2)
    private BigDecimal costo;

    private Integer stock;

    @Column(length = 50)
    private String categoria;

    // Constructor vacío (JPA)
    public Producto() {}

    // Constructor recomendado para instanciar sin ID (para inserción nueva)
    public Producto(Long franquiciaId, String nombre, String codigoBarras, BigDecimal precioVenta, BigDecimal costo, Integer stock, String categoria) {
        this.franquiciaId = franquiciaId;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Constructor completo con ID
    public Producto(Long id, Long franquiciaId, String nombre, String codigoBarras, BigDecimal precioVenta, BigDecimal costo, Integer stock, String categoria) {
        this.id = id;
        this.franquiciaId = franquiciaId;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}