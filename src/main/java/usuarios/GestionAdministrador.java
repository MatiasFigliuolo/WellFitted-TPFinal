package usuarios;

import Interfazes.Quitable;
import exepciones.InvalidOptionException;
import org.example.Menu;

import java.util.Scanner;

public class GestionAdministrador {

    public GestionAdministrador() {
    }

    public void modificacionUsuario(GestionUsuarios gestionUsuarios)
    {
        Scanner scan = new Scanner(System.in);
        gestionUsuarios.mostrarUsuarios();
        Perfil usuario = null;
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
        int seleccion;
     while(true) {
         System.out.println("Que quiere hacer? ");
         System.out.println("1. Cambiar nombre");
         System.out.println("2. Cambiar permiso admin");
         System.out.println("3. Eliminar Usuario");
         System.out.println("0. Cancelar");

         try {
             seleccion = Menu.validateOption(scan.nextLine(),0, 3);
             break; // Opción válida, salir del bucle
         } catch (InvalidOptionException e) {
             System.out.println(e.getMessage());
         }
     }


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
        int seleccion;
        if(usuario.getAdmin())
        {

            while(true) {

                System.out.println("Quitar permiso admin?");
                System.out.println("1. Si");
                System.out.println("2. No");

                try {
                    seleccion = Menu.validateOption(scan.nextLine(),1, 2);
                    break; // Opción válida, salir del bucle
                } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                }
            }

            if(seleccion==1)
            {
                usuario.setAdmin(false);
                System.out.println(Menu.ANSI_GREEN + "! Permisos quitados exitosamente !" + Menu.ANSI_RESET);
            }
        }
        else
        {

            while(true) {
                System.out.println("Agregar permiso admin?");
                System.out.println("1. Si");
                System.out.println("2. No");

                try {
                    seleccion = Menu.validateOption(scan.nextLine(),1, 2);
                    break; // Opción válida, salir del bucle
                } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                }
            }

            if(seleccion == 1)
            {
                usuario.setAdmin(true);
                System.out.println(Menu.ANSI_GREEN + "! Permiso Agregado exitosamente !" + Menu.ANSI_RESET);
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

    public void mostrarUsuarios(GestionUsuarios gestionUsuarios)
    {
        gestionUsuarios.mostrarUsuarios();
    }

}
