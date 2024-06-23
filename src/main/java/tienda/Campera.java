package tienda;

import enums.TipoCampera;

public class Campera extends ProductoSup{
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
        return super.toString()+ ", Tipo= "+tipoCampera+ '\'';
    }
}
