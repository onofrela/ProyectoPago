package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;
import verify.Verificador;

public class EstrategiaTarjetaCredito implements EstrategiaBoton {

    private JFrame ventana;
    private Solicitador solicitador;

    public EstrategiaTarjetaCredito(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String numeroTarjeta;
        String fechaExpiracion;
        double monto = this.solicitador.solicitarMonto();
        if (monto == -1)
            return; // El usuario canceló

        numeroTarjeta = this.solicitador.solicitarNumeroTarjeta();
        if (numeroTarjeta == null)
            return; // El usuario canceló

        fechaExpiracion = this.solicitador.solicitarFechaExpiracion();
        if (fechaExpiracion == null)
            return; // El usuario canceló

        if (Verificador.estaVencida(fechaExpiracion))
            return; // La tarjeta de crédito ha vencido

        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
    }
}
