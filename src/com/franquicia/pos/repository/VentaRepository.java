package com.franquicia.pos.repository;

import com.franquicia.pos.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByFranquiciaId(String franquiciaId);
}