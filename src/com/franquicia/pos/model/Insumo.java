package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "insumos")
public class Insumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long franquiciaId;

    @Column(nullable = false, length = 100)
    private String nombre; // Ej: "Harina de Trigo", "Leche Entera", "Grano de Café"

    @Column(nullable = false, length = 20)
    private String unidadMedida; // "KG", "LITRO", "GRAMO", "UNIDAD"

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal stockActual;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    public Insumo() {}

    public Insumo(Long franquiciaId, String nombre, String unidadMedida, BigDecimal stockActual, BigDecimal costoUnitario) {
        this.franquiciaId = franquiciaId;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stockActual = stockActual;
        this.costoUnitario = costoUnitario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFranquiciaId() { return franquiciaId; }
    public void setFranquiciaId(Long franquiciaId) { this.franquiciaId = franquiciaId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public BigDecimal getStockActual() { return stockActual; }
    public void setStockActual(BigDecimal stockActual) { this.stockActual = stockActual; }

    public BigDecimal getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(BigDecimal costoUnitario) { this.costoUnitario = costoUnitario; }
}