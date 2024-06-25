package tienda;

import org.json.JSONObject;

public class Buzo extends ProductoSup {

    private boolean capucha;


    public Buzo(String nombre, String id, int stock, Number precio, float talla, boolean capucha) {
        super(nombre, id, stock, precio, talla);
        this.capucha = capucha;
    }

    public boolean isCapucha() {
        return capucha;
    }

    public void setCapucha(boolean capucha) {
        this.capucha = capucha;
    }

    public String conSinCapucha(){

        if(this.capucha){

            return "si";
        }
        else{
            return "no";
        }

    }
    @Override
    public String toString() {
        return String.format(super.toString() + "|Capucha: " + conSinCapucha() + "| ");
    }

    public JSONObject toJson()
    {
        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("talla", getTalla());
        productoJson.put("capucha", isCapucha());
        return productoJson;
    }
}
