package Usuarios;

import java.util.Objects;
import java.util.Scanner;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private String email;
    private Boolean admin;


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
        System.out.println("Contraseña: ");
        int contrasenia= scan.nextInt();
        if(contrasenia == 1999)
        {
            setAdmin(true);
            System.out.println("! Modo administrador activado !");
        }
        else
        {
            System.out.println("! Contraseña incorrecta !");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
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
}
