package Factura;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GenerarFacturaPNG {
    public static void generarImagen(Factura factura, String archivoSalida) throws IOException {
        int ancho = 800;
        int alto = 400 + factura.getProductos().size() * 20;
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();

        // Fondo blanco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ancho, alto);

        // Texto en negro
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.ITALIC, 20));

        int y = 20;
        g.drawString("Factura Número: " + factura.getNumero(), 10, y);
        y += 20;
        g.drawString("Fecha: " + factura.getFecha(), 10, y);
        y += 20;
        g.drawString("Cliente: " + factura.getCliente(), 10, y);
        y += 30;

        g.drawString("Productos:", 10, y);
        y += 20;

        for (Factura.Producto producto : factura.getProductos()) {
            g.drawString(producto.getNombre() + " x" + producto.getCantidad() + " $" + producto.getPrecio(), 10, y);
            y += 20;
        }

        y += 20;
        g.drawString("Total: $" + factura.getTotal(), 10, y);

        g.dispose();
        ImageIO.write(imagen, "png", new File(archivoSalida));
    }
}
