package tienda;

import java.io.Serializable;

public abstract class Producto implements Comparable<Producto>, Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;

    private String id;

    private int stock;
    private Number precio;

    public Producto(String nombre, String id, int stock, Number precio) {
        this.nombre = nombre;
        this.id = id;
        this.stock = stock;
        this.precio = precio;
    }

    @Override
    public int compareTo(Producto o) {
        return this.id.compareTo(o.id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Number getPrecio() {
        return precio;
    }

    public void setPrecio(Number precio) {
        this.precio = precio;
    }

    public boolean comparar(String id)
    {
        return this.id.equals(id);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", stock=" + stock + '\'' +
                ", precio=" + precio + '\'';
    }
}
