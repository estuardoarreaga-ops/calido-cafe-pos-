package com.franquicia.pos.controller;

import com.franquicia.pos.model.Usuario;
import com.franquicia.pos.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // --- ENDPOINT DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        return usuarioRepository.findByUsernameAndPassword(username, password)
                .map(usuario -> ResponseEntity.ok(Map.of(
                        "mensaje", "Login exitoso",
                        "username", usuario.getUsername(),
                        "nombre", usuario.getNombre(),
                        "rol", usuario.getRol(),
                        "franquiciaId", usuario.getFranquiciaId()
                )))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas")));
    }
}