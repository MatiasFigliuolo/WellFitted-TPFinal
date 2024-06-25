package tienda;

import enums.TallaLetra;
import enums.TipoRopaInterior;
import org.json.JSONObject;

public class RopaInterior extends ProductoInf
{
    private TipoRopaInterior tipoRopaInterior;
    public RopaInterior(String nombre, String id, int stock, Number precio, TallaLetra tallaLetra,TipoRopaInterior tipoRopaInterior) {
        super(nombre, id, stock, precio, tallaLetra);
        this.tipoRopaInterior = tipoRopaInterior;
    }

    public TipoRopaInterior getTipoRopaInterior() {
        return tipoRopaInterior;
    }

    public void setTipoRopaInterior(TipoRopaInterior tipoRopaInterior) {
        this.tipoRopaInterior = tipoRopaInterior;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Tipo: " + this.tipoRopaInterior + "| ");
    }

    @Override
    public JSONObject toJson() {

        JSONObject productoJson = new JSONObject();
        productoJson.put("nombre", getNombre());
        productoJson.put("id", getId());
        productoJson.put("stock", getStock());
        productoJson.put("precio", getPrecio());
        productoJson.put("tallaLetra", getTallaLetra());
        productoJson.put("tipoRopaInterior", getTipoRopaInterior());
        return productoJson;
    }
}
