package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import pago.ContextoDePago;
import pago.TarjetaCredito;
import main.Verificacion;

public class strategyTarjetaCredito implements EstrategiaDeBoton {
    private JFrame frame;
    private ContextoDePago contextoDePago;

    public strategyTarjetaCredito(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Solicitar los datos de la tarjeta de crédito
        String numeroTarjeta = JOptionPane.showInputDialog(this.frame, "Ingrese el número de tarjeta de crédito:");
        String fechaExpiracion = JOptionPane.showInputDialog(this.frame, "Ingrese la fecha de vencimiento (MM/YY):");
        String montoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el monto a pagar:");

        try {
            double monto = Double.parseDouble(montoStr);

            if (!Verificacion.isValidCreditCardNumber(numeroTarjeta)) {
                JOptionPane.showMessageDialog(this.frame, "Número de tarjeta de crédito no válido. Debe tener 16 dígitos numéricos.");
            } else if (!Verificacion.isValidExpirationDate(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.frame, "Fecha de vencimiento no válida. Debe estar en el formato MM/YY.");
            } else if (Verificacion.isExpired(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.frame, "La tarjeta de crédito ha vencido.");
            } else {
                TarjetaCredito estrategiaTarjeta = new TarjetaCredito(numeroTarjeta, fechaExpiracion);
                this.contextoDePago = new ContextoDePago(estrategiaTarjeta);

                this.contextoDePago.ejecutarPago(monto);
                JOptionPane.showMessageDialog(this.frame, "Pago realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese un valor numérico válido para el monto.");
        }
    }
}
