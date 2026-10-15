package com.franquicia.pos.repository;

import com.franquicia.pos.model.RecetaElemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaElementoRepository extends JpaRepository<RecetaElemento, Long> {
    List<RecetaElemento> findByProductoId(Long productoId);
    void deleteByProductoId(Long productoId);
}