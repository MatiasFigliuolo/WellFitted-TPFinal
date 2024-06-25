package Factura;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class LeerJSON {
    public static Factura leerFacturaDesdeJSON(String archivo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = LeerJSON.class.getClassLoader().getResourceAsStream(archivo);

        if (inputStream == null) {
            throw new IOException("El archivo " + archivo + " no se encuentra.");
        }

        return mapper.readValue(inputStream, Factura.class);
    }
}