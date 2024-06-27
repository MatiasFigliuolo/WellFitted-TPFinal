package JavaSwing;

import tienda.GestionProductos;
import usuarios.GestionAdministrador;
import usuarios.GestionUsuarios;
import usuarios.Perfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin {

    private GestionUsuarios gestionUsuarios;
    private GestionAdministrador gestionAdministrador;
    private GestionProductos gestionProductos;

    private JFrame frame;
    private JScrollPane scrollPane;

    public MenuAdmin(GestionUsuarios gestionUsuarios, GestionAdministrador gestionAdministrador, GestionProductos gestionProductos) {
        this.gestionUsuarios = gestionUsuarios;
        this.gestionAdministrador = gestionAdministrador;
        this.gestionProductos = gestionProductos;
    }

    public void menuAdmin(Perfil usuario) {
        JFrame frame = new JFrame("--WEll FITTED--");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Menu Admin");
        menuBar.add(menuArchivo);

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemGuardar = new JMenuItem("Guardar");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);

        menuItemSalir.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        placeComponentsMenuAdmin(panel,usuario);


        frame.setVisible(true);
    }

    public void placeComponentsMenuAdmin(JPanel panel, Perfil usuario) {
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        JButton agregarProductoButton = new JButton("Agregar nuevo Producto");
        JButton eliminarproductoButton = new JButton("Eliminar un producto");
        JButton modificarProductoButton = new JButton("Modificar un Producto");
        JButton listarProductosButton = new JButton("Listar Productos");
        JButton modificarUsuarioButton = new JButton("Modificar Usuario");
        JButton listaUsuariosButton = new JButton("Lista Usuarios");
        JButton menuClienteButton = new JButton("MenuCliente");

        buttonPanel.add(agregarProductoButton);
        buttonPanel.add(eliminarproductoButton);
        buttonPanel.add(modificarProductoButton);
        buttonPanel.add(listarProductosButton);
        buttonPanel.add(modificarUsuarioButton);
        buttonPanel.add(listaUsuariosButton);
        buttonPanel.add(menuClienteButton);

        panel.add(buttonPanel, BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        scrollPane.setPreferredSize(new Dimension(600, 400));

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gestionProductos.crearProductoSwing(frame);
                gestionProductos.guardarDatos();
            }
        });

        eliminarproductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gestionProductos.eliminarProductoPorIdSwing();
                gestionProductos.guardarDatos();
            }
        });

        modificarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionProductos.modificarProductoSwing();
                gestionProductos.guardarDatos();
            }
        });

        listarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            gestionProductos.listrarProductosSwing(frame,scrollPane);
            }
        });

        modificarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gestionAdministrador.modificacionUsuarioSwing(gestionUsuarios);
                gestionUsuarios.guardarDatos();

            }
        });

        listaUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionAdministrador.mostrarUsuariosSwing(gestionUsuarios);
            }
        });
        menuClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCliente menuCliente = new MenuCliente(gestionUsuarios,gestionAdministrador,gestionProductos);
                menuCliente.menuCliente(usuario);
            }
        });
    }
}
