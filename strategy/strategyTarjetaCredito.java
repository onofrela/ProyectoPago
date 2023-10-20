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
        String numeroTarjeta;
        String fechaExpiracion;
        double monto = 0;

        while (true) {
            numeroTarjeta = JOptionPane.showInputDialog(this.frame, "Ingrese el número de tarjeta de crédito:");
            if (numeroTarjeta == null) { // El usuario cerró el cuadro de diálogo
                return; // Salir si el usuario cancela
            }

            if (!Verificacion.isValidCreditCardNumber(numeroTarjeta)) {
                JOptionPane.showMessageDialog(this.frame, "Número de tarjeta de crédito no válido. Debe tener 16 dígitos numéricos.");
            } else {
                break; // Salir del bucle si el número de tarjeta es válido
            }
        }

        while (true) {
            // Solicitar la fecha de vencimiento
            fechaExpiracion = JOptionPane.showInputDialog(this.frame, "Ingrese la fecha de vencimiento (MM/YY):");
            if (fechaExpiracion == null) { // El usuario cerró el cuadro de diálogo
                return; // Salir si el usuario cancela
            }
    
            if (!Verificacion.isValidExpirationDate(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.frame, "Fecha de vencimiento no válida. Debe estar en el formato MM/YY y el mes debe estar en el rango 1 a 12.");
            } else if (Verificacion.isExpired(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.frame, "La tarjeta de crédito ha vencido.");
            } else {
                break; // Salir del bucle si la fecha de vencimiento es válida
            }
        }

        while (true) {
            String montoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el monto a pagar:");
            if (montoStr == null) { // El usuario cerró el cuadro de diálogo
                return; // Salir si el usuario cancela
            }

            try {
                monto = Double.parseDouble(montoStr);
                if (monto < 0) {
                    JOptionPane.showMessageDialog(this.frame, "El monto debe ser un valor positivo.");
                } else {
                    break; // Salir del bucle si el monto es válido
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese un valor numérico válido para el monto.");
            }
        }

        TarjetaCredito estrategiaTarjeta = new TarjetaCredito(numeroTarjeta, fechaExpiracion);
        this.contextoDePago = new ContextoDePago(estrategiaTarjeta);

        this.contextoDePago.ejecutarPago(monto);
        JOptionPane.showMessageDialog(this.frame, "Pago realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
    }
}