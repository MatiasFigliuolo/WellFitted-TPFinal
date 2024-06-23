package usuarios;

import Interfazes.Agregable;
import Interfazes.Quitable;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

public class GestionUsuarios implements Agregable<Perfil>, Quitable<Perfil> {

    private TreeSet<Perfil> usuarios = new TreeSet<>();

    public TreeSet<Perfil> getUsuarios() {
        return usuarios;
    }

//region Métodos para guardar y cargar usuarios en un archivo
    public void guardarUsuariosEnArchivo(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados exitosamente en " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarUsuariosDesdeArchivo(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            usuarios = (TreeSet<Perfil>) ois.readObject();
            System.out.println("Usuarios cargados exitosamente desde " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los usuarios: " + e.getMessage());
        }
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
        scan.nextLine();//Limpiar Buffer
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
