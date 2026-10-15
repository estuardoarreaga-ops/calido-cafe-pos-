package com.franquicia.pos.service;

import com.franquicia.pos.model.*;
import com.franquicia.pos.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final CajaTurnoRepository cajaTurnoRepository;
    private final RecetaElementoRepository recetaElementoRepository;
    private final InsumoRepository insumoRepository;

    public VentaService(VentaRepository ventaRepository,
                        ProductoRepository productoRepository,
                        CajaTurnoRepository cajaTurnoRepository,
                        RecetaElementoRepository recetaElementoRepository,
                        InsumoRepository insumoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.cajaTurnoRepository = cajaTurnoRepository;
        this.recetaElementoRepository = recetaElementoRepository;
        this.insumoRepository = insumoRepository;
    }

    public Map<String, String> consultarNitSat(String nit) {
        Map<String, String> cliente = new HashMap<>();
        String nitLimpio = nit.trim().toUpperCase();

        if (nitLimpio.equals("CF") || nitLimpio.equals("C/F") || nitLimpio.isEmpty()) {
            cliente.put("nit", "CF");
            cliente.put("nombre", "CONSUMIDOR FINAL");
        } else if (nitLimpio.equals("12345678")) {
            cliente.put("nit", "12345678");
            cliente.put("nombre", "DISTRIBUIDORA Y PASTELERIAS S.A.");
        } else {
            cliente.put("nit", nitLimpio);
            cliente.put("nombre", "CONTRIBUYENTE REGISTRADO EN SAT (" + nitLimpio + ")");
        }
        return cliente;
    }

    @Transactional
    public Venta procesarCobro(Long usuarioId, Venta venta, BigDecimal efectivoRecibido) {
        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getId()));

            BigDecimal cantidadVendida = new BigDecimal(detalle.getCantidad());

            // 1. REVISAR SI EL PRODUCTO TIENE FICHA TÉCNICA (RECETA)
            List<RecetaElemento> receta = recetaElementoRepository.findByProductoId(producto.getId());

            if (!receta.isEmpty()) {
                // Descontar Insumos / Materia Prima (Cafeterías, Pastelerías, Restaurantes)
                for (RecetaElemento ingrediente : receta) {
                    Insumo insumo = ingrediente.getInsumo();
                    BigDecimal consumoTotal = ingrediente.getCantidadRequerida().multiply(cantidadVendida);

                    if (insumo.getStockActual().compareTo(consumoTotal) < 0) {
                        throw new IllegalArgumentException("Insumo insuficiente (" + insumo.getNombre() +
                                ") para preparar: " + producto.getNombre());
                    }

                    insumo.setStockActual(insumo.getStockActual().subtract(consumoTotal));
                    insumoRepository.save(insumo);
                }
            } else {
                // Deducción Directa de Producto Terminado (Retail / Reventa)
                if (producto.getStock() != null) {
                    if (producto.getStock() < detalle.getCantidad()) {
                        throw new IllegalArgumentException("Stock insuficiente para: " + producto.getNombre() +
                                ". Disponible: " + producto.getStock());
                    }
                    producto.setStock(producto.getStock() - detalle.getCantidad());
                    productoRepository.save(producto);
                }
            }

            detalle.setProducto(producto);
            detalle.setPrecioUnitario(producto.getPrecioVenta());

            BigDecimal subtotal = producto.getPrecioVenta().multiply(cantidadVendida);
            detalle.setSubtotal(subtotal);

            totalCalculado = totalCalculado.add(subtotal);
        }

        venta.setTotal(totalCalculado);

        if (efectivoRecibido.compareTo(totalCalculado) < 0) {
            throw new IllegalArgumentException("El efectivo es menor al total de la venta");
        }

        // Registrar en turno activo
        Optional<CajaTurno> turnoOpt = cajaTurnoRepository
                .findFirstByUsuarioAndFranquiciaIdAndEstadoOrderByIdDesc(
                        venta.getNombreCliente() != null ? "cajero1" : "admin",
                        venta.getFranquiciaId(),
                        "ABIERTO");

        if (turnoOpt.isPresent()) {
            CajaTurno turno = turnoOpt.get();
            turno.setVentasEfectivo(turno.getVentasEfectivo().add(totalCalculado));
            cajaTurnoRepository.save(turno);
        }

        return ventaRepository.save(venta);
    }
}