package com.franquicia.pos.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String rol;

    @Column(nullable = false)
    private Long franquiciaId;

    public Usuario() {}

    public Usuario(String username, String password, String nombre, String rol, Long franquiciaId) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.franquiciaId = franquiciaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Long getFranquiciaId() { return franquiciaId; }
    public void setFranquiciaId(Long franquiciaId) { this.franquiciaId = franquiciaId; }
}