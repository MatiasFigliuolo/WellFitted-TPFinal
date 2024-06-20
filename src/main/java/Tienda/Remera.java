package Tienda;

import Enums.TipoRemera;

public class Remera extends ProductoSup{

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
        return super.toString()+ "tipo= "+tipoRemera+ '\'';
    }
}