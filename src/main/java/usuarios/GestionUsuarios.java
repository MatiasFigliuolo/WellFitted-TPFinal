package usuarios;

import Interfazes.Agregable;
import Interfazes.Quitable;
import enums.*;
import exepciones.InvalidOptionException;
import org.json.JSONArray;
import org.json.JSONObject;
import tienda.*;
import org.example.Menu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class GestionUsuarios implements Agregable<Perfil>, Quitable<Perfil> {

    private TreeSet<Perfil> usuarios = new TreeSet<>();
    private static String usuariosPath = "./Usuarios.json";

    public TreeSet<Perfil> getUsuarios() {
        return usuarios;
    }


//region Métodos para guardar y cargar usuarios en un json

    public void cargandoDatos()
    {
        try {
            String pathPerfiles = new String(Files.readAllBytes(Paths.get(usuariosPath)));
            JSONArray jsonArray = new JSONArray(pathPerfiles);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                cargarDatosJsonAPerfil(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void cargarDatosJsonAPerfil(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String email = jsonObject.getString("email");
        Boolean admin = jsonObject.getBoolean("admin");

        JSONObject carritoJson = jsonObject.getJSONObject("carrito");
        Carrito carrito = cargarDatosJsonACarrito(carritoJson);

        JSONArray historialJsonArray = jsonObject.getJSONArray("historial");
        ArrayList<Carrito> historial = new ArrayList<>();
        for (int i = 0; i < historialJsonArray.length(); i++) {
            JSONObject historialJsonObject = historialJsonArray.getJSONObject(i);
            Carrito historialCarrito = cargarDatosJsonACarrito(historialJsonObject);
            historial.add(historialCarrito);
        }

        Perfil perfil = new Perfil(nombre, email, admin, carrito, historial);
        usuarios.add(perfil);
    }

    public static Carrito cargarDatosJsonACarrito(JSONObject jsonObject) {
        LocalDateTime fecha = LocalDateTime.parse(jsonObject.getString("fecha"));
        JSONArray productosJsonArray = jsonObject.getJSONArray("productos");
        ArrayList<Producto> productos = new ArrayList<>();

        for (int i = 0; i < productosJsonArray.length(); i++) {
            JSONObject productoJson = productosJsonArray.getJSONObject(i);
            Producto producto = cargarDatosJsonAProducto(productoJson);
            productos.add(producto);
        }

        return new Carrito(fecha, productos);
    }

    public static Producto cargarDatosJsonAProducto(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        double precio = jsonObject.getDouble("precio");

        if (jsonObject.has("talla")) {    //Compara entre Ropa superior o inferior
            float talla = jsonObject.getFloat("talla");
            if(jsonObject.has("tipoCampera"))  //Compara entre los 3 tipos de ropa superior
            {
                TipoCampera tipoCampera = TipoCampera.valueOf(jsonObject.getString("tipoCampera"));
                return new Campera(nombre, id, stock, precio, talla, tipoCampera);
            } else if (jsonObject.has("tipoRemera"))
            {
                TipoRemera tipoRemera = TipoRemera.valueOf(jsonObject.getString("tipoRemera"));
                return new Remera(nombre, id, stock, precio, talla, tipoRemera);
            } else if (jsonObject.has("capucha"))
            {
                Boolean capucha = jsonObject.getBoolean("capucha");
                return new Buzo(nombre, id, stock, precio, talla, capucha);
            }
        } else if (jsonObject.has("tallaLetra")) {
            TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
            if(jsonObject.has("tipoPantalon"))  //Compara entre los 3 tipos de ropa inferior
            {
                TipoPantalon tipoPantalon = TipoPantalon.valueOf(jsonObject.getString("tipoPantalon"));
                return new Pantalon(nombre, id, stock, precio, tallaLetra, tipoPantalon);
            } else if (jsonObject.has("tipoBermuda"))
            {
                TipoBermuda tipoBermuda = TipoBermuda.valueOf(jsonObject.getString("tipoBermuda"));
                return new Bermuda(nombre, id, stock, precio, tallaLetra, tipoBermuda);
            } else if (jsonObject.has("tipoRopaInterior"))
            {
                TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(jsonObject.getString("tipoRopaInterior"));
                return new RopaInterior(nombre, id, stock, precio, tallaLetra, tipoRopaInterior);
            }
        }  //Compara entre Ropa superior o inferior

        throw new IllegalArgumentException("Tipo de producto desconocido: "+jsonObject);
    }

    public void guardarDatos() {
        JSONArray jsonArray = new JSONArray();

        for (Perfil perfil : usuarios) {
            JSONObject jsonObject = perfil.toJson();
            jsonArray.put(jsonObject);
        }

        try {
            String jsonFormatted = jsonArray.toString(4);
            Files.write(Paths.get(usuariosPath), jsonFormatted.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//endregion

    public void setUsuarios(TreeSet<Perfil> usuarios) {
        this.usuarios = usuarios;
    }

    public Perfil crearUsuario(){

        System.out.println(Menu.ANSI_GREEN + "\n-NUEVO USUARIO-" + Menu.ANSI_RESET);
        String email = solicitarCorreo();

        String nombre = solicitarNombre();

        String apellido = solicitarApellido();

        String nombreYapellido= nombre +" "+ apellido;

        Perfil perfil = new Perfil(nombreYapellido,email);

        int seleccion = solicitarAdmin();
        if(seleccion==1)
        {
            perfil.chequearAdmin();
        }

        if(agregar(perfil))
        {
            return perfil;
        }
         return null;
    }

    public String solicitarNombre() {
        String nombre;
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        while (true) {
            System.out.print("Nombre: ");
            nombre = scan.nextLine().trim();

            if (menu.esNombreValido(nombre)) {
                break;
            } else {
                System.out.println(Menu.ANSI_RED + "El nombre ingresado es inválido. No debe contener números. Por favor, intente nuevamente." + Menu.ANSI_RESET);
            }
        }

        return nombre;
    }

    public String solicitarApellido() {
        String apellido;
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        while (true) {
            System.out.print("Apellido: ");
            apellido = scan.nextLine().trim();

            if (menu.esNombreValido(apellido)) {
                break;
            } else {
                System.out.println(Menu.ANSI_RED +"El apellido ingresado es inválido. No debe contener números. Por favor, intente nuevamente." + Menu.ANSI_RESET);
            }
        }
        return apellido;
    }

    public String solicitarCorreo() {
        String email;
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        while (true) {
            System.out.print("Email: ");
            email = scan.nextLine().trim();

            if (menu.esCorreoValido(email)) {
                break;
            } else {
                System.out.println(Menu.ANSI_RED +"El email ingresado es inválido. debe terminar en [@gmail.com] y debe ser mayor a 10 caracteres. Por favor, intente nuevamente." + Menu.ANSI_RESET);
            }
        }

        return email;
    }

    public int solicitarAdmin() {

        int seleccion = 0;
        Menu menu = new Menu();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println(
                             "\nUsuario Administrador?" +
                            "\n1 Si" +
                            "\n2 No" +
                            "\nInsertar Opcion: ");

            try {
                seleccion = menu.validateOption(scan.nextLine(),1,2);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return seleccion;
    }


    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------


    public Perfil inicioSesion()
    {
        Scanner scan = new Scanner(System.in);
        Perfil perfil = null;
        while (perfil == null)
        {
            int seleccion=0;
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Crear nuevo usuario");

            try {
                seleccion = scan.nextInt();
            }catch (InputMismatchException e)
            {
                System.err.println(Menu.ANSI_RED + "! Dato Ingesado no numerico !" + Menu.ANSI_RESET);
                break;
            }

            switch (seleccion) {
                case 1:
                    perfil = buscarUsuario();
                    break;
                case 2:
                    perfil = crearUsuario();
                    break;
                default:
                    System.out.println(Menu.ANSI_RED +"! Dato invalido !"+ Menu.ANSI_RESET);
                    break;
            }
            if(perfil==null)
            {
                System.out.println(Menu.ANSI_RED + "! Usuario invalido !" + Menu.ANSI_RESET);
            }
        }
        return perfil;
    }

    public Perfil buscarUsuario()
    {

        String email = solicitarCorreo();

        for(Perfil perfil : usuarios)
        {
            if(perfil.getEmail().equals(email))
            {
                return perfil;
            }
        }
        return null;
    }

    public Boolean agregar(Perfil usuario)
    {
       return usuarios.add(usuario);
    }

    public Boolean quitar(Perfil usuario)
    {
        return usuarios.remove(usuario);
    }

    public void mostrarUsuarios()
    {
        System.out.println(usuarios.toString());
    }

    @Override
    public String toString() {
        return "GestionUsuarios{" +
                "usuarios=" + usuarios +
                '}';
    }
}
