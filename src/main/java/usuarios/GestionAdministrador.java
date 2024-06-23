package usuarios;

import Interfazes.Quitable;

import java.util.Scanner;

public class GestionAdministrador {

    public GestionAdministrador() {
    }

    public void modificacionUsuario(GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
        gestionUsuarios.mostrar();
        Perfil usuario = null;
        String email;
        usuario = gestionUsuarios.buscarUsuario();
        if(usuario!=null)
        {
            menuModificarUsuario(usuario,gestionUsuarios);
        }
        else
        {
        }
    }

    public void menuModificarUsuario(Perfil usuario,GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Que quiere hacer? ");
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar permiso admin");
        System.out.println("3. Eliminar Usuario");
        System.out.println("0. Cancelar");
        int seleccion = scan.nextByte();

        switch (seleccion)
        {
            case 1: cambiarNombre(usuario);
                break;
            case 2: cambiarPermisoAdmin(usuario);
                break;
            case 3: eliminarUsuario(usuario,gestionUsuarios);
                break;
            default: ;
                break;
        }

    }

    public void cambiarNombre(Perfil usuario)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese nuevo nombre:");
        String nombre = scan.next();
        System.out.println("Ingrese nuevo apellido:");
        String apellido = scan.next();
        String nombreYapellido= nombre +" "+ apellido;
        usuario.setNombre(nombreYapellido);
        System.out.println("Nombre cambiado a: "+nombreYapellido);
    }

    public void cambiarPermisoAdmin(Perfil usuario)
    {
        Scanner scan = new Scanner(System.in);
        if(usuario.getAdmin())
        {
            System.out.println("Quitar permiso admin?");
            System.out.println("1. Si");
            System.out.println("2. No");
            int seleccion = scan.nextByte();
            if(seleccion==1)
            {
                usuario.setAdmin(false);
                System.out.println("! Permisos quitados exitosamente !");
            }
        }else
        {
            System.out.println("Agregar permiso admin?");
            System.out.println("1. Si");
            System.out.println("2. No");
            int seleccion = scan.nextByte();
            if(seleccion==1)
            {
                usuario.setAdmin(true);
                System.out.println("! Permiso Agregado exitosamente !");
            }
        }
    }

    public void eliminarUsuario(Perfil usuario,GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Seguro que quiere eliminar este usuario?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int seleccion = scan.nextByte();
        if(seleccion==1)
        {
            gestionUsuarios.quitar(usuario);
            System.out.println("! Usuario eliminado !");
        }
    }

}
