package tienda;

import enums.*;
import exepciones.*;
import org.example.Menu;

import java.util.*;

public class GestionProductos {

    private static List<Producto> carrito = new ArrayList<>();
    private TreeSet<Producto> productos;
    private Scanner scan;

    public GestionProductos() {

        this.productos = new TreeSet<>();
        this.scan = new Scanner(System.in);
    }

    public TreeSet<Producto> getProductos() {
        return productos;
    }


    public void crearAgregarProducto() {

        //-------------------------------------------------------
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scan.nextLine();

        //-------------------------------------------------------

        System.out.print("Ingrese el ID del producto: ");
        String ID = scan.nextLine();

        //-------------------------------------------------------

        System.out.print("Ingrese el stock del producto: ");
        int stock = scan.nextInt();
        scan.nextLine();//Limpiar buffer

        //-------------------------------------------------------

        System.out.print("Ingrese el precio del producto: ");
        double precio = scan.nextDouble();
        scan.nextLine(); // Consumir el salto de línea

        //-------------------------------------------------------

        System.out.print("¿El producto es inferior o superior? (inferior/superior): ");
        String categoria = scan.nextLine().toLowerCase();

        //-------------------------------------------------------

        if (categoria.equals("inferior")) {

            System.out.print("Ingrese el talle (XSMALL, SMALL, MEDIUM, LARGE, XLARGE): ");
            TallaLetra talle = TallaLetra.valueOf(scan.nextLine().toUpperCase());

            System.out.print("¿El producto es pantalón, bermuda o ropa interior? (pantalon/bermuda/ropa_interior): ");
            String tipoInferior = scan.nextLine().toLowerCase();

            switch (tipoInferior) {
                case "pantalon":
                    System.out.print("Ingrese el tipo de pantalón (JOGGING,JEANS,CARGO,LEGGINS): ");
                    TipoPantalon tipoPantalon = TipoPantalon.valueOf(scan.nextLine().toUpperCase());
                    productos.add(new Pantalon(nombre, ID, stock, precio, talle, tipoPantalon));
                    break;

                case "bermuda":
                    System.out.print("Ingrese el tipo de bermuda (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA): ");
                    TipoBermuda tipoBermuda = TipoBermuda.valueOf(scan.nextLine().toUpperCase());
                    productos.add(new Bermuda(nombre, ID, stock, precio, talle, tipoBermuda));
                    break;

                case "ropa_interior":
                    System.out.print("Ingrese el tipo de ropa interior (BOXER, SLIPS, TRUNKS, CALZONCILLOS_CONICOS): ");
                    TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(scan.nextLine().toUpperCase());
                    productos.add(new RopaInterior(nombre, ID, stock, precio, talle, tipoRopaInterior));
                    break;

                default:
                    System.out.println("Tipo de producto inferior no válido.");
                    break;
            }
        } else if (categoria.equals("superior")) {
            System.out.print("Ingrese el talle (número entre 30 y 50): ");
            float talleNumerico = scan.nextFloat();
            scan.nextLine(); // Consumir el salto de línea

            System.out.print("¿El producto es remera, campera o buzo? (remera/campera/buzo): ");
            String tipoSuperior = scan.nextLine().toLowerCase();

            switch (tipoSuperior) {
                case "remera":
                    System.out.print("Ingrese el tipo de remera (M_CORTA,M_LARGA,MUSCULOSA,DEPORTIVA): ");
                    TipoRemera tipoRemera = TipoRemera.valueOf(scan.nextLine().toUpperCase());
                    productos.add(new Remera(nombre, ID, stock, precio, talleNumerico, tipoRemera));
                    break;
                case "campera":
                    System.out.print("Ingrese el tipo de campera (ROMPE_VIENTO,NIEVE,AISLANTE): ");
                    TipoCampera tipoCampera = TipoCampera.valueOf(scan.nextLine().toUpperCase());
                    productos.add(new Campera(nombre, ID, stock, precio, talleNumerico, tipoCampera));
                    break;
                case "buzo":
                    System.out.print("¿Tiene capucha? (true/false): ");
                    boolean tieneCapucha = scan.nextBoolean();
                    productos.add(new Buzo(nombre, ID, stock, precio, talleNumerico, true));
                    break;
                default:
                    System.out.println("Tipo de producto superior no válido.");
                    break;
            }
        } else {
            System.out.println("Categoría de producto no válida.");
        }

    }

    public void eliminarProductoPorId() {
        // Pedir al usuario que ingrese el ID del producto a eliminar
        System.out.print("Ingrese el ID del producto que desea eliminar: ");
        String id = scan.nextLine().trim();

        try {
            boolean encontrado = false;
            for (Producto producto : productos) {
                if (producto.getId().equals(id)) {
                    productos.remove(producto);
                    encontrado = true;
                    System.out.println("Producto eliminado correctamente.");
                    break; // Termina el bucle una vez que se elimina el producto
                }
            }
            if (!encontrado) {
                throw new ProductoNoEncontradoException("No se encontró ningún producto con el ID " + id);
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage()); // Maneja la excepción si no se encuentra el producto
        }
    }

    public void filtrarPorPrenda() throws ProductoNoEncontradoException{
        System.out.println("Ingrese el tipo de prenda: ");
        String tipo = scan.nextLine().trim();

        validarTipoPrenda(tipo);
        System.out.println("Productos encontrados: \n");
        productos.stream().filter(producto -> producto.getClass().getSimpleName().equalsIgnoreCase(tipo)).forEach(System.out::println);
    }

    public void filtrarPorTipo(){
        System.out.println("Ingrese el tipo de prenda (sup/inf): ");
        String tipo = scan.nextLine();

        try{
            if (tipo.equalsIgnoreCase("sup")){
                filtrarYImprimirPorClase(productos, ProductoSup.class);
            }else if (tipo.equalsIgnoreCase("inf")){
                filtrarYImprimirPorClase(productos,ProductoInf.class);
            } else {
                throw new IllegalArgumentException("Tipo inválido: "+ tipo);
            }
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public static void filtrarYImprimirPorClase(Set<Producto> productos, Class<? extends Producto> claseDeseada) {
        productos.stream()
                .filter(claseDeseada::isInstance)
                .forEach(System.out::println);
    }

    public static void validarTipoPrenda(String tipoDeseado) throws ProductoNoEncontradoException {
        Set<String> tiposValidos = Set.of("Bermuda","Buzo","Campera","Pantalon", "Remera", "RopaInterior");
        if (!tiposValidos.contains(tipoDeseado)) {
            throw new ProductoNoEncontradoException("Tipo de producto inválido: " + tipoDeseado);
        }
    }

    public void modificarProducto () throws ProductoNoEncontradoException {

        Menu menu = new Menu();

        int seleccion = 0;


        while(seleccion != -1){

            seleccion = menu.menuVisualModifProducto();

        switch (seleccion){

            case 1:
            modificarNombre();
                break;
            //------------------------------------------------
            case 2:
            modificarStock();
                break;
            //------------------------------------------------
            case 3:
            modificarPrecio();
                break;
            //------------------------------------------------
            case 0:
            seleccion = -1;
                break;
            //------------------------------------------------
            default:

                break;
        }
        }
    }
    public void filtrarProductos() throws ProductoNoEncontradoException {
        Menu menu = new Menu();
        int seleccion = 0;

        while (seleccion != -1){
            seleccion = menu.menuVisualFiltrado();

            switch (seleccion){

                case 1:
                    filtrarPorTipo();
                    break;
                case 2:
                    filtrarPorPrenda();
                    break;
                case 0:
                    seleccion = -1;
                    break;
                default:
                    break;
            }
        }
    }

    public void agregarAlCarrito(){
        for (int intento = 1; intento <= 3; intento++) {
            System.out.print("Ingrese el nombre del producto a agregar al carrito (Intento " + intento + " de 3): ");
        String nombreProducto = scan.nextLine();
        Producto producto = productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombreProducto)).findFirst().orElse(null);
        if (producto != null) {
            carrito.add(producto);
            System.out.println("Producto agregado al carrito: " + producto);
            return; // Salir del método si se encontró y añadió el producto
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
        System.out.println("Ha alcanzado el número máximo de intentos. No se agregó ningún producto al carrito.");
}


    public static void mostrarCarrito() {
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Carrito de compras:");
            carrito.forEach(System.out::println);
        }
    }

    public static void realizarCompra() {
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío. No se puede realizar la compra.");
        } else {
            double total = 0.0;
            for (Producto producto : carrito) {
                total += producto.getPrecio().doubleValue(); // Convertir a double para sumar
            }
            System.out.println("Compra realizada. Total a pagar: $" + total);
            carrito.clear();
        }
    }

    public void modificarNombre() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = scan.nextLine();

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setNombre(nombre);// cambia el valor si el producto si se encuentra
            }
        }

    }

    public void modificarStock() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo stock del producto: ");
        int stock = scan.nextInt();scan.nextLine();//Limpiar buffer

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setStock(stock);// cambia el valor si el producto si se encuentra
            }
        }

    }

    public void modificarPrecio() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo precio del producto: ");
        double precio = scan.nextDouble();
        scan.nextLine();//limpiar buffer

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setPrecio(precio);// cambia el valor si el producto si se encuentra
            }
        }

    }


    public Producto buscarProductoPorId(String idBuscado) throws ProductoNoEncontradoException {

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                return producto; // Retorna el producto si se encuentra
            }
        }
        throw new ProductoNoEncontradoException("No se encontró ningún producto con el ID " + idBuscado);
    }

    public void listarProductos(){

        System.out.println("\n\n");
        for (Producto producto : productos) {

            System.out.println(producto);//HACER UNA BUENA PLANTILLA
        }
        System.out.println("\n\n");

    }


}
