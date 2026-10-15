package com.franquicia.pos.controller;

import com.franquicia.pos.model.Sucursal;
import com.franquicia.pos.repository.SucursalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {

    private final SucursalRepository sucursalRepository;

    public SucursalController(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @GetMapping
    public List<Sucursal> obtenerTodas() {
        return sucursalRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalRepository.save(sucursal));
    }
}