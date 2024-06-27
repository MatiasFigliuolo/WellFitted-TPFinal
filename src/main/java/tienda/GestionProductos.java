package tienda;

import Interfazes.Agregable;
import Interfazes.Quitable;
import enums.*;
import exepciones.*;
import org.json.JSONArray;
import org.json.JSONObject;

import org.example.Menu;
import javax.swing.*;
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
        this.creaciondeID = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        ;
        this.idLenght = 5;
        this.bermudasPath = "./Bermudas.json";
        this.buzosPath = "./Buzos.json";
        this.camperasPath = "./Camperas.json";
        this.pantalonesPath = "./Pantalones.json";
        this.ropaInteriorPath = "./RopaInterior.json";
        this.remerasPath = "./Remeras.json";
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

    public void cargarBermudas(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonABermudas(jsonObject);
        }
    }

    public void cargarDatosJsonABermudas(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoBermuda tipoBermuda = TipoBermuda.valueOf(jsonObject.getString("tipoBermuda"));
        Bermuda bermuda = new Bermuda(nombre, id, stock, precio, tallaLetra, tipoBermuda);
        agregar(bermuda);
    }

    public void cargarPantalon(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonAPantalon(jsonObject);
        }
    }

    public void cargarDatosJsonAPantalon(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoPantalon tipoPantalon = TipoPantalon.valueOf(jsonObject.getString("tipoPantalon"));
        Pantalon pantalon = new Pantalon(nombre, id, stock, precio, tallaLetra, tipoPantalon);
        agregar(pantalon);
    }

    public void cargarRopaInterior(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonARopaInterior(jsonObject);
        }
    }

    public void cargarDatosJsonARopaInterior(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        TallaLetra tallaLetra = TallaLetra.valueOf(jsonObject.getString("tallaLetra"));
        TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(jsonObject.getString("tipoRopaInterior"));
        RopaInterior ropaInterior = new RopaInterior(nombre, id, stock, precio, tallaLetra, tipoRopaInterior);
        agregar(ropaInterior);
    }

    public void cargarBuzos(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonABuzos(jsonObject);
        }
    }

    public void cargarDatosJsonABuzos(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla = jsonObject.getFloat("talla");
        Boolean capucha = jsonObject.getBoolean("capucha");
        Buzo buzo = new Buzo(nombre, id, stock, precio, talla, capucha);
        agregar(buzo);
    }

    public void cargarRemeras(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonARemeras(jsonObject);
        }
    }

    public void cargarDatosJsonARemeras(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla = jsonObject.getFloat("talla");
        TipoRemera tipoRemera = TipoRemera.valueOf(jsonObject.getString("tipoRemera"));
        Remera remera = new Remera(nombre, id, stock, precio, talla, tipoRemera);
        agregar(remera);
    }

    public void cargarCamperas(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            cargarDatosJsonACamperas(jsonObject);
        }
    }

    public void cargarDatosJsonACamperas(JSONObject jsonObject) {
        String nombre = jsonObject.getString("nombre");
        String id = jsonObject.getString("id");
        int stock = jsonObject.getInt("stock");
        Number precio = jsonObject.getNumber("precio");
        float talla = jsonObject.getFloat("talla");
        TipoCampera tipoCampera = TipoCampera.valueOf(jsonObject.getString("tipoCampera"));
        Campera campera = new Campera(nombre, id, stock, precio, talla, tipoCampera);
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
            if (producto instanceof Bermuda) {
                JSONObject jsonObject = producto.toJson();
                jsonArrayBermuda.put(jsonObject);
            }
            if (producto instanceof Buzo) {
                JSONObject jsonObject = producto.toJson();
                jsonArrayBuzo.put(jsonObject);
            }
            if (producto instanceof Campera) {
                JSONObject jsonObject = producto.toJson();
                jsonArrayCampera.put(jsonObject);
            }
            if (producto instanceof Remera) {
                JSONObject jsonObject = producto.toJson();
                jsonArrayRemera.put(jsonObject);
            }
            if (producto instanceof RopaInterior) {
                JSONObject jsonObject = producto.toJson();
                jsonArrayRopaInterior.put(jsonObject);
            }
            if (producto instanceof Pantalon) {
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


    public String generarId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String id = null;
        for (int i = 0; i < idLenght; i++) {
            int index = random.nextInt(creaciondeID.length());
            sb.append(creaciondeID.charAt(index));
        }
        id = sb.toString();
        return id;
    }//Metodo que crea un id de 5 caracteres de manera aleatoria

    public Producto buscarProductoPorId(String idBuscado) throws ProductoNoEncontradoException {

        for (Producto producto : productos) {
            if (producto.getId().equals(idBuscado)) {
                return producto; // Retorna el producto si se encuentra
            }
        }
        throw new ProductoNoEncontradoException("No se encontró ningún producto con el ID " + idBuscado);
    }  //Metodo de busqueda de productos por id

    @Override
    public Boolean agregar(Producto elemento) {   //Metodo para agregar productos
        return productos.add(elemento);
    } //Metodo para agregar productos

    @Override
    public Boolean quitar(Producto elemento) {
        return productos.remove(elemento);
    }    //Metodo para quitar productos



    //region   Metodos JavaSwing

    public void filtrarProductosSwing() {
        String[] opciones = {"Filtrar por Tipo", "Filtrar por Prenda", "Cancelar"};
        JComboBox<String> filtroComboBox = new JComboBox<>(opciones);

        JOptionPane.showMessageDialog(null, filtroComboBox, "Seleccione una opción de filtro", JOptionPane.PLAIN_MESSAGE);

        String seleccion = (String) filtroComboBox.getSelectedItem();
        if (seleccion != null) {
            switch (seleccion) {
                case "Filtrar por Tipo":
                    filtrarPorTipoSwing();
                    break;
                case "Filtrar por Prenda":
                    filtrarPorPrendaSwing();
                    break;
                case "Cancelar":
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
    public void filtrarPorTipoSwing() {
        String[] opciones = {"Superior", "Inferior"};

        JComboBox<String> tipoComboBox = new JComboBox<>(opciones);
        JOptionPane.showMessageDialog(null, tipoComboBox, "Seleccione el tipo de prenda", JOptionPane.PLAIN_MESSAGE);

        String seleccion = (String) tipoComboBox.getSelectedItem();
        if (seleccion != null) {
            try {
                if (seleccion.equalsIgnoreCase("superior")) {
                    filtrarYImprimirPorClaseSwing(ProductoSup.class);
                } else if (seleccion.equalsIgnoreCase("inferior")) {
                    filtrarYImprimirPorClaseSwing(ProductoInf.class);
                } else {
                    throw new IllegalArgumentException(Menu.ANSI_RED + "Tipo inválido: " + seleccion + Menu.ANSI_RESET);
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void filtrarPorPrendaSwing() {
        String[] opciones = {"Campera", "Pantalon", "Bermuda", "Remera"}; // Ejemplo de tipos de prendas disponibles

        JComboBox<String> prendaComboBox = new JComboBox<>(opciones);
        JOptionPane.showMessageDialog(null, prendaComboBox, "Seleccione el tipo de prenda", JOptionPane.PLAIN_MESSAGE);

        String seleccion = (String) prendaComboBox.getSelectedItem();
        if (seleccion != null) {
            DefaultListModel<Producto> listModel = new DefaultListModel<>();
            productos.stream()
                    .filter(producto -> producto.getClass().getSimpleName().equalsIgnoreCase(seleccion))
                    .forEach(listModel::addElement);

            JList<Producto> lista = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(lista);
            JOptionPane.showMessageDialog(null, scrollPane, "Productos encontrados", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void filtrarYImprimirPorClaseSwing(Class<? extends Producto> claseDeseada) {
        DefaultListModel<Producto> listModel = new DefaultListModel<>();
        productos.stream()
                .filter(claseDeseada::isInstance)
                .forEach(listModel::addElement);

        JList<Producto> lista = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(lista);
        JOptionPane.showMessageDialog(null, scrollPane, "Productos encontrados", JOptionPane.PLAIN_MESSAGE);
    }

    public void listrarProductosSwing(JFrame frame, JScrollPane scrollPane) {
        DefaultListModel<Producto> listModel = new DefaultListModel<>();
        for (Producto producto : getProductos()) {
            listModel.addElement(producto);
        }
        JList<Producto> lista = new JList<>(listModel);
        scrollPane.setViewportView(lista);
        try {
            frame.revalidate();
            frame.repaint();
        } catch (NullPointerException e)
        {
        }
    }

    public void crearProductoSwing(JFrame frame) {
        // Ingresar el nombre del producto
        String nombre = solicitarNombre();

        // Generar ID
        String ID = generarId();

        // Solicitar Stock
        int stock = solicitarStockSwing();

        // Solicitar Precio
        double precio = solicitarPrecioSwing();

        // Solicitar Categoría
        String categoria = solicitarCategoriaSwing();

        if (categoria.equalsIgnoreCase("inferior")) {
            String talleLetra = solicitarTalleLetraSwing();
            TallaLetra talle = TallaLetra.valueOf(talleLetra.toUpperCase());
            String tipoInferior = solicitarTipoProductoInferiorSwing().toLowerCase();

            switch (tipoInferior) {
                case "pantalon":
                    TipoPantalon tipoPantalon = TipoPantalon.valueOf(solicitarTipoPantalonSwing().toUpperCase());
                    if (agregar(new Pantalon(nombre, ID, stock, precio, talle, tipoPantalon))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "bermuda":
                    TipoBermuda tipoBermuda = TipoBermuda.valueOf(solicitarTipoBermudaSwing().toUpperCase());
                    if (agregar(new Bermuda(nombre, ID, stock, precio, talle, tipoBermuda))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "ropa_interior":
                    TipoRopaInterior tipoRopaInterior = TipoRopaInterior.valueOf(solicitarTipoRopaInteriorSwing().toUpperCase());
                    if (agregar(new RopaInterior(nombre, ID, stock, precio, talle, tipoRopaInterior))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Tipo de producto inferior no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else if (categoria.equalsIgnoreCase("superior")) {
            float talleNumerico = solicitarTalleNumericoSwing();
            String tipoSuperior = solicitarTipoProductoSuperiorSwing().toLowerCase();

            switch (tipoSuperior) {
                case "remera":
                    TipoRemera tipoRemera = TipoRemera.valueOf(solicitarTipoRemeraSwing().toUpperCase());
                    if (agregar(new Remera(nombre, ID, stock, precio, talleNumerico, tipoRemera))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "campera":
                    TipoCampera tipoCampera = TipoCampera.valueOf(solicitarTipoCamperaSwing().toUpperCase());
                    if (agregar(new Campera(nombre, ID, stock, precio, talleNumerico, tipoCampera))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "buzo":
                    boolean tieneCapucha = solicitarCapuchaSwing();
                    if (agregar(new Buzo(nombre, ID, stock, precio, talleNumerico, tieneCapucha))) {
                        JOptionPane.showMessageDialog(frame, "! Producto Agregado !.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "! Id del producto ya en sistema !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Tipo de producto superior no válido.", "Error", JOptionPane.ERROR_MESSAGE);

                    break;
            }
        } else {
            System.out.println("Categoría de producto no válida.");
        }
    }

    private String solicitarNombre() {
        return JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
    }

    public int solicitarStockSwing() {
        int stock = -1;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Stock:");
                stock = Integer.parseInt(input);
                if (stock > 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "El stock ingresado es inválido. Debe ser un stock mayor a 0. Por favor, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número entero válido.");
            }
        }
        return stock;
    }

    public Double solicitarPrecioSwing() {
        double precio = -1;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Precio:");
                precio = Double.parseDouble(input);
                if (precio > 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "El precio ingresado es inválido. Debe ser un precio mayor a 0. Por favor, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
        return precio;
    }

    public String solicitarCategoriaSwing() {
        Set<String> categoriasValidas = Set.of("inferior", "superior");
        String categoria;
        while (true) {
            categoria = JOptionPane.showInputDialog(null, "Categoría (inferior/superior):");
            if (categoriasValidas.contains(categoria.toLowerCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Categoría no válida. Debe ser 'inferior' o 'superior'.");
            }
        }
        return categoria;
    }

    public String solicitarTalleLetraSwing() {
        Set<String> tiposValidos = Set.of("XSMALL", "SMALL", "MEDIUM", "LARGE", "XLARGE");
        String talleLetra;
        while (true) {
            talleLetra = JOptionPane.showInputDialog(null, "Talle (XSMALL, SMALL, MEDIUM, LARGE, XLARGE):");
            if (tiposValidos.contains(talleLetra.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "La talla ingresada es inválida. Debe ser (XSMALL, SMALL, MEDIUM, LARGE, XLARGE). Por favor, intente nuevamente.");
            }
        }
        return talleLetra;
    }

    public String solicitarTipoProductoInferiorSwing() {
        Set<String> tiposValidos = Set.of("pantalon", "bermuda", "ropa_interior");
        String tipoInferior;
        while (true) {
            tipoInferior = JOptionPane.showInputDialog(null, "Tipo de producto inferior (pantalon, bermuda, ropa_interior):");
            if (tiposValidos.contains(tipoInferior.toLowerCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de producto ingresado es inválido. Debe ser (pantalon, bermuda, ropa_interior). Por favor, intente nuevamente.");
            }
        }
        return tipoInferior;
    }

    public String solicitarTipoPantalonSwing() {
        Set<String> tiposValidos = Set.of("JOGGING", "JEANS", "CARGO", "LEGGINS");
        String tipoPantalon;
        while (true) {
            tipoPantalon = JOptionPane.showInputDialog(null, "Tipo de pantalón (JOGGING, JEANS, CARGO, LEGGINS):");
            if (tiposValidos.contains(tipoPantalon.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de pantalón ingresado es inválido. Debe ser (JOGGING, JEANS, CARGO, LEGGINS). Por favor, intente nuevamente.");
            }
        }
        return tipoPantalon;
    }

    public String solicitarTipoBermudaSwing() {
        Set<String> tiposValidos = Set.of("AJUSTADA", "PIERNA_ANCHA", "A_MEDIDA", "CASUAL", "DEPORTIVA");
        String tipoBermuda;
        while (true) {
            tipoBermuda = JOptionPane.showInputDialog(null, "Tipo de bermuda (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA):");
            if (tiposValidos.contains(tipoBermuda.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de bermuda ingresado es inválido. Debe ser (AJUSTADA, PIERNA_ANCHA, A_MEDIDA, CASUAL, DEPORTIVA). Por favor, intente nuevamente.");
            }
        }
        return tipoBermuda;
    }

    public String solicitarTipoRopaInteriorSwing() {
        Set<String> tiposValidos = Set.of("BOXER", "SLIPS", "TRUNKS", "CALZONCILLOS_CONICOS");
        String tipoRopaInterior;
        while (true) {
            tipoRopaInterior = JOptionPane.showInputDialog(null, "Tipo de ropa interior (BOXER, SLIPS, TRUNKS, CALZONCILLOS_CONICOS):");
            if (tiposValidos.contains(tipoRopaInterior.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de ropa interior ingresado es inválido. Debe ser (BOXER, SLIPS, TRUNKS, CALZONCILLOS_CONICOS). Por favor, intente nuevamente.");
            }
        }
        return tipoRopaInterior;
    }

    public float solicitarTalleNumericoSwing() {
        float talleNumerico = -1;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Ingrese el talle (número entre 30 y 50):");
                talleNumerico = Float.parseFloat(input);
                if (talleNumerico >= 30 && talleNumerico <= 50) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "El talle ingresado es inválido. Debe ser un número entre 30 y 50.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
        return talleNumerico;
    }

    public String solicitarTipoProductoSuperiorSwing() {
        Set<String> tiposValidos = Set.of("remera", "campera", "buzo");
        String tipoSuperior;
        while (true) {
            tipoSuperior = JOptionPane.showInputDialog(null, "Tipo de producto superior (remera, campera, buzo):");
            if (tiposValidos.contains(tipoSuperior.toLowerCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de producto ingresado es inválido. Debe ser (remera, campera, buzo). Por favor, intente nuevamente.");
            }
        }
        return tipoSuperior;
    }

    public String solicitarTipoRemeraSwing() {
        Set<String> tiposValidos = Set.of("M_CORTA", "M_LARGA", "MUSCULOSA", "DEPORTIVA");
        String tipoRemera;
        while (true) {
            tipoRemera = JOptionPane.showInputDialog(null, "Tipo de remera (M_CORTA, M_LARGA, MUSCULOSA, DEPORTIVA):");
            if (tiposValidos.contains(tipoRemera.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de remera ingresado es inválido. Debe ser (M_CORTA, M_LARGA, MUSCULOSA, DEPORTIVA). Por favor, intente nuevamente.");
            }
        }
        return tipoRemera;
    }

    public String solicitarTipoCamperaSwing() {
        Set<String> tiposValidos = Set.of("ROMPE_VIENTO", "NIEVE", "AISLANTE");
        String tipoCampera;
        while (true) {
            tipoCampera = JOptionPane.showInputDialog(null, "Tipo de campera (ROMPE_VIENTO, NIEVE, AISLANTE):");
            if (tiposValidos.contains(tipoCampera.toUpperCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El tipo de campera ingresado es inválido. Debe ser (ROMPE_VIENTO, NIEVE, AISLANTE). Por favor, intente nuevamente.");
            }
        }
        return tipoCampera;
    }

    public boolean solicitarCapuchaSwing() {
        Set<String> tiposValidos = Set.of("si", "no");
        String capucha;
        while (true) {
            capucha = JOptionPane.showInputDialog(null, "Capucha (si/no):");
            if (tiposValidos.contains(capucha.toLowerCase())) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El valor de capucha ingresado es inválido. Debe ser (si/no). Por favor, intente nuevamente.");
            }
        }
        return capucha.equalsIgnoreCase("si");
    }

    public void eliminarProductoPorIdSwing() {
        // Pedir al usuario que ingrese el ID del producto a eliminar
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea eliminar:").trim();

        try {
            boolean encontrado = false;
            for (Producto producto : productos) {
                if (producto.getId().equals(id)) {
                    encontrado = quitar(producto);
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                    break; // Termina el bucle una vez que se elimina el producto
                }
            }
            if (!encontrado) {
                throw new ProductoNoEncontradoException("No se encontró ningún producto con el ID " + id);
            }
        } catch (ProductoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); // Maneja la excepción si no se encuentra el producto
        }
    }

    public void modificarProductoSwing() {
        Producto productoBuscado;
        try {
        String idBuscado = JOptionPane.showInputDialog(null, "Ingrese el ID del producto buscado:").trim();
        productoBuscado = buscarProductoPorId(idBuscado);
        } catch (ProductoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null, "Cancelado Modificacion");
            return;
        }

        String[] opciones = {"Modificar Nombre", "Modificar Stock", "Modificar Precio", "Salir"};
        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione la modificación que desea realizar",
                    "Modificar Producto",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    modificarNombreSwing(productoBuscado);
                    break;
                case 1:
                    modificarStockSwing(productoBuscado);
                    break;
                case 2:
                    modificarPrecioSwing(productoBuscado);
                    break;
                case 3:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;
            }
        }
    }

    private void modificarNombreSwing(Producto productoBuscado) {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del producto:").trim();
        productoBuscado.setNombre(nombre);
        JOptionPane.showMessageDialog(null, "Modificación realizada correctamente");
    }

    private void modificarStockSwing(Producto productoBuscado) {
        int stock = solicitarStockSwing();
        productoBuscado.setStock(stock);
        JOptionPane.showMessageDialog(null, "Modificación realizada correctamente");
    }

    private void modificarPrecioSwing(Producto productoBuscado) {
        double precio = solicitarPrecioSwing();
        productoBuscado.setPrecio(precio);
        JOptionPane.showMessageDialog(null, "Modificación realizada correctamente");
    }


    //endregion

}


