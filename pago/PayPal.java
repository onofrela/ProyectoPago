package pago;

public class PayPal implements EstrategiaDePago {
    private final String correoPayPal;

    public PayPal(String correoPayPal) {
        this.correoPayPal = correoPayPal;
    } 

    public String getCorreoPayPal() {
        return correoPayPal;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Se realizó el pago de $" + monto + " con la cuenta " + this.getCorreoPayPal());
    }
    
}
