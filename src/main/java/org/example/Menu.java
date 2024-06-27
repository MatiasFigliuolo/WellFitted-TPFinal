package org.example;

import exepciones.InvalidOptionException;
import exepciones.ProductoNoEncontradoException;
import tienda.GestionProductos;
import usuarios.GestionAdministrador;
import usuarios.GestionUsuarios;
import usuarios.Perfil;

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
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //VALIDACIONES

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





