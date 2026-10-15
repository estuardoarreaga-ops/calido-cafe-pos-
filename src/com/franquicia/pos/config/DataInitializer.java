package com.franquicia.pos.config;

import com.franquicia.pos.model.Insumo;
import com.franquicia.pos.model.Producto;
import com.franquicia.pos.model.Usuario;
import com.franquicia.pos.repository.InsumoRepository;
import com.franquicia.pos.repository.ProductoRepository;
import com.franquicia.pos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final InsumoRepository insumoRepository;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           ProductoRepository productoRepository,
                           InsumoRepository insumoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.insumoRepository = insumoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. USUARIOS INICIALES
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setNombre("Administrador Principal");
            admin.setRol("ADMIN");
            admin.setFranquiciaId(1L);

            Usuario cajero = new Usuario();
            cajero.setUsername("cajero1");
            cajero.setPassword("cajero123");
            cajero.setNombre("Cajero Turno Mañana");
            cajero.setRol("CAJERO");
            cajero.setFranquiciaId(1L);

            usuarioRepository.save(admin);
            usuarioRepository.save(cajero);
            System.out.println("✅ Usuarios creados correctamente.");
        }

        // 2. INSUMOS BASE (CON COSTO UNITARIO PARA EVITAR ERROR NOT-NULL)
        if (insumoRepository.count() == 0) {
            List<Insumo> insumos = new ArrayList<>();

            insumos.add(crearInsumo("Café en Grano", "Gramos", new BigDecimal("10000.00"), new BigDecimal("0.15"), 1L));
            insumos.add(crearInsumo("Leche Entera", "Mililitros", new BigDecimal("20000.00"), new BigDecimal("0.02"), 1L));
            insumos.add(crearInsumo("Leche de Avena", "Mililitros", new BigDecimal("5000.00"), new BigDecimal("0.04"), 1L));
            insumos.add(crearInsumo("Leche de Almendra", "Mililitros", new BigDecimal("5000.00"), new BigDecimal("0.04"), 1L));
            insumos.add(crearInsumo("Polvo Matcha Premium", "Gramos", new BigDecimal("2000.00"), new BigDecimal("0.50"), 1L));
            insumos.add(crearInsumo("Jarabe Vainilla", "Mililitros", new BigDecimal("3000.00"), new BigDecimal("0.05"), 1L));
            insumos.add(crearInsumo("Jarabe Caramelo", "Mililitros", new BigDecimal("3000.00"), new BigDecimal("0.05"), 1L));
            insumos.add(crearInsumo("Jarabe Lavanda", "Mililitros", new BigDecimal("2000.00"), new BigDecimal("0.06"), 1L));

            insumoRepository.saveAll(insumos);
            System.out.println("✅ Insumos base cargados en el inventario.");
        }

        // 3. MENÚ COMPLETO DEL CLIENTE (PRODUCTOS)
        if (productoRepository.count() == 0) {
            Long fId = 1L;
            List<Producto> menu = new ArrayList<>();

            // --- FILTRADOS ---
            menu.add(crearProd("V60 / Origami / Chemex (10 oz)", "P001", new BigDecimal("30.00"), 100, "Filtrados", fId));

            // --- CALIENTES ---
            menu.add(crearProd("Espresso", "P002", new BigDecimal("20.00"), 100, "Calientes", fId));
            menu.add(crearProd("Cortadito (4 oz)", "P003", new BigDecimal("22.00"), 100, "Calientes", fId));
            menu.add(crearProd("Americano (12 oz)", "P004", new BigDecimal("22.00"), 100, "Calientes", fId));
            menu.add(crearProd("Flat White (6 oz)", "P005", new BigDecimal("26.00"), 100, "Calientes", fId));
            menu.add(crearProd("Cappuccino (12 oz)", "P006", new BigDecimal("28.00"), 100, "Calientes", fId));
            menu.add(crearProd("Latte (12 oz)", "P007", new BigDecimal("28.00"), 100, "Calientes", fId));
            menu.add(crearProd("Caramel Latte (12 oz)", "P008", new BigDecimal("30.00"), 100, "Calientes", fId));
            menu.add(crearProd("Mocca Latte (12 oz)", "P009", new BigDecimal("30.00"), 100, "Calientes", fId));
            menu.add(crearProd("Matcha Latte Caliente (12 oz)", "P010", new BigDecimal("30.00"), 100, "Calientes", fId));

            // --- FRÍOS (ICED) ---
            menu.add(crearProd("Iced Americano (16 oz)", "P011", new BigDecimal("24.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Latte (16 oz)", "P012", new BigDecimal("30.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Caramel Latte", "P013", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Vainilla Latte", "P014", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Mocca Latte", "P015", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Spanish Latte", "P016", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Honey Latte", "P017", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Lavanda Latte", "P018", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Horchata Latte", "P019", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Iced Cremita Latte", "P020", new BigDecimal("34.00"), 100, "Fríos", fId));
            menu.add(crearProd("Orange Espresso", "P021", new BigDecimal("34.00"), 100, "Fríos", fId));

            // --- MATCHA ---
            menu.add(crearProd("Latte Matcha", "P022", new BigDecimal("34.00"), 100, "Matcha", fId));
            menu.add(crearProd("White Honey Matcha", "P023", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Strawberry Matcha", "P024", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Blueberry Matcha", "P025", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Lavanda Matcha", "P026", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Berry Soda Matcha", "P027", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Lemonade Matcha", "P028", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Orange Tonic Matcha", "P029", new BigDecimal("37.00"), 100, "Matcha", fId));
            menu.add(crearProd("Coco Matcha", "P030", new BigDecimal("37.00"), 100, "Matcha", fId));

            // --- ADICIONALES ---
            menu.add(crearProd("Extra Saborizante (Vainilla/Miel/Lavanda)", "P031", new BigDecimal("4.00"), 500, "Adicionales", fId));
            menu.add(crearProd("Cambio Leche de Avena / Almendra", "P032", new BigDecimal("5.00"), 500, "Adicionales", fId));

            productoRepository.saveAll(menu);
            System.out.println("✅ Menú completo cargado exitosamente.");
        }
    }

    // Auxiliar con costoUnitario asignado
    private Insumo crearInsumo(String nombre, String unidad, BigDecimal stockActual, BigDecimal costoUnitario, Long franquiciaId) {
        Insumo i = new Insumo();
        i.setNombre(nombre);
        i.setUnidadMedida(unidad);
        i.setStockActual(stockActual);
        i.setCostoUnitario(costoUnitario);
        i.setFranquiciaId(franquiciaId);
        return i;
    }

    private Producto crearProd(String nombre, String codigo, BigDecimal precio, Integer stock, String categoria, Long franquiciaId) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setCodigoBarras(codigo);
        p.setPrecioVenta(precio);
        p.setStock(stock);
        p.setCategoria(categoria);
        p.setFranquiciaId(franquiciaId);
        return p;
    }
}