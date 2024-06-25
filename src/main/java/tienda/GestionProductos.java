package tienda;

import Interfazes.Agregable;
import Interfazes.Quitable;
import enums.*;
import exepciones.*;
import org.example.Menu;
import org.json.JSONArray;
import org.json.JSONObject;
import usuarios.Perfil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class GestionProductos implements Agregable<Producto>, Quitable<Producto> {

    private TreeSet<Producto> productos;
    private Scanner scan;
    private String bermudasPath;
    private String pantalonesPath;
    private String ropaInteriorPath;
    private String buzosPath;
    private String camperasPath;
    private String remerasPath;

    private final int idLenght;
    private final String creaciondeID;



    public GestionProductos() {

        this.productos = new TreeSet<>();
        this.scan = new Scanner(System.in);
        this.creaciondeID = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";;
        this.idLenght = 5;
        this.bermudasPath = "./Bermudas.json";
        this.buzosPath = "./Buzos.json";
        this.camperasPath = "./Camperas.json";
        this.pantalonesPath = "./Pantalones.json";
        this.ropaInteriorPath = "./RopaInterior.json";
        this.remerasPath= "./Remeras.json";
    }

    public TreeSet<Producto> getProductos() {
        return productos;
    }

    //region Métodos para guardar y cargar productos en un Json

    public void cargandoDatos() {
        String pathBermudas = null;
        String pathBuzos = null;
        String pathCamperas = null;
        String pathPantalones = null;
        String pathRemeras = null;
        String pathRopaInterior = null;
        try {
            pathBermudas = new String(Files.readAllBytes(Paths.get(bermudasPath)));
            pathBuzos = new String(Files.readAllBytes(Paths.get(buzosPath)));
            pathCamperas = new String(Files.readAllBytes(Paths.get(camperasPath)));
            pathPantalones = new String(Files.readAllBytes(Paths.get(pantalonesPath)));
            pathRemeras = new String(Files.readAllBytes(Paths.get(remerasPath)));
            pathRopaInterior = new String(Files.readAllBytes(Paths.get(ropaInteriorPath)));
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
        agregar(bermuda);
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
        agregar(pantalon);
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
        agregar(ropaInterior);
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
        agregar(buzo);
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
        agregar(remera);
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
        agregar(campera);
    }

    public void guardarDatos() {
        JSONArray jsonArrayBermuda = new JSONArray();
        JSONArray jsonArrayBuzo = new JSONArray();
        JSONArray jsonArrayCampera = new JSONArray();
        JSONArray jsonArrayRemera = new JSONArray();
        JSONArray jsonArrayRopaInterior = new JSONArray();
        JSONArray jsonArrayPantalon = new JSONArray();

        for (Producto producto : productos) {
            if(producto instanceof Bermuda)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayBermuda.put(jsonObject);
            }
           if(producto instanceof Buzo)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayBuzo.put(jsonObject);
            }
             if(producto instanceof Campera)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayCampera.put(jsonObject);
            }
             if(producto instanceof Remera)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayRemera.put(jsonObject);
            }
             if(producto instanceof RopaInterior)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayRopaInterior.put(jsonObject);
            }
            if(producto instanceof Pantalon)
            {
                JSONObject jsonObject = producto.toJson();
                jsonArrayPantalon.put(jsonObject);
            }
        }
        try {
            String jsonFormattedBermuda = jsonArrayBermuda.toString(4);
            String jsonFormattedCampera = jsonArrayCampera.toString(4);
            String jsonFormattedBuzo = jsonArrayBuzo.toString(4);
            String jsonFormattedPantalon = jsonArrayPantalon.toString(4);
            String jsonFormattedRemera = jsonArrayRemera.toString(4);
            String jsonFormattedRopaInterior = jsonArrayRopaInterior.toString(4);
            Files.write(Paths.get(bermudasPath), jsonFormattedBermuda.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(camperasPath), jsonFormattedCampera.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(buzosPath), jsonFormattedBuzo.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(remerasPath), jsonFormattedRemera.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(pantalonesPath), jsonFormattedPantalon.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(ropaInteriorPath), jsonFormattedRopaInterior.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //endregion
//---------------------------------------------------------------------------------------------------------
    public void crearAgregarProducto() {

        //-------------------------------------------------------
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scan.nextLine();

        //-------------------------------------------------------

        String ID = generarIdReserva();

        //-------------------------------------------------------

        int stock = solicitarStock();

        //-------------------------------------------------------

        double precio = solicitarPrecio();

        //-------------------------------------------------------

        String categoria = solicitarCategoria();

        //-------------------------------------------------------

        if (categoria.equalsIgnoreCase("inferior")) {

            TallaLetra talle = TallaLetra.valueOf(solicitarTalleLetra().toUpperCase()); //


            String tipoInferior = solicitarTipoProductoInferior().toLowerCase(); //

            switch (tipoInferior) {
                case "pantalon":

                    TipoPantalon tipoPantalon = TipoPantalon.valueOf(solicitarTipoPantalon().toUpperCase());

                    if(agregar(new Pantalon(nombre, ID, stock, precio, talle, tipoPantalon)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "\n! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }
                    break;

                case "bermuda":

                    TipoBermuda tipoBermuda = TipoBermuda.valueOf(solicitarTipoBermuda().toUpperCase());

                    if(agregar(new Bermuda(nombre, ID, stock, precio, talle, tipoBermuda)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "\n! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }
                    break;

                case "ropa_interior":

                    TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(solicitarTipoRopaInterior().toUpperCase());

                    if(agregar(new RopaInterior(nombre, ID, stock, precio, talle, tipoRopaInterior)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "\n! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }
                    break;

                default:
                    System.out.println("Tipo de producto inferior no válido.");
                    break;
            }

        } else if (categoria.equalsIgnoreCase("superior")) {

            float talleNumerico = solicitarTalleNumerico();


            String tipoSuperior = solicitarTipoProductoSuperior().toLowerCase();

            switch (tipoSuperior) {
                case "remera":

                    TipoRemera tipoRemera = TipoRemera.valueOf(solicitarTipoRemera().toUpperCase());

                    if( agregar(new Remera(nombre, ID, stock, precio, talleNumerico, tipoRemera)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "\n! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }

                    break;
                case "campera":

                    TipoCampera tipoCampera = TipoCampera.valueOf(solicitarTipoCampera().toUpperCase());

                    if(agregar(new Campera(nombre, ID, stock, precio, talleNumerico, tipoCampera)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }
                    break;
                case "buzo":

                    boolean tieneCapucha = solicitarCapucha();

                    if(agregar(new Buzo(nombre, ID, stock, precio, talleNumerico, tieneCapucha)))
                    {
                        System.out.println(Menu.ANSI_GREEN + "\n! Producto agregado exitosamente !" + Menu.ANSI_RESET);
                    }else {
                        System.out.println("! el id del producto creado ya esta en el sistema !");
                    }
                    break;
                default:
                    System.out.println("Tipo de producto superior no válido.");
                    break;
            }
        } else {
            System.out.println("Categoría de producto no válida.");
        }

    }

    public int solicitarStock() {
        int stock = -1;
        Menu menu = new Menu();

        while (true) {
            System.out.print("Stock: ");
            if (scan.hasNextInt()) {

                stock = scan.nextInt();
                scan.nextLine(); // Limpiar Buffer

                if(stock > 0){
                    break;
                }
                else{

                    System.out.println(Menu.ANSI_RED + "El stock ingresado es inválido.Debe ser un stock mayor a 0. Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);

                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese un número entero válido.\n" + Menu.ANSI_RESET);
                scan.nextLine(); // Consumir la entrada inválida
            }
        }

        return stock;
    }

    public Double solicitarPrecio() {

        Double precio;
        Menu menu = new Menu();

        while (true) {
            System.out.print("Precio: ");
            if (scan.hasNextDouble()) {

                precio = scan.nextDouble();
                scan.nextLine(); // Limpiar Buffer

                if(precio > 0){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "El precio ingresado es inválido.Debe ser un precio mayor a 0. Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese un número entero válido.\n"+ Menu.ANSI_RESET);
                scan.nextLine(); // Consumir la entrada inválida
            }
        }

        return precio;
    }

    public String solicitarCategoria() {

        String categoria;
        Menu menu = new Menu();

        while (true) {

            System.out.print("Categoria(inferior/superior): ");

                if(scan.hasNext()){
                    categoria = scan.nextLine().trim();

                if(categoria.equalsIgnoreCase("inferior") || categoria.equalsIgnoreCase("superior")){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "La categoria ingresada es inválida.Debe ser |superior| o |inferior|. Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese una opcion valida.\n"+ Menu.ANSI_RESET);
            }
        }

        return categoria;
    }

    public String solicitarTalleLetra() {

        String talleLetra;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("XSMALL","SMALL","MEDIUM","LARGE", "XLARGE");

        while (true) {
            System.out.print("Talle(XSMALL, SMALL, MEDIUM, LARGE, XLARGE): ");

            if (scan.hasNext()){

                talleLetra = scan.nextLine();

                if(tiposValidos.contains(talleLetra.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "La talla ingresada es inválida.Debe ser (XSMALL, SMALL, MEDIUM, LARGE, XLARGE). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese una opcion valida.\n"+ Menu.ANSI_RESET);

            }
        }

        return talleLetra;
    }

    public String solicitarTipoProductoInferior() {

        String tipoProductoInferior;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("pantalon","ropa_interior","bermuda");

        while (true) {
            System.out.print("Talle(pantalon, ropa_interior, bermuda): ");

            if (scan.hasNext()){

                tipoProductoInferior = scan.nextLine();

                if(tiposValidos.contains(tipoProductoInferior.toLowerCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "El tipo de producto inferior ingresado es inválido.Debe ser (pantalon, ropa_interior, bermuda). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese una opcion valida.\n"+ Menu.ANSI_RESET);

            }
        }
        return tipoProductoInferior;
    }

    public String solicitarTipoPantalon() {

        String tipoPantalon;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("JOGGING","JEANS","CARGO","LEGGINS");

        while (true) {
            System.out.print("Tipo(JOGGING,JEANS,CARGO,LEGGINS): ");

            if (scan.hasNext()){

                tipoPantalon = scan.nextLine();

                if(tiposValidos.contains(tipoPantalon.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl tipo de pantalon ingresado es inválido.Debe ser (JOGGING,JEANS,CARGO,LEGGINS). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoPantalon;
    }

    public String solicitarTipoBermuda() {

        String tipoBermuda;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("AJUSTADA","PIERNA_ANCHA","A_MEDIDA","CASUAL", "DEPORTIVA");

        while (true) {
            System.out.print("Tipo(AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA): ");

            if (scan.hasNext()){

                tipoBermuda = scan.nextLine();

                if(tiposValidos.contains(tipoBermuda.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl tipo de bermuda ingresado es inválido.Debe ser (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoBermuda;
    }

    public String solicitarTipoRopaInterior() {

        String tipoRopaInterior;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("BOXER","SLIPS","TRUNKS","CALZONCILLOS_CONICOS");

        while (true) {
            System.out.print("Tipo(BOXER, SLIPS, TRUNKS, CALZONCILLOS_CONICOS): ");

            if (scan.hasNext()){

                tipoRopaInterior = scan.nextLine();

                if(tiposValidos.contains(tipoRopaInterior.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl tipo de ropa interior ingresado es inválido.Debe ser (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoRopaInterior;
    }

    public float solicitarTalleNumerico(){

        float talleNumerico;
        Menu menu = new Menu();

        while (true) {
            System.out.println("Ingrese el talle (número entre 30 y 50):  ");

            try {
                talleNumerico = menu.validateOption(scan.nextLine(),30,50 );
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return talleNumerico;
    }

    public String solicitarTipoProductoSuperior() {

        String tipoProductoSuperior;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("remera","campera","buzo");

        while (true) {
            System.out.print("Talle(remera, campera, buzo): ");

            if (scan.hasNext()){

                tipoProductoSuperior = scan.nextLine();

                if(tiposValidos.contains(tipoProductoSuperior.toLowerCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "El tipo de producto superior ingresado es inválido.Debe ser (pantalon, ropa_interior, bermuda). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "Entrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoProductoSuperior;
    }

    public String solicitarTipoRemera() {

        String tipoRemera;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("M_CORTA","M_LARGA","MUSCULOSA","DEPORTIVA");

        while (true) {
            System.out.print("Tipo(M_CORTA,M_LARGA,MUSCULOSA,DEPORTIVA): ");

            if (scan.hasNext()){

                tipoRemera = scan.nextLine();

                if(tiposValidos.contains(tipoRemera.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl tipo de ropa remera ingresado es inválido.Debe ser (M_CORTA,M_LARGA,MUSCULOSA,DEPORTIVA). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoRemera;
    }

    public String solicitarTipoCampera() {

        String tipoCampera;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("ROMPE_VIENTO","NIEVE","AISLANTE");

        while (true) {
            System.out.print("Tipo(ROMPE_VIENTO,NIEVE,AISLANTE): ");

            if (scan.hasNext()){

                tipoCampera = scan.nextLine();

                if(tiposValidos.contains(tipoCampera.toUpperCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl tipo de ropa remera ingresado es inválido.Debe ser (ROMPE_VIENTO,NIEVE,AISLANTE). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" + Menu.ANSI_RESET);

            }
        }
        return tipoCampera;
    }

    public Boolean solicitarCapucha() {

        String capucha;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("si","no");

        while (true) {
            System.out.print("Capucha(si/no): ");

            if (scan.hasNext()){

                capucha = scan.nextLine();

                if(tiposValidos.contains(capucha.toLowerCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "\nEl valor de capucha ingresado es inválido.Debe ser (si/no). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED + "\nEntrada inválida. Por favor, ingrese una opcion valida.\n" +Menu.ANSI_RESET);

            }
        }

        if(capucha.equalsIgnoreCase("si")){
            return true;
        }
        else{
            return false;
        }

    }

    public String solicitarTipoProductoGeneral() {

        String tipoProductoGeneral;
        Menu menu = new Menu();
        Set<String> tiposValidos = Set.of("pantalon","ropa_interior","bermuda","remera","campera","buzo");

        while (true) {
            System.out.print("Prenda(pantalon, ropa_interior, bermuda, remera, campera, buzo): ");

            if (scan.hasNext()){

                tipoProductoGeneral = scan.nextLine();

                if(tiposValidos.contains(tipoProductoGeneral.toLowerCase())){
                    break;
                }
                else{
                    System.out.println(Menu.ANSI_RED + "El tipo de producto  ingresado es inválido.Debe ser (pantalon, ropa_interior, bermuda, remera, campera, buzo). Por favor, intente nuevamente.\n" + Menu.ANSI_RESET);
                }
            } else {
                System.out.println(Menu.ANSI_RED +"Entrada inválida. Por favor, ingrese una opcion valida.\n"+ Menu.ANSI_RESET);

            }
        }
        return tipoProductoGeneral;
    }


    public String generarIdReserva(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String id = null;
        for (int i = 0; i < idLenght; i++)
        {
            int index = random.nextInt(creaciondeID.length());
            sb.append(creaciondeID.charAt(index));
        }
        id = sb.toString();
        return id;
    }//Metodo que crea un id de 5 caracteres de manera aleatoria

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void eliminarProductoPorId() {
        // Pedir al usuario que ingrese el ID del producto a eliminar
        System.out.print("Ingrese el ID del producto que desea eliminar: ");
        String id = scan.nextLine().trim();

        try {
            boolean encontrado = false;
            for (Producto producto : productos) {
                if (producto.getId().equals(id)) {
                    encontrado = quitar(producto);
                    System.out.println(Menu.ANSI_GREEN + "Producto eliminado correctamente." + Menu.ANSI_RESET);
                    break; // Termina el bucle una vez que se elimina el producto
                }
            }
            if (!encontrado) {
                throw new ProductoNoEncontradoException(Menu.ANSI_RED +"No se encontró ningún producto con el ID " + id + Menu.ANSI_RESET);
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage()); // Maneja la excepción si no se encuentra el producto
        }
    }         //Metodo para la eliminacion de productos por id



    //---------------------------------------------------------------------------------------------------------
    public void filtrarPorPrenda() throws ProductoNoEncontradoException{

        String tipo = solicitarTipoProductoGeneral();

        System.out.println("Productos encontrados: \n");
        productos.stream().filter(producto -> producto.getClass().getSimpleName().equalsIgnoreCase(tipo)).forEach(System.out::println);
    }    //Metodo de filtrado

    public void filtrarPorTipo(){
        String tipo = solicitarCategoria();

        try{
            if (tipo.equalsIgnoreCase("superior")){
                filtrarYImprimirPorClase(productos, ProductoSup.class);
            }else if (tipo.equalsIgnoreCase("inferior")){
                filtrarYImprimirPorClase(productos,ProductoInf.class);
            } else {
                throw new IllegalArgumentException(Menu.ANSI_RED + "Tipo inválido: "+ tipo + Menu.ANSI_RESET);
            }
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }     //Metodo de filtrado

    public static void filtrarYImprimirPorClase(Set<Producto> productos, Class<? extends Producto> claseDeseada) {
        productos.stream()
                .filter(claseDeseada::isInstance)
                .forEach(System.out::println);
    }   //Metodo de filtrado

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
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }   //Metodo para filtrar la lista de productos por filtro a eleccion

   //---------------------------------------------------------------------------------------------------------

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
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }  //Metodo para la modificacion de algun atributo de un producto

    public void modificarNombre() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = scan.nextLine();

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setNombre(nombre);// cambia el valor si el producto si se encuentra
                System.out.println(Menu.ANSI_GREEN + "\nModificacion realizada correctamente" + Menu.ANSI_RESET);
            }
        }

    } //Metodo de modificacion

    public void modificarStock() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo stock del producto: ");
        int stock = solicitarStock();

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setStock(stock);// cambia el valor si el producto si se encuentra
                System.out.println(Menu.ANSI_GREEN + "\nModificacion realizada correctamente" + Menu.ANSI_RESET);
            }
        }

    } //Metodo de modificacion

    public void modificarPrecio() throws ProductoNoEncontradoException {

        System.out.print("Ingrese el ID del producto buscado: ");
        String idBuscado = scan.nextLine();


        Producto productoBuscado = buscarProductoPorId(idBuscado);

        System.out.print("Ingrese el nuevo precio del producto: ");
        double precio = solicitarPrecio();
        scan.nextLine();//limpiar buffer

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                producto.setPrecio(precio);// cambia el valor si el producto si se encuentra
                System.out.println(Menu.ANSI_GREEN + "\nModificacion realizada correctamente" + Menu.ANSI_RESET);
            }
        }

    } //Metodo de modificacion
//---------------------------------------------------------------------------------------------------------

    public Producto buscarProductoPorId(String idBuscado) throws ProductoNoEncontradoException {

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                return producto; // Retorna el producto si se encuentra
            }
        }
        throw new ProductoNoEncontradoException("No se encontró ningún producto con el ID " + idBuscado);
    }  //Metodo de busqueda de productos por id

    public void listarProductos(){

        System.out.println("\n\n");
        for (Producto producto : productos) {

            System.out.println(producto);//HACER UNA BUENA PLANTILLA
        }
        System.out.println("\n\n");

    }  //Metodo para mostrar todos los productos

    @Override
    public Boolean agregar(Producto elemento) {   //Metodo para agregar productos
        return productos.add(elemento);
    } //Metodo para agregar productos

    @Override
    public Boolean quitar(Producto elemento) {
        return productos.remove(elemento);
    }    //Metodo para quitar productos

}

