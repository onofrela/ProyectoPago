package pago;

public class TarjetaCredito implements EstrategiaDePago {
    private String numeroTarjeta;
    private String fechaExpiracion;

    public TarjetaCredito(String numeroTarjeta, String fechaExpiracion) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Se realizó el pago de $" + monto + " con tarjeta de crédito cuyo número es " + numeroTarjeta);
    }
}
