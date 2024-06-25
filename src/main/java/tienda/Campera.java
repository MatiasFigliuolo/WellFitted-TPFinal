package tienda;

import enums.TipoCampera;
import org.json.JSONObject;

public class Campera extends ProductoSup {
    private TipoCampera tipoCampera;

    public Campera(String nombre, String id, int stock, Number precio, float talla, TipoCampera tipoCampera) {
        super(nombre, id, stock, precio, talla);
        this.tipoCampera = tipoCampera;
    }

    public TipoCampera getTipoCampera() {
        return tipoCampera;
    }

    public void setTipoCampera(TipoCampera tipoCampera) {
        this.tipoCampera = tipoCampera;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Tipo: " + this.tipoCampera + "| ");
    }

    @Override
    public JSONObject toJson()
    {
        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("talla", getTalla());
        productoJson.put("tipoCampera", getTipoCampera());
        return productoJson;
    }

}
