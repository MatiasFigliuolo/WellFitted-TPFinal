package org.example;

import exepciones.InvalidOptionException;
import exepciones.ProductoNoEncontradoException;
import tienda.GestionProductos;
import usuarios.GestionAdministrador;
import usuarios.GestionUsuarios;
import usuarios.Perfil;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Scanner scan;


    //------------------------------------------------------------------
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    //----------------------------------------------------------------



    public Menu() {

        this.scan = new Scanner(System.in);
    }

    public void menu() throws ProductoNoEncontradoException {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionProductos gestionProductos = new GestionProductos();
        GestionAdministrador gestionAdministrador = new GestionAdministrador();

        // Cargar datos desde archivos al iniciar
        //gestionProductos.cargarProductosDesdeArchivo("productos.dat");
        //gestionUsuarios.cargarUsuariosDesdeArchivo("usuarios.dat");

        //Cargar datos desde json
        gestionProductos.cargandoDatos();
        gestionUsuarios.cargandoDatos();

        Perfil usuario = gestionUsuarios.inicioSesion();

        try {
            if (usuario.getAdmin() == true) {
                menuAdmin(usuario, gestionUsuarios, gestionProductos, gestionAdministrador);

            } else {
                menuCliente(usuario, gestionProductos);
            }
        }catch (NullPointerException e)
        {
            System.err.println("! Error, Finalizando Ejecucion !");
        }

    }


    public void menuCliente(Perfil usuario, GestionProductos gestionProductos) throws ProductoNoEncontradoException {
        int seleccion = 0;
        while (seleccion != -1) {
            System.out.println("\n- - - MENU CLIENTE - - -");
            seleccion = menuVisualCliente();

            System.out.println("\nHa seleccionado la opción: " + seleccion);

            switch (seleccion) {
                case 1: //Buscar Producto
                    gestionProductos.filtrarProductos();
                    break;
//-----------------------------------------------------------------------------------
                case 2: //Sumar al carrito
                    usuario.agregarAlCarrito(gestionProductos);
                    break;
//-----------------------------------------------------------------------------------
                case 3: //Quitar del carrito
                    usuario.quitarDelCarrito();
                    break;
//-----------------------------------------------------------------------------------
                case 4: //Mostrar carrito
                    usuario.mostrandoCarrito();
                    break;
//-----------------------------------------------------------------------------------

                case 5: //listar Productos
                    gestionProductos.listarProductos();
                    break;
//-----------------------------------------------------------------------------------
                case 6: //Realizar compra
                    usuario.realizarCompra();
                    break;
//-----------------------------------------------------------------------------------
                case 0:
                    seleccion = -1;
                    break;
            }
        }
    }

    public void menuAdmin(Perfil usuario, GestionUsuarios gestionUsuarios, GestionProductos gestionProductos, GestionAdministrador gestionAdministrador) throws ProductoNoEncontradoException {
        int seleccion = 0;
        while (seleccion != -1) {

            System.out.println("\n- - - MENU ADMIN - - -");
            seleccion = menuVisualAdmin();

            System.out.println("\nHa seleccionado la opción: " + seleccion);

            switch (seleccion) {
                case 1: //Agregar nuevo producto

                    gestionProductos.crearAgregarProducto();

                    break;
//-----------------------------------------------------------------------------------
                case 2: //Eliminar un producto
                    gestionProductos.eliminarProductoPorId();

                    break;
//-----------------------------------------------------------------------------------
                case 3: //Modificar un Producto
                    gestionProductos.modificarProducto();
                    break;
//-----------------------------------------------------------------------------------
                case 4:
                    gestionProductos.listarProductos();
                    break;
//-----------------------------------------------------------------------------------
                case 5:
                    gestionAdministrador.modificacionUsuario(gestionUsuarios); //Modificar un usuario
                    break;
//-----------------------------------------------------------------------------------
                case 6:
                    gestionUsuarios.crearUsuario(); //Crear usuario nuevo
                    break;
// -----------------------------------------------------------------------------------
                  case 7:
                    gestionAdministrador.mostrarUsuarios(gestionUsuarios); //Crear usuario nuevo
                    break;
// -----------------------------------------------------------------------------------

                case 0:
                    seleccion = -1;
                    break;
//-----------------------------------------------------------------------------------
                default:

                    break;
            }
        }
    }


    public int menuVisualAdmin() {

        int seleccion = 0;

        while (true) {
            System.out.println(
                    "\n1 Agregar nuevo producto" +
                    "\n2 Eliminar un producto" +
                    "\n3 Modificar un Producto" +
                    "\n4 Listar Productos" +
                    "\n" +
                    "\n5 Modificar usuario" +
                    "\n6 Crear Usuario" +
                    "\n7 Mostrar Usuarios" +
                    "\n0 Salir" +
                    "\nInsertar Opcion: ");

            try {
                seleccion = validateOption(scan.nextLine(),0, 7);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return seleccion;
    }


    public int menuVisualModifProducto() {

        int seleccion = 0;


        while (true) {
            System.out.println(
                            "\n1 Nombre" +
                            "\n2 Stock" +
                            "\n3 Precio" +
                            "\n0 Salir" +
                            "\nInsertar Opcion: ");

            try {
                seleccion = validateOption(scan.nextLine(),0, 3);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return seleccion;
    }


    public int menuVisualCliente() {

        int seleccion = 0;

        while (true) {
            System.out.println(
                            "\n1 Buscar Producto" +
                            "\n2 Sumar al carrito" +
                            "\n3 Quitar producto del carrito" +
                            "\n4 Mostrar carrito" +
                            "\n5 listar Productos" +
                            "\n6 Realizar compra" +
                            "\n0 Salir" +
                            "\nInsertar Opcion: ");

            try {
                seleccion = validateOption(scan.nextLine(),0, 6);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return seleccion;
    }



    public int menuVisualFiltrado() {

        int seleccion = 0;


        while (true) {
            System.out.println(
                            "\n1 Por Tipo" + // superior o inferior
                            "\n2 Por Prenda" + // pantalon,remera,campera,etc
                            "\n0 Salir" +
                            "\nInsertar Opcion: ");

            try {
                seleccion = validateOption(scan.nextLine(),0, 2);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        return seleccion;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //VALIDACIONES


    public static int validateOption(String input, int min, int max) throws InvalidOptionException {
        // Verifica si la entrada está compuesta solo de dígitos
        if (!input.matches("\\d+")) {
            throw new InvalidOptionException(ANSI_RED +"Error: Debe ingresar un número válido." + ANSI_RESET);
        }

        // Convierte la cadena a un número entero
        int number = Integer.parseInt(input);

        // Verifica si el número está en el rango válido (0 a 7)
        if (number < min || number > max) {
            throw new InvalidOptionException(ANSI_RED +"Error: Opción no válida. Por favor, seleccione un número entre " + min +" y " + max + ANSI_RESET);
        }

        return number;
    }

    //devuelve true si el string no contiene numeros
    public  boolean esNombreValido(String nombre) {
        // Verificar si el nombre contiene algún dígito
        return !nombre.matches(".*\\d.*");
    }

    //retorna true si el string corresponde con las propiedades
    public  boolean esCorreoValido(String correo) {
        // Verificar si los últimos 10 caracteres son '@gmail.com'
        return correo.endsWith("@gmail.com") && correo.length() > 10;
    }






}





