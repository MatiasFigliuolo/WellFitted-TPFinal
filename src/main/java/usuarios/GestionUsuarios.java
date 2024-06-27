package usuarios;

import Interfazes.Agregable;
import Interfazes.Quitable;
import enums.*;
import exepciones.InvalidOptionException;
import org.json.JSONArray;
import org.json.JSONObject;
import tienda.*;

import org.example.Menu;

import javax.swing.*;
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

    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------

    public Perfil buscarUsuario(String usuarioEmail)
    {


        for(Perfil perfil : usuarios)
        {
            if(perfil.getEmail().equals(usuarioEmail))
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


    @Override
    public String toString() {
        return "GestionUsuarios{" +
                "usuarios=" + usuarios +
                '}';
    }

    //region Metodos JavaSwing

    public Perfil crearUsuarioSwing() {
        JOptionPane.showMessageDialog(null, "Creación de Nuevo Usuario", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

        String email = solicitarCorreoSwing();
        if (email == null) {
            return null;
        }
        String nombre = solicitarNombreSwing();
        String apellido = solicitarApellidoSwing();

        String nombreCompleto = nombre + " " + apellido;

        Perfil perfil = new Perfil(nombreCompleto, email);

        int seleccion = solicitarAdminSwing();
        if (seleccion == 1) {
            perfil.chequearAdminSwing();
        }

        if (agregar(perfil)) {
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.", "Creación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            return perfil;
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario. Inténtelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String solicitarNombreSwing() {
        String nombre = null;
        Menu menu = new Menu();
        while (true) {
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre:", "Nombre", JOptionPane.PLAIN_MESSAGE);

            if (nombre != null && !nombre.isEmpty() && menu.esNombreValido(nombre)) {
                break; // Salir del bucle si el nombre es válido y no se cancela
            } else {
                JOptionPane.showMessageDialog(null, "El nombre ingresado es inválido o está vacío. Por favor, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return nombre;
    }

    public String solicitarApellidoSwing() {
        String apellido = null;
        Menu menu = new Menu();
        while (true) {
            apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido:", "Apellido", JOptionPane.PLAIN_MESSAGE);

            if (apellido != null && !apellido.isEmpty() && menu.esNombreValido(apellido)) {
                break; // Salir del bucle si el apellido es válido y no se cancela
            } else {
                JOptionPane.showMessageDialog(null, "El apellido ingresado es inválido o está vacío. Por favor, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return apellido;
    }

    public String solicitarCorreoSwing() {
        String email = null;
        Menu menu = new Menu();

        while (true) {
            email = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:", "Correo Electrónico", JOptionPane.PLAIN_MESSAGE);

            if (email == null) {
                // El usuario presionó "Cancelar" o cerró el cuadro de diálogo
                JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

            if (!email.isEmpty() && menu.esCorreoValido(email)) {
                break; // Salir del bucle si el correo es válido
            } else {
                JOptionPane.showMessageDialog(null, "El correo electrónico ingresado es inválido o está vacío. Debe terminar en [@gmail.com] y ser mayor a 10 caracteres. Por favor, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return email;
    }

    public int solicitarAdminSwing() {
        int seleccion = 0;
        Object[] opciones = {"Si", "No"};

        while (true) {
            // Mostrar un cuadro de diálogo con opciones usando JOptionPane
            seleccion = JOptionPane.showOptionDialog(null, "¿Usuario Administrador?",
                    "Selección de Administrador", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == JOptionPane.YES_OPTION  || seleccion == JOptionPane.NO_OPTION)
            {
                break; // Salir del bucle si se selecciona una opción válida
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Devolver 1 si se selecciona "Si" (administrador), 2 si se selecciona "No" (no administrador)
        return seleccion == JOptionPane.YES_OPTION ? 1 : 2;
    }


    //endregion


}
