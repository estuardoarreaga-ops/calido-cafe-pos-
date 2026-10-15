package com.franquicia.pos.controller;

import com.franquicia.pos.model.Producto;
import com.franquicia.pos.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/franquicia/{franquiciaId}")
    public ResponseEntity<List<Producto>> listarPorFranquicia(@PathVariable Long franquiciaId) {
        return ResponseEntity.ok(productoRepository.findByFranquiciaId(franquiciaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            Producto nuevo = productoRepository.save(producto);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setCodigoBarras(producto.getCodigoBarras());
                    p.setPrecioVenta(producto.getPrecioVenta());
                    p.setCosto(producto.getCosto());
                    p.setStock(producto.getStock());
                    p.setCategoria(producto.getCategoria());
                    Producto actualizado = productoRepository.save(p);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(p -> {
                    productoRepository.delete(p);
                    return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado correctamente"));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}