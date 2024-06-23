package tienda;

import Interfazes.Agregable;
import enums.*;
import exepciones.*;
import org.example.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GestionProductos implements Serializable, Agregable<Producto> {

    private TreeSet<Producto> productos;
    private Scanner scan;

    public GestionProductos() {

        this.productos = new TreeSet<>();
        this.scan = new Scanner(System.in);
    }

    public TreeSet<Producto> getProductos() {
        return productos;
    }

    //region Métodos para guardar y cargar productos en un archivo

    public void cargandoDatos() {
        String pathBermudas = null;
        String pathBuzos = null;
        String pathCamperas = null;
        String pathPantalones = null;
        String pathRemeras = null;
        String pathRopaInterior = null;
        try {
            pathBermudas = new String(Files.readAllBytes(Paths.get("./Bermudas.json")));
            pathBuzos = new String(Files.readAllBytes(Paths.get("./Buzos.json")));
            pathCamperas = new String(Files.readAllBytes(Paths.get("./Camperas.json")));
            pathPantalones = new String(Files.readAllBytes(Paths.get("./Pantalones.json")));
            pathRemeras = new String(Files.readAllBytes(Paths.get("./Remeras.json")));
            pathRopaInterior = new String(Files.readAllBytes(Paths.get("./RopaInterior.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONArray jsonArray = new JSONArray(pathBermudas);
            cargarBermudas(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(pathPantalones);
            cargarPantalon(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(pathRopaInterior);
            cargarRopaInterior(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(pathBuzos);
            cargarBuzos(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(pathRemeras);
            cargarRemeras(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(pathCamperas);
            cargarCamperas(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarBermudas(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonABermudas(jsonObject);
        }
    }
    public void cargarDatosJsonABermudas(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoBermuda tipoBermuda = TipoBermuda.valueOf(jsonObject.getString("tipoBermuda"));
        Bermuda bermuda = new Bermuda(nombre,id,stock,precio,tallaLetra,tipoBermuda);
        productos.add(bermuda);
    }

    public void cargarPantalon(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonAPantalon(jsonObject);
        }
    }
    public void cargarDatosJsonAPantalon(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoPantalon tipoPantalon = TipoPantalon.valueOf(jsonObject.getString("tipoPantalon"));
        Pantalon pantalon = new Pantalon(nombre,id,stock,precio,tallaLetra,tipoPantalon);
        productos.add(pantalon);
    }
   public void cargarRopaInterior(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonARopaInterior(jsonObject);
        }
    }
    public void cargarDatosJsonARopaInterior(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(jsonObject.getString("tipoRopaInterior"));
        RopaInterior ropaInterior = new RopaInterior(nombre,id,stock,precio,tallaLetra,tipoRopaInterior);
        productos.add(ropaInterior);
    }
  public void cargarBuzos(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonABuzos(jsonObject);
        }
    }
    public void cargarDatosJsonABuzos(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla= jsonObject.getFloat("talla");
        Boolean capucha = jsonObject.getBoolean("capucha");
        Buzo buzo = new Buzo(nombre,id,stock,precio,talla,capucha);
        productos.add(buzo);
    }
  public void cargarRemeras(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonARemeras(jsonObject);
        }
    }
    public void cargarDatosJsonARemeras(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla= jsonObject.getFloat("talla");
        TipoRemera tipoRemera = TipoRemera.valueOf(jsonObject.getString("tipoRemera"));
        Remera remera = new Remera(nombre,id,stock,precio,talla,tipoRemera);
        productos.add(remera);
    }
 public void cargarCamperas(JSONArray jsonArray)
    {
        JSONObject jsonObject = new JSONObject();
        for(int i=0 ; i<jsonArray.length();i++)
        {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonACamperas(jsonObject);
        }
    }
    public void cargarDatosJsonACamperas(JSONObject jsonObject)
    {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla= jsonObject.getFloat("talla");
        TipoCampera tipoCampera = TipoCampera.valueOf(jsonObject.getString("tipoCampera"));
        Campera campera = new Campera(nombre,id,stock,precio,talla,tipoCampera);
        productos.add(campera);
    }

    //endregion

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
                    agregar(new Pantalon(nombre, ID, stock, precio, talle, tipoPantalon));
                    break;

                case "bermuda":
                    System.out.print("Ingrese el tipo de bermuda (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA): ");
                    TipoBermuda tipoBermuda = TipoBermuda.valueOf(scan.nextLine().toUpperCase());
                    agregar(new Bermuda(nombre, ID, stock, precio, talle, tipoBermuda));
                    break;

                case "ropa_interior":
                    System.out.print("Ingrese el tipo de ropa interior (BOXER, SLIPS, TRUNKS, CALZONCILLOS_CONICOS): ");
                    TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(scan.nextLine().toUpperCase());
                    agregar(new RopaInterior(nombre, ID, stock, precio, talle, tipoRopaInterior));
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
                    agregar(new Remera(nombre, ID, stock, precio, talleNumerico, tipoRemera));
                    break;
                case "campera":
                    System.out.print("Ingrese el tipo de campera (ROMPE_VIENTO,NIEVE,AISLANTE): ");
                    TipoCampera tipoCampera = TipoCampera.valueOf(scan.nextLine().toUpperCase());
                    agregar(new Campera(nombre, ID, stock, precio, talleNumerico, tipoCampera));
                    break;
                case "buzo":
                    System.out.print("¿Tiene capucha? (true/false): ");
                    boolean tieneCapucha = scan.nextBoolean();
                    agregar(new Buzo(nombre, ID, stock, precio, talleNumerico, true));
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

    @Override
    public Boolean agregar(Producto elemento) {
        return productos.add(elemento);
    }
}

