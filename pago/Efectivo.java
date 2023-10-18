package pago;

public class Efectivo implements EstrategiaDePago {
    private double monto;
    private double efectivoPagado;

    public Efectivo(double monto, double efectivoPagado) {
        this.monto = monto;
        this.efectivoPagado = efectivoPagado;
    }

    @Override
    public void realizarPago(double monto) {
        if (efectivoPagado >= monto) {
            // Realiza el pago y calcula el cambio
            double cambio = efectivoPagado - monto;
            System.out.println("Pago realizado en efectivo. Cambio: $" + cambio);
        } else {
            System.out.println("La cantidad de efectivo pagada es insuficiente.");
        }
    }
}
