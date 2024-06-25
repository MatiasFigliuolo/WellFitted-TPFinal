package tienda;

public class Buzo extends ProductoSup {

    private boolean capucha;


    public Buzo(String nombre, String id, int stock, Number precio, float talla, boolean capucha) {
        super(nombre, id, stock, precio, talla);
        this.capucha = capucha;
    }

    public boolean isCapucha() {
        return capucha;
    }

    public void setCapucha(boolean capucha) {
        this.capucha = capucha;
    }

    public String conSinCapucha(){

        if(this.capucha){

            return "si";
        }
        else{
            return "no";
        }

    }
    @Override
    public String toString() {
        return String.format(super.toString() + "|Capucha: " + conSinCapucha() + "| ");
    }
}
