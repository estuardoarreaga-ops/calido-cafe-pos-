package com.franquicia.pos.repository;

import com.franquicia.pos.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByFranquiciaIdOrderByIdAsc(Long franquiciaId);
}