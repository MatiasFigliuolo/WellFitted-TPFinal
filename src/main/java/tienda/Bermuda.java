package tienda;

import enums.TallaLetra;
import enums.TipoBermuda;

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
        return super.toString() + ", Tipo= "+tipoBermuda+ '\n';
    }
}
