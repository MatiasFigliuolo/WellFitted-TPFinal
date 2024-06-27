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





