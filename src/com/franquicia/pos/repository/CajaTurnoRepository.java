package com.franquicia.pos.repository;

import com.franquicia.pos.model.CajaTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CajaTurnoRepository extends JpaRepository<CajaTurno, Long> {

    // Método para obtener el último turno registrado en la base de datos
    Optional<CajaTurno> findTopByOrderByIdDesc();

    // Método requerido por CajaTurnoController
    Optional<CajaTurno> findFirstByUsuarioAndFranquiciaIdAndEstadoOrderByIdDesc(String usuario, String franquiciaId, String estado);

    // Métodos auxiliares de búsqueda por estado
    boolean existsByEstadoIgnoreCase(String estado);

    Optional<CajaTurno> findFirstByEstadoIgnoreCaseOrderByIdDesc(String estado);
}