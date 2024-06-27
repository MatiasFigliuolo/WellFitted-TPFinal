package JavaSwing;

import org.example.Menu;
import tienda.GestionProductos;
import usuarios.GestionAdministrador;
import usuarios.GestionUsuarios;
import usuarios.Perfil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJavaSwing {
    GestionUsuarios gestionUsuarios = new GestionUsuarios();
    GestionAdministrador gestionAdministrador = new GestionAdministrador();
    GestionProductos gestionProductos = new GestionProductos();
    MenuCliente menuCliente;
    MenuAdmin menuAdmin;

    public MenuJavaSwing() {
        //Cargar datos desde json
        this.gestionProductos.cargandoDatos();
        this.gestionUsuarios.cargandoDatos();
        this.menuAdmin = new MenuAdmin(gestionUsuarios,gestionAdministrador,gestionProductos);
        this.menuCliente = new MenuCliente(gestionUsuarios,gestionAdministrador,gestionProductos);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("--WEll FITTED--");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Inicio");
        menuBar.add(menuArchivo);

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemGuardar = new JMenuItem("Guardar");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);

        menuItemSalir.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponentsInicio(panel);

        frame.setVisible(true);
    }

    public void placeComponentsInicio(JPanel panel) {
        panel.setLayout(null);

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(0, 0, 150, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Crear Usuario");
        registerButton.setBounds(0, 25, 150, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuInicioSesion();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                Perfil perfil= gestionUsuarios.crearUsuarioSwing();
                if (perfil.getAdmin())
                {
                    menuAdmin.menuAdmin(perfil);
                } else {
                    menuCliente.menuCliente(perfil);
                }
            }
        });
    }

    public void menuInicioSesion() {
        JFrame frame = new JFrame("--WEll FITTED--");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Inicio de sesion");
        menuBar.add(menuArchivo);

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemGuardar = new JMenuItem("Guardar");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);

        menuItemSalir.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponentsInicioSesion(panel, frame);

        frame.setVisible(true);
    }

    public void placeComponentsInicioSesion(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        JButton buscarUsuarioButton = new JButton("Entrar");
        buscarUsuarioButton.setBounds(270, 20, 150, 25);
        panel.add(buscarUsuarioButton);

        buscarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                    try {
                        Perfil usuario = gestionUsuarios.buscarUsuario(userText.getText());
                        if (menu.esCorreoValido(userText.getText())) {
                            if (usuario.getAdmin())
                            {
                                menuAdmin.menuAdmin(usuario);
                            } else {
                                menuCliente.menuCliente(usuario);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "El email ingresado es inválido. debe terminar en [@gmail.com] y debe ser mayor a 10 caracteres. Por favor, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (NullPointerException ex)
                    {
                        JOptionPane.showMessageDialog(frame, "! Usuario No encontrado !", "Error", JOptionPane.ERROR_MESSAGE);
                    }


            }
        });
    }

}