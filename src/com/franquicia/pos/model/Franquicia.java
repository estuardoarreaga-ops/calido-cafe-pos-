package com.franquicia.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "franquicias")
public class Franquicia {
    @Id
    private String id;

    @Column(nullable = false)
    private String nombreComercial;

    private String rucOIdentificacionFiscal;
    private boolean activa;

    public Franquicia() {} // Constructor vacío requerido por JPA

    public Franquicia(String id, String nombreComercial, String rucOIdentificacionFiscal) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.rucOIdentificacionFiscal = rucOIdentificacionFiscal;
        this.activa = true;
    }

    public String getId() { return id; }
    public String getNombreComercial() { return nombreComercial; }
    public boolean isActiva() { return activa; }
}