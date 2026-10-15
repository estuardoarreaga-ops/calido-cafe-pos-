package com.franquicia.pos.controller;

import com.franquicia.pos.model.Usuario;
import com.franquicia.pos.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Debe proporcionar usuario y contraseña.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username.trim());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getPassword().equals(password.trim())) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Inicio de sesión exitoso");
                response.put("usuario", usuario.getUsername());
                response.put("nombre", usuario.getNombre());
                response.put("rol", usuario.getRol());
                response.put("franquiciaId", usuario.getFranquiciaId());
                return ResponseEntity.ok(response);
            }
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Credenciales incorrectas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}