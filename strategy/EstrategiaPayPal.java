package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;

public class EstrategiaPayPal implements EstrategiaBoton {

    private JFrame ventana;
    private Solicitador solicitador;

    public EstrategiaPayPal(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String correoPayPal;
        double monto = this.solicitador.solicitarMonto();
        if (monto == -1)
            return; // El usuario canceló

        correoPayPal = this.solicitador.solicitarCorreoPayPal();
        if (correoPayPal == null)
            return; // El usuario canceló

        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con PayPal con el correo " + correoPayPal);
    }
}
