package tienda;

import enums.TallaLetra;
import enums.TipoBermuda;
import org.json.JSONObject;

public class Bermuda extends ProductoInf{


    private TipoBermuda tipoBermuda;
    public Bermuda(String nombre, String id, int stock, Number precio, TallaLetra tallaLetra,TipoBermuda tipoBermuda) {
        super(nombre, id, stock, precio, tallaLetra);
        this.tipoBermuda = tipoBermuda;
    }

    public TipoBermuda getTipoBermuda() {
        return tipoBermuda;
    }

    public void setTipoBermuda(TipoBermuda tipoBermuda) {
        this.tipoBermuda = tipoBermuda;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Tipo: " + this.tipoBermuda + "| ");
    }

    @Override
    public JSONObject toJson() {

        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("tallaLetra", getTallaLetra());
        productoJson.put("tipoBermuda", getTipoBermuda());
        return productoJson;
    }
}
