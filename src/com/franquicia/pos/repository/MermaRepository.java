package com.franquicia.pos.repository;

import com.franquicia.pos.model.Merma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Long> {
    List<Merma> findByFranquiciaId(Long franquiciaId);
}