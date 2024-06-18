package Tienda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Carrito {

    private LocalDateTime fecha;
    private ArrayList<Producto> productos;

    public Carrito() {
        this.fecha = LocalDateTime.now();
        this.productos = new ArrayList<>();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "fecha=" + fecha +
                ", productos= " + productos +
                '}';
    }
}
