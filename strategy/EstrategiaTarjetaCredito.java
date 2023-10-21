package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import verify.Solicitador;
import verify.Verificador;

public class EstrategiaTarjetaCredito implements EstrategiaBoton {

    // Requiere de la ventana para hacer aparecer los botones
    private JFrame ventana;
    private Solicitador solicitador;

    // Constructor que recibe la ventana y crea una instancia del Solicitador
    public EstrategiaTarjetaCredito(JFrame ventana) {
        this.ventana = ventana;
        this.solicitador = new Solicitador(this.ventana);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String numeroTarjeta;
        String fechaExpiracion;
        // Solicita al usuario ingresar el monto y verifica si se cancela
        double monto = this.solicitador.solicitarMonto();
        if (monto == -1)
            return; // El usuario canceló

        // Solicita al usuario ingresar el número de tarjeta y verifica si se cancela
        numeroTarjeta = this.solicitador.solicitarNumeroTarjeta();
        if (numeroTarjeta == null)
            return; // El usuario canceló

        // Solicita al usuario ingresar la fecha de vencimiento y verifica si se cancela
        fechaExpiracion = this.solicitador.solicitarFechaExpiracion();
        if (fechaExpiracion == null)
            return; // El usuario canceló

        // Verifica si la tarjeta de crédito ha vencido antes de realizar el pago
        if (Verificador.estaVencida(fechaExpiracion))
            return; // La tarjeta de crédito ha vencido

        // Muestra un mensaje de pago exitoso con el monto y el número de tarjeta de crédito
        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
    }
}