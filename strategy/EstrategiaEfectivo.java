package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;

public class EstrategiaEfectivo implements EstrategiaBoton {

    private JFrame ventana;
    private Solicitador solicitador;

    public EstrategiaEfectivo(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        double monto = this.solicitador.solicitarMonto();
        if (monto == -1) 
            return; // El usuario canceló

        double efectivo = this.solicitador.solicitarEfectivo();
        if (efectivo == -1) 
            return; // El usuario canceló

        if (efectivo < monto)
            JOptionPane.showMessageDialog(this.ventana, "Pago no realizado. Efectivo insuficiente.");
        else {
            double cambio = efectivo - monto;
            JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado en efectivo. Cambio: $" + cambio);
        }
    }
}