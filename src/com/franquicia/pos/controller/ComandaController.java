package com.franquicia.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/comandas")
@CrossOrigin(origins = "*")
public class ComandaController {

    private final Map<String, Map<String, Object>> comandasActivas = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarComanda(@RequestBody Map<String, Object> comanda) {
        String id = (String) comanda.get("id");
        if (id == null || id.isEmpty()) {
            id = "comanda_" + System.currentTimeMillis();
            comanda.put("id", id);
        }
        comandasActivas.put(id, comanda);
        return ResponseEntity.ok(comanda);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerComandas() {
        return ResponseEntity.ok(new ArrayList<>(comandasActivas.values()));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> actualizarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        if (comandasActivas.containsKey(id)) {
            String nuevoEstado = body.get("estado");
            if ("COMPLETADO".equalsIgnoreCase(nuevoEstado) || "CANCELADO".equalsIgnoreCase(nuevoEstado)) {
                comandasActivas.remove(id);
            } else {
                comandasActivas.get(id).put("estado", nuevoEstado);
            }
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/mesa/{numMesa}")
    public ResponseEntity<Void> liberarComandasMesa(@PathVariable String numMesa) {
        comandasActivas.entrySet().removeIf(entry -> {
            Object mesaObj = entry.getValue().get("numMesa");
            return mesaObj != null && mesaObj.toString().equals(numMesa);
        });
        return ResponseEntity.ok().build();
    }
}