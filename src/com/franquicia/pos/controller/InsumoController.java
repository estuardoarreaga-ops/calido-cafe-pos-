package com.franquicia.pos.controller;

import com.franquicia.pos.model.Insumo;
import com.franquicia.pos.model.Producto;
import com.franquicia.pos.model.RecetaElemento;
import com.franquicia.pos.repository.InsumoRepository;
import com.franquicia.pos.repository.ProductoRepository;
import com.franquicia.pos.repository.RecetaElementoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {

    private final InsumoRepository insumoRepository;
    private final RecetaElementoRepository recetaElementoRepository;
    private final ProductoRepository productoRepository;

    public InsumoController(InsumoRepository insumoRepository,
                            RecetaElementoRepository recetaElementoRepository,
                            ProductoRepository productoRepository) {
        this.insumoRepository = insumoRepository;
        this.recetaElementoRepository = recetaElementoRepository;
        this.productoRepository = productoRepository;
    }

    // Listar todos los insumos por franquicia
    @GetMapping("/franquicia/{franquiciaId}")
    public ResponseEntity<List<Insumo>> listarPorFranquicia(@PathVariable Long franquiciaId) {
        return ResponseEntity.ok(insumoRepository.findByFranquiciaIdOrderByIdAsc(franquiciaId));
    }

    // Crear un nuevo insumo/materia prima
    @PostMapping
    public ResponseEntity<?> crearInsumo(@RequestBody Insumo insumo) {
        try {
            Insumo nuevo = insumoRepository.save(insumo);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Obtener la ficha técnica/receta de un producto específico
    @GetMapping("/receta/producto/{productoId}")
    public ResponseEntity<List<RecetaElemento>> obtenerRecetaProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(recetaElementoRepository.findByProductoId(productoId));
    }

    // Asignar o Vincular un ingrediente a una receta
    @PostMapping("/receta")
    @Transactional
    public ResponseEntity<?> agregarIngredienteAReceta(@RequestBody Map<String, Object> payload) {
        try {
            Long productoId = Long.valueOf(payload.get("productoId").toString());
            Long insumoId = Long.valueOf(payload.get("insumoId").toString());
            BigDecimal cantidadRequerida = new BigDecimal(payload.get("cantidadRequerida").toString());

            // findById espera un Long
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

            Insumo insumo = insumoRepository.findById(insumoId)
                    .orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado: " + insumoId));

            RecetaElemento elemento = new RecetaElemento(producto, insumo, cantidadRequerida);
            RecetaElemento guardado = recetaElementoRepository.save(elemento);

            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}