package usuarios;

import Interfazes.Agregable;
import Interfazes.Quitable;
import enums.*;
import org.json.JSONArray;
import org.json.JSONObject;
import tienda.*;

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

    public TreeSet<Perfil> getUsuarios() {
        return usuarios;
    }

//region Métodos para guardar y cargar usuarios en un json

    public void cargandoDatos()
    {
        try {
            String pathPerfiles = new String(Files.readAllBytes(Paths.get("./Usuarios.json")));
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

//endregion
    public void setUsuarios(TreeSet<Perfil> usuarios) {
        this.usuarios = usuarios;
    }

    public Perfil crearUsuario()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("email: ");
        String email = scan.next();
        System.out.println("Nombre: ");
        String nombre = scan.next();
        System.out.println("Apellido: ");
        String apellido = scan.next();
        String nombreYapellido= nombre +" "+ apellido;
        Perfil perfil = new Perfil(nombreYapellido,email);
        System.out.println("Usuario admin?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int seleccion = scan.nextByte();
        scan.nextLine();//Limpiar Buffer
        if(seleccion==1)
        {
            perfil.chequearAdmin();
        }
        if(agregar(perfil))
        {
            try {
                agregarUsuarioAlArchivo(perfil,"./Usuarios.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return perfil;
        }
        return null;
    }

    public static void agregarUsuarioAlArchivo(Perfil perfil, String filePath) throws IOException {
        // Leer el archivo existente
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray jsonArray = new JSONArray(content);

        // Crear un JSONObject para el nuevo perfil
        JSONObject nuevoPerfilJson = new JSONObject();
        nuevoPerfilJson.put("nombre", perfil.getNombre());
        nuevoPerfilJson.put("email", perfil.getEmail());
        nuevoPerfilJson.put("admin", perfil.getAdmin());

        JSONObject carritoJson = new JSONObject();
        carritoJson.put("fecha", perfil.getCarrito().getFecha().toString());
        JSONArray productosJsonArray = new JSONArray();
        for (Producto producto : perfil.getCarrito().getProductos()) {
            JSONObject productoJson = new JSONObject();
            productoJson.put("nombre", producto.getNombre());
            productoJson.put("id", producto.getId());
            productoJson.put("stock", producto.getStock());
            productoJson.put("precio", producto.getPrecio());
            // Dependiendo del tipo de producto
            if (producto instanceof Campera) {
                Campera campera = (Campera) producto;
                productoJson.put("talla", campera.getTalla());
                productoJson.put("tipoCampera", campera.getTipoCampera());
            } else if (producto instanceof Pantalon) {
                Pantalon pantalon = (Pantalon) producto;
                productoJson.put("tallaLetra", pantalon.getTallaLetra());
                productoJson.put("tipoPantalon", pantalon.getTipoPantalon());
            }else if(producto instanceof Remera)
            {
                Remera remera = (Remera) producto;
                productoJson.put("talla", remera.getTalla());
                productoJson.put("tipoRemera", remera.getTipoRemera());
            } else if(producto instanceof Bermuda)
            {
                Bermuda bermuda = (Bermuda) producto;
                productoJson.put("tallaLetra", bermuda.getTallaLetra());
                productoJson.put("tipoBermuda", bermuda.getTipoBermuda());
            } else if(producto instanceof RopaInterior)
            {
                RopaInterior ropaInterior = (RopaInterior) producto;
                productoJson.put("tallaLetra", ropaInterior.getTallaLetra());
                productoJson.put("tipoRopaInterior", ropaInterior.getTipoRopaInterior());
            }else if(producto instanceof Buzo)
            {
                Buzo buzo = (Buzo) producto;
                productoJson.put("talla", buzo.getTalla());
                productoJson.put("capucha", buzo.isCapucha());
            }

            productosJsonArray.put(productoJson);
        }
        carritoJson.put("productos", productosJsonArray);
        nuevoPerfilJson.put("carrito", carritoJson);

        JSONArray historialJsonArray = new JSONArray();
        for (Carrito historialCarrito : perfil.getHistorial()) {
            JSONObject historialCarritoJson = new JSONObject();
            historialCarritoJson.put("fecha", historialCarrito.getFecha().toString());
            JSONArray historialProductosJsonArray = new JSONArray();
            for (Producto producto : historialCarrito.getProductos()) {
                JSONObject productoJson = new JSONObject();
                productoJson.put("nombre", producto.getNombre());
                productoJson.put("id", producto.getId());
                productoJson.put("stock", producto.getStock());
                productoJson.put("precio", producto.getPrecio());
                if (producto instanceof Campera) {
                    Campera campera = (Campera) producto;
                    productoJson.put("talla", campera.getTalla());
                    productoJson.put("tipoCampera", campera.getTipoCampera());
                } else if (producto instanceof Pantalon) {
                    Pantalon pantalon = (Pantalon) producto;
                    productoJson.put("tallaLetra", pantalon.getTallaLetra());
                    productoJson.put("tipoPantalon", pantalon.getTipoPantalon());
                }else if(producto instanceof Remera)
                {
                    Remera remera = (Remera) producto;
                    productoJson.put("talla", remera.getTalla());
                    productoJson.put("tipoRemera", remera.getTipoRemera());
                } else if(producto instanceof Bermuda)
                {
                    Bermuda bermuda = (Bermuda) producto;
                    productoJson.put("tallaLetra", bermuda.getTallaLetra());
                    productoJson.put("tipoBermuda", bermuda.getTipoBermuda());
                } else if(producto instanceof RopaInterior)
                {
                    RopaInterior ropaInterior = (RopaInterior) producto;
                    productoJson.put("tallaLetra", ropaInterior.getTallaLetra());
                    productoJson.put("tipoRopaInterior", ropaInterior.getTipoRopaInterior());
                }else if(producto instanceof Buzo)
                {
                    Buzo buzo = (Buzo) producto;
                    productoJson.put("talla", buzo.getTalla());
                    productoJson.put("capucha", buzo.isCapucha());
                }
                historialProductosJsonArray.put(productoJson);
            }
            historialCarritoJson.put("productos", historialProductosJsonArray);
            historialJsonArray.put(historialCarritoJson);
        }
        nuevoPerfilJson.put("historial", historialJsonArray);

        // Agregar el nuevo perfil al JSONArray
        jsonArray.put(nuevoPerfilJson);

        // Escribir el JSONArray actualizado de vuelta al archivo
        Files.write(Paths.get(filePath), jsonArray.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

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
                System.err.println("! Dato Ingesado no numerico !");
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
                    System.out.println("! Dato invalido !");
                    break;
            }
            if(perfil==null)
            {
                System.out.println("! Usuario invalido !");
            }
        }
        return perfil;
    }

    public Perfil buscarUsuario()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su email: ");
        String email = scan.next();
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
