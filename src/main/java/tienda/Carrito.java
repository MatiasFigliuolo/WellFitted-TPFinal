package tienda;

import Interfazes.Agregable;
import Interfazes.Quitable;
import Interfazes.ToJson;
import org.example.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Carrito implements Agregable<Producto>, Quitable<String>, ToJson {


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
    public Carrito(Carrito otroCarrito) {
        this.fecha = otroCarrito.fecha;  // LocalDateTime es inmutable, así que esto está bien
        this.productos = new ArrayList<>(otroCarrito.productos);
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Boolean agregar(Producto producto)
    {
       return productos.add(producto);
    }  //Metodo para agregar productos

    public Boolean quitar(String id)  //Metodo para quitar productos
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
            System.out.println(Menu.ANSI_GREEN + "! Producto eliminado con exito !" + Menu.ANSI_RESET);
            return true;
        }
        return false;
    }

    public  void mostrarCarrito(JFrame frame,JScrollPane scrollPane) {
        DefaultListModel<Producto> listModel = new DefaultListModel<>();
        if (getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "! Carrito Vacio !", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else
        {
            for (Producto producto : getProductos()) {
                listModel.addElement(producto);
            }
            JList<Producto> lista = new JList<>(listModel);
            scrollPane.setViewportView(lista);
            frame.revalidate();
            frame.repaint();
        }
    } //Metodo para mostrar los productos del carrito

    public void limpiarCarrito()
    {
        productos.clear();
    }  //Metodo para limpiar el carrito

    @Override
    public String toString() {
        return String.format(
                        "\nCARRITO\n" +
                        "|Fecha: "    + this.fecha +
                        "|\nProductos: " + this.productos);

    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fecha", this.fecha.toString());

        JSONArray productosJson = new JSONArray();
        for (Producto p : productos) {
            productosJson.put(p.toJson());
        }
        jsonObject.put("productos", productosJson);

        return jsonObject;
    }
}
