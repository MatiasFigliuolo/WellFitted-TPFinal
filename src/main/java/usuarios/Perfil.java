package usuarios;

import Factura.Factura;
import Factura.GenerarFacturaPNG;
import Interfazes.ToJson;
import org.example.Menu;
import org.json.JSONArray;
import org.json.JSONObject;
import tienda.Carrito;
import tienda.GestionProductos;
import tienda.Producto;

import java.io.IOException;
import java.io.Serializable;
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

    public void agregarAlCarrito(GestionProductos gestionProductos){
        Scanner scan = new Scanner(System.in);
        if(carrito.getProductos().isEmpty())
        {
            carrito = new Carrito();
        }
        for (int intento = 1; intento <= 3; intento++) {
            System.out.print("Ingrese el nombre del producto a agregar al carrito (Intento " + intento + " de 3): ");
            String nombreProducto = scan.nextLine();
            Producto producto = gestionProductos.getProductos().stream().filter(p -> p.getNombre().equalsIgnoreCase(nombreProducto)).findFirst().orElse(null);
            if (producto != null) {
                if(producto.getStock()>0)
                {
                    carrito.agregar(producto);
                    producto.setStock(producto.getStock() - 1);
                    System.out.println(Menu.ANSI_GREEN + "Producto agregado al carrito: " + producto + Menu.ANSI_RESET);
                    return; // Salir del método si se encontró y añadió el producto
                } else
                {
                    System.out.println(Menu.ANSI_YELLOW + "! Producto Agotado !" + Menu.ANSI_RESET);
                    intento = intento-1;
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        }
        System.out.println(Menu.ANSI_YELLOW + "Ha alcanzado el número máximo de intentos. No se agregó ningún producto al carrito." + Menu.ANSI_RESET);
    }

    public void mostrandoCarrito()
    {
        carrito.mostrarCarrito();
    }

    public void mostrarHistorial()
    {
        System.out.println(historial);
    }

    public  void realizarCompra() {

        if (carrito.getProductos().isEmpty()) {
            System.out.println(Menu.ANSI_YELLOW +"El carrito está vacío. No se puede realizar la compra." + Menu.ANSI_RESET);
        } else {
            Factura factura = new Factura();
            double total = 0.0;
            GenerarFacturaPNG generarFacturaPNG = new GenerarFacturaPNG();
            String facturaPath;
            for (Producto producto : carrito.getProductos()) {
                total += producto.getPrecio().doubleValue(); // Convertir a double para sumar
            }

            System.out.println(Menu.ANSI_GREEN +"Compra realizada. Total a pagar: $" + total+ Menu.ANSI_RESET);
            Carrito carritoCopia = new Carrito(carrito);
            factura.setTotal(total);
            factura.setCliente(this.getNombre());
            factura.setProductos(this.carrito.getProductos());
            factura.setFecha(this.carrito.getFecha());
            historial.add(carritoCopia);
            Path path = generarFacturaPath();
            try {
                generarFacturaPNG.generarImagen(factura,path.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            carrito.limpiarCarrito();
        }
    }

    public Path generarFacturaPath() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fechaFormateada = carrito.getFecha().format(formatter);
        String facturaPath = "./" + getNombre() + "_" + fechaFormateada + ".png";
        return Paths.get(facturaPath);
    }

    public void quitarDelCarrito() {
        Scanner scan = new Scanner(System.in);
        mostrandoCarrito();
        if(!carrito.getProductos().isEmpty())
        {
            System.out.println("Ingrese el id del producto que quiere elminar: ");
            String id = scan.next();
            if(!carrito.quitar(id))
            {
                System.out.println(Menu.ANSI_RED + "! Producto no encontrado !"+ Menu.ANSI_RESET);
            }
        }
    }



    @Override
    public String toString() {
        return super.toString() +"\n" +
                 carrito+ "\n" +
                 "Hisotrial { "+ historial+ " }"+"\n";
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
}
