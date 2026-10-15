package com.franquicia.pos.controller;

import com.franquicia.pos.model.Insumo;
import com.franquicia.pos.model.Merma;
import com.franquicia.pos.model.Producto;
import com.franquicia.pos.repository.InsumoRepository;
import com.franquicia.pos.repository.MermaRepository;
import com.franquicia.pos.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mermas")
@CrossOrigin(origins = "*")
public class MermaController {

    private final MermaRepository mermaRepository;
    private final ProductoRepository productoRepository;
    private final InsumoRepository insumoRepository;

    public MermaController(MermaRepository mermaRepository,
                           ProductoRepository productoRepository,
                           InsumoRepository insumoRepository) {
        this.mermaRepository = mermaRepository;
        this.productoRepository = productoRepository;
        this.insumoRepository = insumoRepository;
    }

    @GetMapping("/franquicia/{franquiciaId}")
    public List<Merma> listarPorFranquicia(@PathVariable Long franquiciaId) {
        return mermaRepository.findByFranquiciaId(franquiciaId);
    }

    @PostMapping
    public ResponseEntity<?> registrarMerma(@RequestBody Merma merma) {
        if ("PRODUCTO".equalsIgnoreCase(merma.getTipoItem())) {
            Producto prod = productoRepository.findById(merma.getItemId()).orElse(null);
            if (prod == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Producto no encontrado"));
            }
            int cantidadRestar = merma.getCantidad().intValue();
            int nuevoStock = Math.max(0, prod.getStock() - cantidadRestar);
            prod.setStock(nuevoStock);
            productoRepository.save(prod);

        } else if ("INSUMO".equalsIgnoreCase(merma.getTipoItem())) {
            Insumo insumo = insumoRepository.findById(merma.getItemId()).orElse(null);
            if (insumo == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Insumo no encontrado"));
            }
            insumo.setStockActual(insumo.getStockActual().subtract(merma.getCantidad()));
            insumoRepository.save(insumo);
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Tipo de item inválido. Debe ser PRODUCTO o INSUMO"));
        }

        Merma guardada = mermaRepository.save(merma);
        return ResponseEntity.ok(guardada);
    }
}