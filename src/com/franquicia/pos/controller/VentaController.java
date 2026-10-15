package com.franquicia.pos.controller;

import com.franquicia.pos.model.CajaTurno;
import com.franquicia.pos.model.Venta;
import com.franquicia.pos.repository.CajaTurnoRepository;
import com.franquicia.pos.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CajaTurnoRepository cajaTurnoRepository;

    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody Venta venta) {
        // 1. Obtener todos los turnos registrados
        List<CajaTurno> turnos = cajaTurnoRepository.findAll();

        // 2. Si no hay registros de caja, denegar la venta
        if (turnos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "No existe ningún turno de caja registrado. Debe abrir caja antes de realizar ventas."));
        }

        // 3. Obtener el último turno registrado
        CajaTurno ultimoTurno = turnos.get(turnos.size() - 1);
        String estadoActual = ultimoTurno.getEstado();

        // 4. Validar si la caja se encuentra abierta
        boolean estaAbierta = estadoActual != null &&
                (estadoActual.equalsIgnoreCase("ABIERTA") || estadoActual.equalsIgnoreCase("ABIERTO"));

        if (!estaAbierta) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "OPERACIÓN DENEGADA: La caja está CERRADA. Debe abrir un turno primero."));
        }

        // 5. Guardar la venta en la base de datos si la caja está abierta
        Venta nuevaVenta = ventaRepository.save(venta);
        return ResponseEntity.ok(nuevaVenta);
    }
}