package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import pago.ContextoDePago;
import pago.Efectivo;

public class strategyEfectivo implements EstrategiaDeBoton {
    private JFrame frame;
    private ContextoDePago contextoDePago;

    public strategyEfectivo(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double monto = 0;
        double efectivo = 0;

        while (true) {
            String montoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el monto en efectivo:");
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
                JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese un valor numérico válido.");
            }
        }

        while (true) {
            String efectivoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el efectivo que el cliente pagó:");
            if (efectivoStr == null) { // El usuario cerró el cuadro de diálogo
                return; // Salir si el usuario cancela
            }

            try {
                efectivo = Double.parseDouble(efectivoStr);
                if (efectivo < 0) {
                    JOptionPane.showMessageDialog(this.frame, "El efectivo debe ser un valor positivo.");
                } else {
                    break; // Salir del bucle si el efectivo es válido
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese un valor numérico válido.");
            }
        }

        if (efectivo < monto) {
            JOptionPane.showMessageDialog(this.frame, "Pago no realizado. Efectivo insuficiente.");
        } else {
            Efectivo estrategiaEfectivo = new Efectivo(efectivo);
            this.contextoDePago = new ContextoDePago(estrategiaEfectivo);

            this.contextoDePago.ejecutarPago(monto);
            double cambio = efectivo - monto;
            JOptionPane.showMessageDialog(this.frame, "Pago realizado en efectivo. Cambio: $" + cambio);
        }
    }
}
