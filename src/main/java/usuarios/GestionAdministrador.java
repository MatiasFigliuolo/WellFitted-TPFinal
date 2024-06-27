package usuarios;

import exepciones.InvalidOptionException;
import org.example.Menu;
import javax.swing.*;
import java.util.Scanner;

public class GestionAdministrador {

    public GestionAdministrador() {
    }

    //region Metodos JavaSwing
    public void modificacionUsuarioSwing(GestionUsuarios gestionUsuarios) {
        // Crear un JTextField para que el usuario ingrese el correo electrónico
        JTextField correoTextField = new JTextField(20);
        Menu menu = new Menu();

        // Crear un JPanel para contener el JTextField y el mensaje de error
        JPanel panel = new JPanel();
        panel.add(new JLabel("Ingrese el correo electrónico del usuario:"));
        panel.add(correoTextField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Buscar Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String correo = correoTextField.getText();

            // Validar el correo electrónico ingresado
            if (menu.esCorreoValido(correo)) {
                Perfil usuario = gestionUsuarios.buscarUsuario(correo);

                if (usuario != null) {
                    menuModificarUsuarioSwing(usuario, gestionUsuarios);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El correo ingresado es inválido. Debe terminar en '@gmail.com' y debe tener más de 10 caracteres.");
            }
        }
    }

    public void menuModificarUsuarioSwing(Perfil usuario,GestionUsuarios gestionUsuarios) {
        String[] opciones = {"Cambiar nombre", "Cambiar permiso admin", "Eliminar Usuario", "Cancelar"};
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "¿Qué acción desea realizar?",
                "Modificar Usuario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[3]
        );

        switch (seleccion) {
            case 0:
                cambiarNombreSwing(usuario);
                break;
            case 1:
                cambiarPermisoAdminSwing(usuario);
                break;
            case 2:
                eliminarUsuarioSwing(usuario,gestionUsuarios);
                break;
            default:
                break;
        }
    }

    public void cambiarNombreSwing(Perfil usuario) {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nuevo nombre:");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese nuevo apellido:");
        String nombreCompleto = nombre + " " + apellido;
        usuario.setNombre(nombreCompleto.toUpperCase());
        JOptionPane.showMessageDialog(null, "Nombre cambiado a: " + nombreCompleto);
    }

    public void cambiarPermisoAdminSwing(Perfil usuario) {
        String[] opciones = {"Si", "No"};
        int seleccion;
        if (usuario.getAdmin()) {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Quitar permiso admin?",
                    "Cambiar Permiso Admin",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[1]
            );

            if (seleccion == 0) {
                usuario.setAdmin(false);
                JOptionPane.showMessageDialog(null, "! Permisos quitados exitosamente !");
            }
        } else {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Agregar permiso admin?",
                    "Cambiar Permiso Admin",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[1]
            );

            if (seleccion == 0) {
                usuario.setAdmin(true);
                JOptionPane.showMessageDialog(null, "! Permiso Agregado exitosamente !");
            }
        }
    }

    public void eliminarUsuarioSwing(Perfil usuario, GestionUsuarios gestionUsuarios) {
        int seleccion = JOptionPane.showConfirmDialog(
                null,
                "¿Seguro que quiere eliminar este usuario?",
                "Eliminar Usuario",
                JOptionPane.YES_NO_OPTION
        );

        if (seleccion == JOptionPane.YES_OPTION) {
            gestionUsuarios.quitar(usuario);
            JOptionPane.showMessageDialog(null, "! Usuario eliminado !");
        }
    }

    public void mostrarUsuariosSwing(GestionUsuarios gestionUsuarios) {
        StringBuilder sb = new StringBuilder();
        for (Perfil usuario : gestionUsuarios.getUsuarios()) {
            sb.append(usuario.toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(sb.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }
    //endregion

}



