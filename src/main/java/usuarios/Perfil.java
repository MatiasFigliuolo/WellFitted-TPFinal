package usuarios;

import tienda.Carrito;
import tienda.Producto;

import java.util.ArrayList;

public class Perfil extends Usuario implements Comparable<Usuario>{

    private Carrito carrito;
    private ArrayList<Carrito> historial;

    public Perfil(String nombre, String email) {
        super(nombre, email);
        this.carrito = new Carrito();
        this.historial = new ArrayList<>();
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Carrito> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Carrito> historial) {
        this.historial = historial;
    }

    public void agregarCarrito(Producto producto)
    {
        carrito.agregar(producto);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
