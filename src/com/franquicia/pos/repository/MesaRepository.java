package com.franquicia.pos.repository;

import com.franquicia.pos.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByFranquiciaIdOrderByIdAsc(Long franquiciaId);
}