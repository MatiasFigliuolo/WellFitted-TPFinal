package usuarios;

import javax.swing.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private String email;
    private Boolean admin;

    private static int contraAdmin = 1999;


    public Usuario(String nombre, String email) {
        this.nombre = nombre.toUpperCase();
        this.email = email;
        this.admin = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void chequearAdmin()
    {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (i<3)
        try {
            System.out.println("Ingrese la Contraseña:    (Intentos: "+i+"/3)   //Solo numericos");
            int contrasenia= scan.nextInt();
            if(contrasenia == contraAdmin)
            {
                setAdmin(true);
                System.out.println("! Modo administrador activado !");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            else
            {
                System.out.println("! Contraseña incorrecta !");
                i++;
            }
        }catch (InputMismatchException e)
        {
            System.err.println("! Caracteres no numericos ingresados !");
            return;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public int compareTo(Usuario o)
    {
        return this.email.compareTo(o.getEmail());
    }


    @Override
    public String toString() {
        return String.format("-------------------------------------------------------" +
                             "\nUSUARIO\n" +
                             "|Nombre: "   + this.nombre +
                             "| |Email: "  + this.email +
                             "| |Admin: "  + this.admin);
    }


    //region Metodos javaSwing

    public void chequearAdminSwing() {
        int intentos = 0;
        while (intentos < 3) {
            try {
                String input = JOptionPane.showInputDialog(null, "Ingrese la Contraseña:\n(Intentos restantes: " + (3 - intentos) + ")", "Verificación de Administrador", JOptionPane.PLAIN_MESSAGE);
                if (input == null) {
                    // El usuario canceló la operación
                    return;
                }
                int contrasenia = Integer.parseInt(input);
                if (contrasenia == contraAdmin) {
                    setAdmin(true);
                    JOptionPane.showMessageDialog(null, "! Modo administrador activado !", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "! Contraseña incorrecta !", "Error", JOptionPane.ERROR_MESSAGE);
                    intentos++;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "! Caracteres no numéricos ingresados !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Número de intentos agotados. Acceso denegado.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    //endregion
}
