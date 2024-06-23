package tienda;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public Boolean agregar(Producto producto)
    {
       return productos.add(producto);
    }

    public void quitar(String id)
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
        }
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "fecha=" + fecha +
                ", productos= " + productos +
                '}';
    }
}
