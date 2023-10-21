package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;

public class EstrategiaPayPal implements EstrategiaBoton {

    // Referencia a la ventana para mostrar mensajes
    private JFrame ventana;
    private Solicitador solicitador;

    // Constructor que recibe la ventana y crea una instancia del Solicitador
    public EstrategiaPayPal(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String correoPayPal;
        // Solicita al usuario ingresar el monto y verifica si se cancela
        double monto = this.solicitador.solicitarMonto();
        if (monto == -1)
            return; // El usuario canceló

        // Solicita al usuario ingresar el correo de PayPal y verifica si se cancela
        correoPayPal = this.solicitador.solicitarCorreoPayPal();
        if (correoPayPal == null)
            return; // El usuario canceló

        // Muestra un mensaje de pago exitoso con el monto y el correo de PayPal
        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con PayPal con el correo " + correoPayPal);
    }
}