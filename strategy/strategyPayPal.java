package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import pago.ContextoDePago;
import pago.PayPal;
import main.Verificacion;

public class strategyPayPal implements EstrategiaDeBoton {
    private JFrame frame;
    private ContextoDePago contextoDePago;

    public strategyPayPal(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String correoPayPal;
        double monto = 0;

        while (true) {
            correoPayPal = JOptionPane.showInputDialog(this.frame, "Ingrese su correo de PayPal:");
            if (correoPayPal == null) { // El usuario cerró el cuadro de diálogo
                return; // Salir si el usuario cancela
            }

            if (!Verificacion.isValidEmail(correoPayPal)) {
                JOptionPane.showMessageDialog(this.frame, "Correo de PayPal no válido. Debe tener un formato de correo electrónico válido.");
            } else {
                break; // Salir del bucle si el correo es válido
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

        PayPal estrategiaPayPal = new PayPal(correoPayPal);
        this.contextoDePago = new ContextoDePago(estrategiaPayPal);

        this.contextoDePago.ejecutarPago(monto);
        JOptionPane.showMessageDialog(this.frame, "Pago realizado con PayPal");
    }
}