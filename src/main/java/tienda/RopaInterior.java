package tienda;

import enums.TallaLetra;
import enums.TipoRopaInterior;

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
}
