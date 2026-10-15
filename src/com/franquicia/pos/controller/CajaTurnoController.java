package com.franquicia.pos.controller;

import com.franquicia.pos.model.CajaTurno;
import com.franquicia.pos.repository.CajaTurnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/caja")
@CrossOrigin(origins = "*")
public class CajaTurnoController {

    private final CajaTurnoRepository cajaTurnoRepository;

    public CajaTurnoController(CajaTurnoRepository cajaTurnoRepository) {
        this.cajaTurnoRepository = cajaTurnoRepository;
    }

    @GetMapping("/estado")
    public ResponseEntity<?> obtenerEstadoTurno(@RequestParam String usuario, @RequestParam String franquiciaId) {
        Optional<CajaTurno> turnoOpt = cajaTurnoRepository
                    .findFirstByUsuarioAndFranquiciaIdAndEstadoOrderByIdDesc(usuario, franquiciaId, "ABIERTO");

        Map<String, Object> response = new HashMap<>();
        if (turnoOpt.isPresent()) {
            response.put("abierto", true);
            response.put("turno", turnoOpt.get());
        } else {
            response.put("abierto", false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/abrir")
    public ResponseEntity<?> abrirTurno(@RequestBody Map<String, Object> request) {
        String usuario = (String) request.get("usuario");
        String franquiciaId = (String) request.get("franquiciaId");
        BigDecimal fondoInicial = new BigDecimal(request.get("fondoInicial").toString());

        Optional<CajaTurno> turnoExistente = cajaTurnoRepository
                .findFirstByUsuarioAndFranquiciaIdAndEstadoOrderByIdDesc(usuario, franquiciaId, "ABIERTO");

        if (turnoExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un turno abierto para este usuario.");
        }

        CajaTurno nuevoTurno = new CajaTurno(usuario, franquiciaId, fondoInicial);
        CajaTurno guardado = cajaTurnoRepository.save(nuevoTurno);

        return ResponseEntity.ok(guardado);
    }

    @PostMapping("/cerrar")
    public ResponseEntity<?> cerrarTurno(@RequestBody Map<String, Object> request) {
        Long turnoId = Long.parseLong(request.get("turnoId").toString());
        BigDecimal totalDeclarado = new BigDecimal(request.get("totalDeclarado").toString());

        CajaTurno turno = cajaTurnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (!"ABIERTO".equals(turno.getEstado())) {
            return ResponseEntity.badRequest().body("El turno ya se encuentra cerrado.");
        }

        turno.setFechaCierre(LocalDateTime.now());
        turno.setTotalDeclarado(totalDeclarado);

        BigDecimal esperado = turno.getFondoInicial().add(turno.getVentasEfectivo());
        turno.setTotalEsperado(esperado);

        BigDecimal diferencia = totalDeclarado.subtract(esperado);
        turno.setDiferencia(diferencia);
        turno.setEstado("CERRADO");

        CajaTurno cerrado = cajaTurnoRepository.save(turno);

        return ResponseEntity.ok(cerrado);
    }
}