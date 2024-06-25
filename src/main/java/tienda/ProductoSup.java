package tienda;

import org.json.JSONObject;

public class ProductoSup extends Producto{

    private float talla;
    public ProductoSup(String nombre, String id, int stock, Number precio,float talla) {
        super(nombre, id, stock, precio);
        this.talla = talla;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
       return String.format(super.toString() + "|Talla: " + this.talla + "| ");
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
