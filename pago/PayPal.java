package pago;

public class PayPal implements EstrategiaDePago {
    private String correo;

    public PayPal(String correo) {
        this.correo = correo;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago realizado con PayPal. Correo: " + correo);
    }

    public String getCorreo() {
        return correo;
    }
}
