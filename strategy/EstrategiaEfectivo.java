package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class EstrategiaEfectivo implements EstrategiaBoton {

    //Requiere de la ventana para hacer aparecer los botones
    private JFrame ventana;

    public EstrategiaEfectivo(JFrame ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        double monto = 0;
        double efectivo = 0;

        // Solicita al usuario ingresar el monto en efectivo
        while (true) {
            String montoStr = JOptionPane.showInputDialog(this.ventana, "Ingrese el monto en efectivo:");
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
                JOptionPane.showMessageDialog(this.ventana, "Por favor, ingrese un valor numérico válido.");
            }
        }

        // Solicita al usuario ingresar la cantidad de efectivo que el cliente pagó
        while (true) {
            String efectivoStr = JOptionPane.showInputDialog(this.ventana, "Ingrese el efectivo que el cliente pagó:");
            if (efectivoStr == null) 
                // El usuario cerró el cuadro de diálogo, así que salimos de la estrategia
                return;

            try {
                efectivo = Double.parseDouble(efectivoStr);
                if (efectivo < 0)
                    JOptionPane.showMessageDialog(this.ventana, "El efectivo debe ser un valor positivo.");
                else
                    break; // Salimos del bucle si el efectivo es válido
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.ventana, "Por favor, ingrese un valor numérico válido.");
            }
        }

        // Comprueba si el efectivo es suficiente para cubrir el monto
        if (efectivo < monto) 
            JOptionPane.showMessageDialog(this.ventana, "Pago no realizado. Efectivo insuficiente.");
        else {
            // Calcula el cambio y muestra un mensaje de pago exitoso
            double cambio = efectivo - monto;
            JOptionPane.showMessageDialog(this.ventana, "Pago realizado en efectivo. Cambio: $" + cambio);
        }
    }
}