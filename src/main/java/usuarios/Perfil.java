package usuarios;

import Factura.Factura;
import Factura.GenerarFacturaPNG;
import Interfazes.ToJson;
import org.json.JSONArray;
import org.json.JSONObject;
import tienda.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Perfil extends Usuario implements Comparable<Usuario>, ToJson {

    private Carrito carrito;
    private ArrayList<Carrito> historial;


    public Perfil(String nombre, String email) {
        super(nombre, email);
        this.carrito = new Carrito();
        this.historial = new ArrayList<>();

    }

    public Perfil(String nombre, String email, Boolean admin, Carrito carrito, ArrayList<Carrito> historial) {
        super(nombre, email);
        setAdmin(admin);
        setCarrito(carrito);
        setHistorial(historial);
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


    public Path generarFacturaPath() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fechaFormateada = carrito.getFecha().format(formatter);
        String facturaPath = "./" + getNombre() + "_" + fechaFormateada + ".png";
        return Paths.get(facturaPath);
    }


    @Override
    public String toString() {
        return super.toString() + "\n" +
                carrito + "\n" +
                "HISTORIAL\n" + historial;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombre", this.getNombre());
        jsonObject.put("email", this.getEmail());
        jsonObject.put("admin", this.getAdmin());

        JSONObject carritoJson = this.carrito.toJson();
        jsonObject.put("carrito", carritoJson);

        JSONArray historialJson = new JSONArray();
        for (Carrito c : historial) {
            historialJson.put(c.toJson());
        }
        jsonObject.put("historial", historialJson);

        return jsonObject;
    }

    //region Metodos JavaSwing

    public boolean quitarDelCarritoSwing(JScrollPane scrollPane) {
        DefaultListModel<Producto> listModel = new DefaultListModel<>();
        for (Producto producto : carrito.getProductos()) {
            listModel.addElement(producto);
        }
        JList<Producto> lista = new JList<>(listModel);
        // Aquí deberías tener una forma de mostrar la lista en tu interfaz gráfica
        // Puede ser un JScrollPane, un JPanel, etc.
        // Por simplicidad, asumiré que tienes un scrollPane declarado globalmente o disponible localmente
        scrollPane.setViewportView(lista); // Actualiza la lista visualmente en tu interfaz

        if (carrito.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        String idProducto = JOptionPane.showInputDialog(null, "Ingrese el ID del producto que quiere eliminar:");
        if (idProducto != null && !idProducto.isEmpty()) {
            boolean productoEliminado = carrito.quitar(idProducto);
            if (productoEliminado) {
                JOptionPane.showMessageDialog(null, "Producto eliminado del carrito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado en el carrito.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ID del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public void comprarProductoSwing() {
        if (carrito.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío. No se puede realizar la compra.", "Carrito Vacío", JOptionPane.WARNING_MESSAGE);
        } else {
            double total = 0.0;
            GenerarFacturaPNG generarFacturaPNG = new GenerarFacturaPNG();
            Factura factura = new Factura();

            // Calcular total y configurar la factura
            for (Producto producto : carrito.getProductos()) {
                total += producto.getPrecio().doubleValue();
                producto.setStock(producto.getStock()-1);
            }
            factura.setTotal(total);
            factura.setCliente(this.getNombre());
            factura.setProductos(this.carrito.getProductos());
            factura.setFecha(this.carrito.getFecha());

            // Guardar copia del carrito y limpiar carrito actual
            Carrito carritoCopia = new Carrito(carrito);
            historial.add(carritoCopia);

            // Preguntar al usuario si desea generar la factura
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea generar la factura?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                // Mostrar mensaje de compra realizada
                JOptionPane.showMessageDialog(null, "Compra realizada. Total a pagar: $" + String.format("%.2f", total), "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);

                // Generar factura como imagen PNG
                Path path = generarFacturaPath();
                try {
                    generarFacturaPNG.generarImagen(factura, path.toString());
                    JOptionPane.showMessageDialog(null, "Factura generada y guardada en: " + path.toString(), "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Factura Cancelada.", "Operación Cancelada", JOptionPane.INFORMATION_MESSAGE);
            }

            carrito.limpiarCarrito();
        }
    }

    public void mostrarHistorialSwing(JFrame frame) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (getHistorial().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "! Historial Vacío !", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Carrito carrito : getHistorial()) {
                StringBuilder sb = new StringBuilder();
                sb.append("<html><body>");

                sb.append("<b>Fecha: </b>").append(carrito.getFecha().format(formatter)).append("<br>");

                // Añadir detalles de cada producto en líneas separadas
                for (Producto producto : carrito.getProductos()) {
                    sb.append("| <b>ID: </b>").append(producto.getId()).append(" | ");
                    sb.append("<b>Nombre: </b>").append(producto.getNombre()).append(" | ");
                    sb.append("<b>Stock: </b>").append(producto.getStock()).append(" | ");
                    sb.append("<b>Precio: </b>").append(producto.getPrecio()).append(" | ");

                    // Añadir la talla dependiendo del tipo de producto
                    if (producto instanceof ProductoSup) {
                        ProductoSup productoSup = (ProductoSup) producto;
                        sb.append("<b>Talla: </b>").append(productoSup.getTalla()).append(" | ");
                    } else if (producto instanceof ProductoInf) {
                        ProductoInf productoInf = (ProductoInf) producto;
                        sb.append("<b>Talla Letra: </b>").append(productoInf.getTallaLetra()).append(" | ");
                    }

                    sb.append("<br>");
                }

                sb.append("</body></html>");

                // Agregar la cadena del carrito al modelo de lista
                listModel.addElement(sb.toString());

                // Agregar un separador entre carritos para mejor visualización
                listModel.addElement("----------------------------------------");
            }

            JList<String> lista = new JList<>(listModel);
            lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lista.setVisibleRowCount(-1);

            JScrollPane scrollPane = new JScrollPane(lista);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            scrollPane.setPreferredSize(new Dimension(800, 600));

            JOptionPane.showMessageDialog(frame, scrollPane, "Historial de Compras", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void agregarAlCarritoSwing(GestionProductos gestionProductos, JFrame frame) {
        if (getCarrito().getProductos().isEmpty()) {
            setCarrito(new Carrito());
        }

        for (int intento = 1; intento <= 3; intento++) {
            String nombreProducto = JOptionPane.showInputDialog(frame, "Ingrese el nombre del producto a agregar al carrito (Intento " + intento + " de 3):");

            if (nombreProducto == null || nombreProducto.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nombre del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            Producto producto = gestionProductos.getProductos().stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(nombreProducto))
                    .findFirst()
                    .orElse(null);

            if (producto != null) {
                if (producto.getStock() > 0) {
                    getCarrito().agregar(producto);
                    producto.setStock(producto.getStock() - 1);
                    JOptionPane.showMessageDialog(frame, "Producto agregado al carrito: " + producto.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return; // Salir del método si se encontró y añadió el producto
                } else {
                    JOptionPane.showMessageDialog(frame, "¡Producto Agotado!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    intento--; // No contar este intento
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(frame, "Ha alcanzado el número máximo de intentos. No se agregó ningún producto al carrito.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarCarritoSwing(JFrame frame, JScrollPane scrollPane)
    {
     carrito.mostrarCarrito(frame,scrollPane);
    }

    //endregion
}

