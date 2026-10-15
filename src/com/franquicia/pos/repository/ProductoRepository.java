package com.franquicia.pos.repository;

import com.franquicia.pos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByFranquiciaId(Long franquiciaId);
    List<Producto> findByFranquiciaIdAndCategoria(Long franquiciaId, String categoria);
    Producto findByFranquiciaIdAndCodigoBarras(Long franquiciaId, String codigoBarras);
}