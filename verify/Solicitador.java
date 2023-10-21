package verify;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Solicitador {
    private JFrame ventana;

    public Solicitador(JFrame ventana) {
        this.ventana = ventana;
    }

    // Método para solicitar el monto al usuario
    public double solicitarMonto() {
        double monto = 0;
        while (true) {
            String montoStr = JOptionPane.showInputDialog(this.ventana, "Ingrese el monto:");
            if (montoStr == null) {
                // Si el usuario cierra el cuadro de diálogo, se sale del método
                return -1;
            }

            try {
                monto = Double.parseDouble(montoStr);
                if (monto < 0) {
                    JOptionPane.showMessageDialog(this.ventana, "El monto debe ser un valor positivo.");
                } else {
                    break; // Salimos si el monto es válido
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.ventana, "Por favor, ingrese un valor numérico válido.");
            }
        }
        return monto;
    }

    // Método para solicitar el efectivo al usuario
    public double solicitarEfectivo() {
        double efectivo = 0;

        while (true) {
            String efectivoStr = JOptionPane.showInputDialog(this.ventana, "Ingrese el efectivo que el cliente pagó:");
            if (efectivoStr == null) {
                // Si el usuario cierra el cuadro de diálogo, se sale del método
                return -1;
            }

            try {
                efectivo = Double.parseDouble(efectivoStr);
                if (efectivo < 0) {
                    JOptionPane.showMessageDialog(this.ventana, "El efectivo debe ser un valor positivo.");
                } else {
                    break; // Salimos si el efectivo es válido
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.ventana, "Por favor, ingrese un valor numérico válido.");
            }
        }
        return efectivo;
    }

    // Método para solicitar el correo de PayPal al usuario
    public String solicitarCorreoPayPal() {
        String correoPayPal;
        while (true) {
            correoPayPal = JOptionPane.showInputDialog(this.ventana, "Ingrese su correo de PayPal:");
            if (correoPayPal == null) {
                // Si el usuario cierra el cuadro de diálogo, se sale del método
                return null;
            }

            if (!Verificador.esCorreoElectronicoValido(correoPayPal)) {
                JOptionPane.showMessageDialog(this.ventana, "Correo de PayPal no válido. Debe tener un formato de correo electrónico válido.");
            } else {
                break; // Salimos si el correo es válido
            }
        }
        return correoPayPal;
    }

    // Método para solicitar el número de tarjeta de crédito al usuario
    public String solicitarNumeroTarjeta() {
        String numeroTarjeta;
        while (true) {
            numeroTarjeta = JOptionPane.showInputDialog(this.ventana, "Ingrese el número de tarjeta de crédito:");
            if (numeroTarjeta == null) {
                // Si el usuario cierra el cuadro de diálogo, se sale del método
                return null;
            }

            if (!Verificador.esNumeroTarjetaCreditoValido(numeroTarjeta)) {
                JOptionPane.showMessageDialog(this.ventana, "Número de tarjeta de crédito no válido. Debe tener 16 dígitos numéricos.");
            } else {
                break; // Salimos si el número de tarjeta es válido
            }
        }
        return numeroTarjeta;
    }

    // Método para solicitar la fecha de vencimiento al usuario
    public String solicitarFechaExpiracion() {
        String fechaExpiracion;
        while (true) {
            fechaExpiracion = JOptionPane.showInputDialog(this.ventana, "Ingrese la fecha de vencimiento (MM/YY):");
            if (fechaExpiracion == null) {
                // Si el usuario cierra el cuadro de diálogo, se sale del método
                return null;
            }

            if (!Verificador.esFechaVencimientoValida(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.ventana, "Fecha de vencimiento no válida. Debe estar en el formato MM/YY y el mes debe estar en el rango 1 a 12.");
            } else if (Verificador.estaVencida(fechaExpiracion)) {
                JOptionPane.showMessageDialog(this.ventana, "La tarjeta de crédito ha vencido.");
            } else {
                break; // Salimos si la fecha de vencimiento es válida
            }
        }
        return fechaExpiracion;
    }
}