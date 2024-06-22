package org.example;

import Enums.TallaLetra;
import Enums.TipoPantalon;
import Exepciones.InvalidOptionException;
import Exepciones.ProductoNoEncontradoException;
import Tienda.GestionProductos;
import Tienda.Producto;
import Usuarios.GestionUsuarios;
import Usuarios.Perfil;
import Usuarios.Usuario;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Menu
{

    private Scanner scan;


    public Menu() {

        this.scan = new Scanner(System.in);
    }

    public void menu() throws ProductoNoEncontradoException {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionProductos gestionProductos = new GestionProductos();


        Perfil usuario = inicioSesion(gestionUsuarios);
        if(usuario.getAdmin()==true)
        {
            menuAdmin(usuario,gestionUsuarios, gestionProductos);

        }else
        {
            menuCliente(usuario,gestionUsuarios);
        }
    }

    public Perfil inicioSesion(GestionUsuarios gestionUsuarios)
    {

        Perfil perfil = null;
        while (perfil == null)
        {
            int seleccion;
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Crear nuevo usuario");
            seleccion = scan.nextInt();
            switch (seleccion) {
                case 1:
                    perfil = iniciarSesion(gestionUsuarios.getUsuarios());
                    break;
                case 2:
                    perfil = crearSesion(gestionUsuarios);
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

    public Perfil iniciarSesion(TreeSet<Perfil> usuarios)
    {
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

    public Perfil crearSesion(GestionUsuarios gestionUsuarios)
    {
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
        scan.nextLine(); //limpiar buffer
        if(seleccion==1)
        {
            perfil.chequearAdmin();
        }
        if(gestionUsuarios.agregar(perfil))
        {
            return perfil;
        }
        return null;
    }

    public void menuCliente(Perfil usuario, GestionUsuarios gestionUsuarios)
    {
        int seleccion = 0;
        while (seleccion != -1)
        {
            System.out.println("- - - MENU CLIENTE - - -");
            System.out.println("0. Salir");

            seleccion=scan.nextInt();
            switch (seleccion)
            {
                case 0: seleccion=-1;
                    break;
            }
        }
    }



    public void menuAdmin(Perfil usuario, GestionUsuarios gestionUsuarios, GestionProductos gestionProductos) throws ProductoNoEncontradoException
    {
        int seleccion = 0;


        while (seleccion != -1)
        {
            System.out.println("- - - MENU ADMIN - - -\n");
            seleccion = menuVisualAdmin();



        System.out.println("\nHa seleccionado la opción: " + seleccion);


            switch (seleccion)
            {
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
                case 5: //Cambiar contrasenia usuario

                    break;
//-----------------------------------------------------------------------------------
                case 6: //Modificar nombre usuario

                    break;
//-----------------------------------------------------------------------------------
                case 7: //Modificar email usuario

                    break;
//-----------------------------------------------------------------------------------
                case 8: //Eliminar usuario

                    break;
//-----------------------------------------------------------------------------------
                case 0:
                    seleccion=-1;
                    break;
//-----------------------------------------------------------------------------------
                default:

                    break;
            }
        }
    }



    public int menuVisualAdmin(){

        int seleccion = 0;


        while(true){
        System.out.println("\n1 Agregar nuevo producto" +
                      "\n2 Eliminar un producto" +
                      "\n3 Modificar un Producto" +
                      "\n4 Listar Productos" +
                      "\n" +
                      "\n5 Cambiar contrasenia usuario" +
                      "\n6 Modificar nombre usuario" +
                      "\n7 Modificar email usuario" +
                      "\n8 Eliminar usuario" +
                      "\n0 Salir" +
                      "\nInsertar Opcion: ");

        try {
            seleccion = validateOption(scan.nextLine(),8);
            break; // Opción válida, salir del bucle
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
        }}

        return seleccion;
    }


    public int menuVisualModifProducto(){

        int seleccion = 0;


        while(true){
            System.out.println(
                    "\n1 Nombre" +
                    "\n2 Stock" +
                    "\n3 Precio" +
                    "\n0 Salir" +
                    "\nInsertar Opcion: ");

            try {
                seleccion = validateOption(scan.nextLine(),3);
                break; // Opción válida, salir del bucle
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }}

        return seleccion;
    }






    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private int validateOption(String input, int limite) throws InvalidOptionException {
        // Verifica si la entrada está compuesta solo de dígitos
        if (!input.matches("\\d+")) {
            throw new InvalidOptionException("Error: Debe ingresar un número válido.");
        }

        // Convierte la cadena a un número entero
        int number = Integer.parseInt(input);

        // Verifica si el número está en el rango válido (0 a 7)
        if (number < 0 || number > limite) {
            throw new InvalidOptionException("Error: Opción no válida. Por favor, seleccione un número entre 0 y ." + limite);
        }

        return number;
    }

    }





