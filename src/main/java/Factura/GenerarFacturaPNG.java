package Factura;

import tienda.Producto;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GenerarFacturaPNG {
    public GenerarFacturaPNG() {
    }

    public void generarImagen(Factura factura, String archivoSalida) throws IOException {
        int ancho = 600;
        int alto = 400 + factura.getProductos().size() * 30;
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();

        // Fondo blanco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ancho, alto);

        // Configuración de fuente
        Font fontTitulo = new Font("Arial", Font.BOLD, 20);
        Font font = new Font("Arial", Font.PLAIN, 14);
        Font fontPequena = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(Color.BLACK);

        // Dibujar el marco
        g.drawRect(5, 5, ancho - 10, alto - 10);

        // Encabezado de la factura
        g.setFont(fontTitulo);
        g.drawString("   WellFitted", ancho / 2 - 60, 40);

        g.setFont(font);
        int y = 80;
        g.drawString("FACTURA", ancho / 2 - 30, y);
        y += 30;
        g.drawLine(10, y, ancho - 10, y);
        y += 20;
        g.drawString("Fecha: " + factura.getFecha(), 10, y);
        g.drawString("Cliente: " + factura.getCliente(), ancho - 200, y);
        y += 30;
        g.drawLine(10, y, ancho - 10, y);
        y += 20;

        // Detalles de los productos
        g.drawString("Productos:", 10, y);
        y += 20;


        for (Producto producto : factura.getProductos()) {
            g.drawString(producto.getNombre(), 10, y);
            g.drawString("Precio: $" + producto.getPrecio(), 400, y);
            y += 20;
        }

        y += 10;
        g.drawLine(10, y, ancho - 10, y);
        y += 30;
        g.drawString("Total: $" + factura.getTotal(), 10, y);

        // Pie de página
        y += 40;
        g.drawLine(10, y, ancho - 10, y);
        y += 20;
        g.setFont(fontPequena);
        g.drawString("Gracias por su compra.", 10, y);

        g.dispose();
        ImageIO.write(imagen, "png", new File(archivoSalida));
    }
}