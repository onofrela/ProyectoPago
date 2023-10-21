package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;

public class EstrategiaEfectivo implements EstrategiaBoton {

    // Referencia a la ventana para mostrar mensajes
    private JFrame ventana;
    private Solicitador solicitador;

    // Constructor que recibe la ventana y crea una instancia del Solicitador
    public EstrategiaEfectivo(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        // Solicita al usuario ingresar el monto y verifica si se cancela
        double monto = this.solicitador.solicitarMonto();
        if (monto == -1) 
            return; // El usuario canceló

        // Solicita al usuario ingresar la cantidad de efectivo y verifica si se cancela
        double efectivo = this.solicitador.solicitarEfectivo();
        if (efectivo == -1) 
            return; // El usuario canceló

        // Comprueba si el efectivo es suficiente para cubrir el monto y muestra un mensaje correspondiente
        if (efectivo < monto)
            JOptionPane.showMessageDialog(this.ventana, "Pago no realizado. Efectivo insuficiente.");
        else {
            // Calcula el cambio y muestra un mensaje de pago exitoso
            double cambio = efectivo - monto;
            JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado en efectivo. Cambio: $" + cambio);
        }
    }
}