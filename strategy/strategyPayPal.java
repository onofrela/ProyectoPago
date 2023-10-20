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
        // Solicitar el correo de PayPal
        String correoPayPal = JOptionPane.showInputDialog(this.frame, "Ingrese su correo de PayPal:");
        String montoStr = JOptionPane.showInputDialog(this.frame, "Ingrese el monto a pagar:");

        try {
            double monto = Double.parseDouble(montoStr);

            if (!Verificacion.isValidEmail(correoPayPal)) {
                JOptionPane.showMessageDialog(this.frame, "Correo de PayPal no válido. Debe tener un formato de correo electrónico válido.");
            } else {
                PayPal estrategiaPayPal = new PayPal(correoPayPal);
                this.contextoDePago = new ContextoDePago(estrategiaPayPal);

                this.contextoDePago.ejecutarPago(monto);
                JOptionPane.showMessageDialog(this.frame, "Pago realizado con PayPal");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Por favor, ingrese un valor numérico válido para el monto.");
        }
    }
}
