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
        System.out.println("Pago realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
}
