package strategy;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import main.Verificador;

public class EstrategiaTarjetaCredito implements EstrategiaBoton {

    //Requiere de la ventana para hacer aparecer los botones
    private JFrame ventana;

    public EstrategiaTarjetaCredito(JFrame ventana){
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String numeroTarjeta;
        String fechaExpiracion;
        double monto = 0;

        // Solicita al usuario ingresar el número de tarjeta de crédito
        while (true) {
            numeroTarjeta = JOptionPane.showInputDialog(this.ventana, "Ingrese el número de tarjeta de crédito:");
            if (numeroTarjeta == null)
                // El usuario cerró el cuadro de diálogo, así que salimos de la estrategia
                return;

            if (!Verificador.esNumeroTarjetaCreditoValido(numeroTarjeta))
                JOptionPane.showMessageDialog(this.ventana, "Número de tarjeta de crédito no válido. Debe tener 16 dígitos numéricos.");
            else 
                break; // Salimos del bucle si el número de tarjeta es válido
        }

        // Solicita al usuario ingresar la fecha de vencimiento en formato MM/YY
        while (true) {
            fechaExpiracion = JOptionPane.showInputDialog(this.ventana, "Ingrese la fecha de vencimiento (MM/YY):");
            if (fechaExpiracion == null)
                // El usuario cerró el cuadro de diálogo, así que salimos de la estrategia
                return;

            if (!Verificador.esFechaVencimientoValida(fechaExpiracion))
                JOptionPane.showMessageDialog(this.ventana, "Fecha de vencimiento no válida. Debe estar en el formato MM/YY y el mes debe estar en el rango 1 a 12.");
            else if (Verificador.estaVencida(fechaExpiracion))
                JOptionPane.showMessageDialog(this.ventana, "La tarjeta de crédito ha vencido.");
            else
                break; // Salimos del bucle si la fecha de vencimiento es válida
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

        // Muestra un mensaje de pago exitoso con el monto y el número de tarjeta de crédito
        JOptionPane.showMessageDialog(this.ventana, "Pago de $" + monto + " realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
    }
}
