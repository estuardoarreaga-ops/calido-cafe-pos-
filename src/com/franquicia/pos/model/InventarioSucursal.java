package com.franquicia.pos.model;

public class InventarioSucursal {
    private String sucursalId;
    private String productoId;
    private double stockActual;

    public InventarioSucursal(String sucursalId, String productoId, double stockInicial) {
        this.sucursalId = sucursalId;
        this.productoId = productoId;
        this.stockActual = stockInicial;
    }

    public synchronized boolean descontarStock(double cantidad) {
        if (this.stockActual >= cantidad) {
            this.stockActual -= cantidad;
            return true;
        }
        return false;
    }

    public synchronized void reponerStock(double cantidad) {
        this.stockActual += cantidad;
    }

    public double getStockActual() { return stockActual; }
    public String getProductoId() { return productoId; }
}