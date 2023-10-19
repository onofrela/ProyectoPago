package test.window.strategy;

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
        // Solicitar el monto primero
        String montoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el monto en efectivo:");
        String efectivoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el efectivo que el cliente pagó:");
        
        try {
            double monto = Double.parseDouble(montoStr);
            double efectivo = Double.parseDouble(efectivoStr);
            
            if (monto < 0 || efectivo < 0) {
                JOptionPane.showMessageDialog(this.frame, "Monto y efectivo deben ser valores positivos.");
            } else if (efectivo < monto) {
                JOptionPane.showMessageDialog(this.frame, "Pago no realizado. Efectivo insuficiente.");
            } else {
                Efectivo estrategiaEfectivo = new Efectivo(efectivo);
                this.contextoDePago = new ContextoDePago(estrategiaEfectivo);

                this.contextoDePago.ejecutarPago(monto);
                double cambio = efectivo - monto;
                JOptionPane.showMessageDialog(this.frame, "Pago realizado en efectivo. Cambio: $" + cambio);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese valores numéricos válidos.");
        }
    }
}
