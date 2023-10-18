package pago;

public class Efectivo implements EstrategiaDePago  {
    private final double efectivo;
    
    public Efectivo(double efectivo){
        this.efectivo = efectivo;
    }
    
    public double getEfectivo(){
        return this.efectivo;
    }
    
    @Override
    public void realizarPago(double monto) {
        if (this.getEfectivo() < monto)
            System.out.println("No es suficiente efectivo para pagar el monto");
        System.out.println("Se realizó el pago de $" + monto + " en efectivo");
        System.out.println("Se pagó: $" + this.getEfectivo());
        System.out.println("El cambio fue de: $" + (this.getEfectivo() - monto));
    }
}
