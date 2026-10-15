package com.franquicia.pos.repository;

import com.franquicia.pos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método necesario para AuthController
    Optional<Usuario> findByUsername(String username);

    // Método para login con contraseña directa
    Optional<Usuario> findByUsernameAndPassword(String username, String password);
}