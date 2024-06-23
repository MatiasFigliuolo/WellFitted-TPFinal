package usuarios;

import tienda.Carrito;
import tienda.GestionProductos;
import tienda.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Perfil extends Usuario implements Comparable<Usuario> {

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
        for (int intento = 1; intento <= 3; intento++) {
            System.out.print("Ingrese el nombre del producto a agregar al carrito (Intento " + intento + " de 3): ");
            String nombreProducto = scan.nextLine();
            Producto producto = gestionProductos.getProductos().stream().filter(p -> p.getNombre().equalsIgnoreCase(nombreProducto)).findFirst().orElse(null);
            if (producto != null) {
                if(producto.getStock()>0)
                {
                    carrito.agregar(producto);
                    producto.setStock(producto.getStock() - 1);
                    System.out.println("Producto agregado al carrito: " + producto);
                    return; // Salir del método si se encontró y añadió el producto
                } else
                {
                    System.out.println("! Producto Agotado !");
                    intento = intento-1;
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        }
        System.out.println("Ha alcanzado el número máximo de intentos. No se agregó ningún producto al carrito.");
    }

    public void mostrandoCarrito()
    {
        carrito.mostrarCarrito();
    }

    public  void realizarCompra() {
        if (carrito.getProductos().isEmpty()) {
            System.out.println("El carrito está vacío. No se puede realizar la compra.");
        } else {
            double total = 0.0;
            for (Producto producto : carrito.getProductos()) {
                total += producto.getPrecio().doubleValue(); // Convertir a double para sumar
            }
            System.out.println("Compra realizada. Total a pagar: $" + total);
            carrito.limpiarCarrito();
        }
    }

    public void quitarDelCarrito()
    {
        Scanner scan = new Scanner(System.in);
        mostrandoCarrito();
        if(!carrito.getProductos().isEmpty())
        {
            System.out.println("Ingrese el id del producto que quiere elminar: ");
            String id = scan.next();
            if(!carrito.quitar(id))
            {
                System.out.println("! Producto no encontrado !");
            }
        }
    }



    @Override
    public String toString() {
        return super.toString() +"\n" +
                 carrito+ "\n" +
                 "Hisotrial { "+ historial+ " }";
    }
}
