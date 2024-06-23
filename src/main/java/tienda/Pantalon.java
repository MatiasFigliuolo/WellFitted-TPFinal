package tienda;

import enums.TallaLetra;
import enums.TipoPantalon;

import java.io.Serializable;

public class Pantalon  extends ProductoInf implements Serializable {
    private static final long serialVersionUID = 1L;
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
        return super.toString()+ "tipoPantalon= "+ tipoPantalon+ '\'';
    }
}
