package tienda;

import enums.TallaLetra;

public class ProductoInf extends Producto{

    private TallaLetra tallaLetra;
    public ProductoInf(String nombre, String id, int stock, Number precio, TallaLetra tallaLetra) {
        super(nombre, id, stock, precio);
        this.tallaLetra = tallaLetra;
    }

    public TallaLetra getTallaLetra() {
        return tallaLetra;
    }

    public void setTallaLetra(TallaLetra tallaLetra) {
        this.tallaLetra = tallaLetra;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|Talla: " + this.tallaLetra + "| ");
    }
}
