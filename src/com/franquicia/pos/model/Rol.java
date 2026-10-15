package com.franquicia.pos.model;

public enum Rol {
    ADMIN_GLOBAL,       // SuperAdmin del SaaS (nosotros)
    FRANQUICIA_ADMIN,   // Dueño de la franquicia (ve las 11 sucursales)
    SUPERVISOR_TIENDA,  // Supervisor de 1 o varias tiendas
    CAJERO              // Empleado de caja (solo cobro y emisión)
}