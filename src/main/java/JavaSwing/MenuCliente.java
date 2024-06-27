package JavaSwing;


import tienda.*;
import usuarios.GestionAdministrador;
import usuarios.GestionUsuarios;
import usuarios.Perfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuCliente {

    private GestionUsuarios gestionUsuarios;
    private GestionAdministrador gestionAdministrador;
    private GestionProductos gestionProductos;
    private JFrame frame;
    private JScrollPane scrollPane;

    public MenuCliente(GestionUsuarios gestionUsuarios, GestionAdministrador gestionAdministrador, GestionProductos gestionProductos) {
        this.gestionUsuarios = gestionUsuarios;
        this.gestionAdministrador = gestionAdministrador;
        this.gestionProductos = gestionProductos;
    }

    public void menuCliente(Perfil usuario) {
        frame = new JFrame("--WEll FITTED--");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Menu Cliente");
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

        placeComponentsMenuCliente(panel, usuario);

        frame.setVisible(true);
    }

    public void placeComponentsMenuCliente(JPanel panel, Perfil usuario) {
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        JButton buscarProductoButton = new JButton("Buscar Producto");
        JButton sumarAlCarritoButton = new JButton("Sumar al Carrito");
        JButton quitarDelCarritoButton = new JButton("Quitar del Carrito");
        JButton mostrarCarritoButton = new JButton("Mostrar el Carrito");
        JButton listarProductosButton = new JButton("Mostrar Productos");
        JButton realizarCompraButton = new JButton("Realizar Compra");
        JButton mostrarHistorialButton = new JButton("Mostrar Historial");
        JButton menuAdminButton = new JButton("Menu Admin");

        buttonPanel.add(buscarProductoButton);
        buttonPanel.add(sumarAlCarritoButton);
        buttonPanel.add(quitarDelCarritoButton);
        buttonPanel.add(mostrarCarritoButton);
        buttonPanel.add(listarProductosButton);
        buttonPanel.add(realizarCompraButton);
        buttonPanel.add(mostrarHistorialButton);
        buttonPanel.add(menuAdminButton);

        panel.add(buttonPanel, BorderLayout.WEST);

        scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              gestionProductos.filtrarProductosSwing();
            }
        });
        sumarAlCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.agregarAlCarritoSwing(gestionProductos,frame);
                gestionUsuarios.guardarDatos();
            }

        });
        quitarDelCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.quitarDelCarritoSwing(scrollPane);
                gestionUsuarios.guardarDatos();
            }

        });
        mostrarCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.mostrarCarritoSwing(frame,scrollPane);
            }
        });
        listarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gestionProductos.listrarProductosSwing(frame,scrollPane);
            }
        });
        realizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              usuario.comprarProductoSwing();
              gestionUsuarios.guardarDatos();
            }
        });
        mostrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                usuario.mostrarHistorialSwing(frame);
            }
        });

        menuAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(usuario.getAdmin())
                {
                    MenuAdmin menuAdmin =new MenuAdmin(gestionUsuarios,gestionAdministrador,gestionProductos);
                    menuAdmin.menuAdmin(usuario);
                }else
                {
                    JOptionPane.showMessageDialog(frame, "! No tiene Permisos !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
