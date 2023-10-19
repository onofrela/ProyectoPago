package pago;

public class Efectivo implements EstrategiaDePago {
    private double efectivoPagado;

    public Efectivo(double efectivoPagado) {
        this.efectivoPagado = efectivoPagado;
    }

    @Override
    public void realizarPago(double monto) {
        if (this.efectivoPagado >= monto) {
            // Realiza el pago y calcula el cambio
            double cambio = this.efectivoPagado - monto;
            System.out.println("Pago realizado en efectivo. Cambio: $" + cambio);
        } else {
            System.out.println("La cantidad de efectivo pagada es insuficiente.");
        }
    }
}
