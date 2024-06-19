package org.example;

import Usuarios.GestionUsuarios;
import Usuarios.Perfil;
import Usuarios.Usuario;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Menu
{
    public Menu() {
    }

    public void menu()
    {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        Perfil usuario = inicioSesion(gestionUsuarios);
        if(usuario.getAdmin()==true)
        {
            menuAdmin(usuario,gestionUsuarios);

        }else
        {
            menuCliente(usuario,gestionUsuarios);
        }
    }

    public Perfil inicioSesion(GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
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

    public Perfil crearSesion(GestionUsuarios gestionUsuarios)
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

    public void menuAdmin(Perfil usuario, GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
        int seleccion = 0;
        while (seleccion != -1)
        {
            System.out.println("- - - MENU ADMIN - - -");
            System.out.println("0. Salir");

            seleccion=scan.nextInt();
            switch (seleccion)
            {
                case 0: seleccion=-1;
                break;
            }
        }
    }

    public void menuCliente(Perfil usuario, GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
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
}
