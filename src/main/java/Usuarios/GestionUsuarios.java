package Usuarios;

import java.util.TreeSet;

public class GestionUsuarios {

    private TreeSet<Perfil> usuarios = new TreeSet<>();

    public TreeSet<Perfil> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(TreeSet<Perfil> usuarios) {
        this.usuarios = usuarios;
    }

    public Boolean agregar(Perfil usuario)
    {
       return usuarios.add(usuario);
    }

    public Boolean quitar(Perfil usuario)
    {
        return usuarios.remove(usuario);
    }

    @Override
    public String toString() {
        return "GestionUsuarios{" +
                "usuarios=" + usuarios +
                '}';
    }
}
