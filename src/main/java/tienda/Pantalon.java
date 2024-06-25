package tienda;

import enums.TallaLetra;
import enums.TipoPantalon;
import org.json.JSONObject;

import java.io.Serializable;

public class Pantalon  extends ProductoInf{

    private TipoPantalon tipoPantalon;

    public Pantalon(String nombre, String id, int stock, Number precio, TallaLetra tallaLetra,TipoPantalon tipoPantalon) {
        super(nombre, id, stock, precio, tallaLetra);
        this.tipoPantalon = tipoPantalon;
    }

    public TipoPantalon getTipoPantalon() {
        return tipoPantalon;
    }

    public void setTipoPantalon(TipoPantalon tipoPantalon) {
        this.tipoPantalon = tipoPantalon;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Tipo: " + this.tipoPantalon + "| ");
    }

    @Override
    public JSONObject toJson() {

        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("tallaLetra", getTallaLetra());
        productoJson.put("tipoPantalon", getTipoPantalon());
        return productoJson;
    }
}
