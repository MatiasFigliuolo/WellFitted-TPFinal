package Usuarios;

import Tienda.Carrito;

import java.util.ArrayList;

public class Perfil extends Usuario{

    private Carrito carrito;
    private ArrayList<Carrito> historial;
    public Perfil(String nombre, String email, String ID) {
        super(nombre, email, ID);
    }
}
