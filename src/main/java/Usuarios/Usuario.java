package Usuarios;

public class Usuario {

    private String nombre;
    private String email;
    private String ID;
    private Boolean admin;


    public Usuario(String nombre, String email, String ID) {
        this.nombre = nombre.toUpperCase();
        this.email = email;
        this.ID = ID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
