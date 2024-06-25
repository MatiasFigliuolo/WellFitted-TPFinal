package Factura;
import tienda.Producto;

import java.time.LocalDateTime;
import java.util.List;

public class Factura {
    private LocalDateTime fecha;
    private String cliente;
    private List<Producto> productos;
    private double total;

    public Factura() {
    }

    // Getters y Setters


    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
