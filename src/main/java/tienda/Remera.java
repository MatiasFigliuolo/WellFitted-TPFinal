package tienda;

import enums.TipoRemera;
import org.json.JSONObject;

import java.io.Serializable;

public class Remera extends ProductoSup  {

    private TipoRemera tipoRemera;
    public Remera(String nombre, String id, int stock, Number precio, float talla, TipoRemera tipoRemera) {
        super(nombre, id, stock, precio, talla);
        this.tipoRemera = tipoRemera;
    }

    public TipoRemera getTipoRemera() {
        return tipoRemera;
    }

    public void setTipoRemera(TipoRemera tipoRemera) {
        this.tipoRemera = tipoRemera;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Tipo: " + this.tipoRemera + "| ");
    }

    public JSONObject toJson()
    {
        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("talla", getTalla());
        productoJson.put("tipoRemera", getTipoRemera());
        return productoJson;
    }
}
