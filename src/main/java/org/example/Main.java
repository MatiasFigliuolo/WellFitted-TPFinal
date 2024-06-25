package org.example;

import exepciones.ProductoNoEncontradoException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws ProductoNoEncontradoException, IOException {
        Menu menu = new Menu();
        menu.menu();
    }
}

//---------------------
