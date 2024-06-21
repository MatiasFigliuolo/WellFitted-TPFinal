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
        Perfil usuario = gestionUsuarios.inicioSesion();
        if(usuario.getAdmin()==true)
        {
            menuAdmin(usuario,gestionUsuarios);

        }else
        {
            menuCliente(usuario,gestionUsuarios);
        }
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
            System.out.println("1. Mirar Productos");
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
