package tienda;

import Interfazes.Agregable;
import Interfazes.Quitable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Carrito implements Agregable<Producto>, Quitable<String> {


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
    public Boolean agregar(Producto producto)
    {
       return productos.add(producto);
    }

    public Boolean quitar(String id)
    {
        int pos=-1;
        for(int i=0;i<productos.size();i++)
        {
            if(productos.get(i).comparar(id))
            {
                pos=i;
            }
        }

        if(pos!=-1)
        {
            productos.remove(pos);
            System.out.println("! Producto eliminado con exito !");
            return true;
        }
        return false;
    }

    public  void mostrarCarrito() {
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Carrito de compras:  (" +fecha.format(DateTimeFormatter.ISO_DATE_TIME) + ")");
            productos.forEach(System.out::println);
        }
    }

    public void limpiarCarrito()
    {
        productos.clear();
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "fecha=" + fecha +
                ", productos= " + productos +
                '}';
    }

}
