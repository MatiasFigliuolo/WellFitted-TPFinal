package org.example;

import JavaSwing.MenuJavaSwing;
import exepciones.ProductoNoEncontradoException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws ProductoNoEncontradoException, IOException {
        MenuJavaSwing menuJavaSwing = new MenuJavaSwing();
        menuJavaSwing.createAndShowGUI();
    }
}
