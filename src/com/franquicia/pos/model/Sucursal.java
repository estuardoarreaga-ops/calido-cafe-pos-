package com.franquicia.pos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;

    public Sucursal() {}

    // Constructor completo de 4 parámetros
    public Sucursal(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor de 3 parámetros por compatibilidad
    public Sucursal(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = "N/A";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}