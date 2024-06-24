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

    public Carrito(LocalDateTime fecha, ArrayList<Producto> productos) {
        this.fecha = fecha;
        this.productos = productos;
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
    }  //Metodo para agregar productos

    public Boolean quitar(String id)  //Metodo para quitra productos
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
    } //Metodo para mostrar los productos del carrito

    public void limpiarCarrito()
    {
        productos.clear();
    }  //Metodo para limpiar el carrito

    @Override
    public String toString() {
        return "Carrito{" +
                "fecha=" + fecha +
                ", productos = "+ '\n' + productos +
                '}';
    }
}
