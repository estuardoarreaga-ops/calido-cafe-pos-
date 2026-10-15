package com.franquicia.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    private final Map<String, Map<String, Object>> estadoMesas = new ConcurrentHashMap<>();

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerMesas() {
        return ResponseEntity.ok(new ArrayList<>(estadoMesas.values()));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> actualizarMesa(@RequestBody Map<String, Object> mesa) {
        Object numMesa = mesa.get("numero");
        if (numMesa == null) {
            numMesa = mesa.get("id");
        }
        if (numMesa != null) {
            estadoMesas.put(numMesa.toString(), mesa);
        }
        return ResponseEntity.ok(mesa);
    }

    @DeleteMapping("/{numMesa}")
    public ResponseEntity<Void> liberarMesa(@PathVariable String numMesa) {
        estadoMesas.remove(numMesa);
        return ResponseEntity.ok().build();
    }
}