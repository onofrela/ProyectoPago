package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import main.Verificador;

public class EstrategiaPayPal implements EstrategiaBoton {

    //Requiere de la ventana para hacer aparecer los botones
    private JFrame ventana;

    public EstrategiaPayPal(JFrame ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String correoPayPal;
        double monto = 0;

        // Solicita al usuario ingresar su correo de PayPal
        while (true) {
            correoPayPal = JOptionPane.showInputDialog(this.ventana, "Ingrese su correo de PayPal:");
            if (correoPayPal == null)
                // El usuario cerró el cuadro de diálogo, así que salimos de la estrategia
                return;

            if (!Verificador.esCorreoElectronicoValido(correoPayPal))
                JOptionPane.showMessageDialog(this.ventana, "Correo de PayPal no válido. Debe tener un formato de correo electrónico válido.");
            else 
                break; // Salimos del bucle si el correo es válido
        }

        // Solicita al usuario ingresar el monto a pagar
        while (true) {
            String montoStr = JOptionPane.showInputDialog(this.ventana, "Ingrese el monto a pagar:");
            if (montoStr == null)
                // El usuario cerró el cuadro de diálogo, así que salimos de la estrategia
                return;

            try {
                monto = Double.parseDouble(montoStr);
                if (monto < 0)
                    JOptionPane.showMessageDialog(this.ventana, "El monto debe ser un valor positivo.");
                else 
                    break; // Salimos del bucle si el monto es válido
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.ventana, "Por favor, ingrese un valor numérico válido para el monto.");
            }
        }

        // Muestra un mensaje de pago exitoso con el monto y el correo de PayPal
        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con PayPal con el correo " + correoPayPal);
    }
}