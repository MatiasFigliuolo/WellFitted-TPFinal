package Usuarios;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

public class GestionUsuarios {

    private TreeSet<Perfil> usuarios = new TreeSet<>();

    public TreeSet<Perfil> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(TreeSet<Perfil> usuarios) {
        this.usuarios = usuarios;
    }

    public Perfil crearUsuario()
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
        if(agregar(perfil))
        {
            return perfil;
        }
        return null;
    }

    public Perfil inicioSesion()
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
                    perfil = buscarUsuario();
                    break;
                case 2:
                    perfil = crearUsuario();
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

    public Perfil buscarUsuario()
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

    public Boolean agregar(Perfil usuario)
    {
       return usuarios.add(usuario);
    }

    public Boolean quitar(Perfil usuario)
    {
        return usuarios.remove(usuario);
    }

    public void mostrar()
    {
        System.out.println(usuarios.toString());
    }

    @Override
    public String toString() {
        return "GestionUsuarios{" +
                "usuarios=" + usuarios +
                '}';
    }
}
