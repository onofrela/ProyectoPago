package test.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import pago.*;

public class PruebaVentana {

    private static JButton crearBoton(String text, String imagePath) {
        JButton button = new JButton(text);

        try {
            BufferedImage iconImage = ImageIO.read(PruebaVentana.class.getResourceAsStream(imagePath));
            ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        // Personaliza el botón
        button.setBackground(Color.WHITE);
        button.setBorder(new LineBorder(Color.BLACK, 3, true));

        return button;
    }

    // Función para validar el formato de correo electrónico
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Función para validar el número de tarjeta de crédito
    private static boolean isValidCreditCardNumber(String number) {
        String creditCardRegex = "\\d{16}";
        return number.matches(creditCardRegex);
    }

    // Función para validar el formato de la fecha de vencimiento (MM/YY)
    private static boolean isValidExpirationDate(String date) {
        String expirationDateRegex = "\\d{2}/\\d{2}";
        return date.matches(expirationDateRegex);
    }

    // Función para verificar si la tarjeta de crédito ha vencido
    private static boolean isExpired(String expirationDate) {
        try {
            // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();

            String expirationDateNew = "01/" + expirationDate;

            // Parsear la fecha de vencimiento
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate expiration = LocalDate.parse(expirationDateNew, formatter);
            expiration.plusMonths(1);

            // Comparar la fecha de vencimiento con la fecha actual
            return expiration.isBefore(currentDate);
        } catch (Exception e) {
            System.out.println("Error");
            // Manejo de errores en el formato de la fecha de vencimiento
            return true; // Consideramos que la tarjeta está vencida en caso de error
        }
    }

    public static void main(String[] args) {
        UIManager.put("Button.background", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.border", new LineBorder(Color.BLACK, 3, true));

        JFrame frame = new JFrame("Proceso de pago");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        try {
            BufferedImage iconImage = ImageIO.read(PruebaVentana.class.getResourceAsStream("/test/window/img/icono.png"));
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel contentPane = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Elige el método para pagar");
        label.setFont(new Font("Calibri", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.NORTH);

        JPanel subPanel = new JPanel(new GridLayout(1, 0, 10, 0));

        JButton buttonEfectivo = crearBoton("Efectivo", "/test/window/img/efectivo.png");
        JButton buttonPayPal = crearBoton("PayPal", "/test/window/img/paypal.png");
        JButton buttonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/test/window/img/tarjeta.png");

        subPanel.add(buttonEfectivo);
        subPanel.add(buttonPayPal);
        subPanel.add(buttonTarjetaCredito);

        contentPane.add(subPanel, BorderLayout.CENTER);

        frame.setContentPane(contentPane);
        frame.setVisible(true);

        // Contexto de pago
        ContextoDePago contextoDePago = new ContextoDePago(null);

        buttonEfectivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar el monto primero
                String montoStr = JOptionPane.showInputDialog(frame, "Ingrese el monto en efectivo:");
                String efectivoStr = JOptionPane.showInputDialog(frame, "Ingrese el efectivo que el cliente pagó:");
                
                try {
                    double monto = Double.parseDouble(montoStr);
                    double efectivo = Double.parseDouble(efectivoStr);
                    
                    if (monto < 0 || efectivo < 0) {
                        JOptionPane.showMessageDialog(frame, "Monto y efectivo deben ser valores positivos.");
                    } else if (efectivo < monto) {
                        JOptionPane.showMessageDialog(frame, "Pago no realizado. Efectivo insuficiente.");
                    } else {
                        Efectivo estrategiaEfectivo = new Efectivo(monto, efectivo);
                        contextoDePago.setStrategy(estrategiaEfectivo);
        
                        contextoDePago.ejecutarPago(monto);
                        double cambio = efectivo - monto;
                        JOptionPane.showMessageDialog(frame, "Pago realizado en efectivo. Cambio: $" + cambio);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores numéricos válidos.");
                }
            }
        });        

        buttonPayPal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar el correo de PayPal
                String correoPayPal = JOptionPane.showInputDialog(frame, "Ingrese su correo de PayPal:");
                String montoStr = JOptionPane.showInputDialog(frame, "Ingrese el monto a pagar:");
        
                try {
                    double monto = Double.parseDouble(montoStr);
        
                    if (!isValidEmail(correoPayPal)) {
                        JOptionPane.showMessageDialog(frame, "Correo de PayPal no válido. Debe tener un formato de correo electrónico válido.");
                    } else {
                        PayPal estrategiaPayPal = new PayPal(correoPayPal);
                        contextoDePago.setStrategy(estrategiaPayPal);
        
                        contextoDePago.ejecutarPago(monto);
                        JOptionPane.showMessageDialog(frame, "Pago realizado con PayPal");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un valor numérico válido para el monto.");
                }
            }
        });
    
        buttonTarjetaCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar los datos de la tarjeta de crédito
                String numeroTarjeta = JOptionPane.showInputDialog(frame, "Ingrese el número de tarjeta de crédito:");
                String fechaExpiracion = JOptionPane.showInputDialog(frame, "Ingrese la fecha de vencimiento (MM/YY):");
                String montoStr = JOptionPane.showInputDialog(frame, "Ingrese el monto a pagar:");
        
                try {
                    double monto = Double.parseDouble(montoStr);
        
                    if (!isValidCreditCardNumber(numeroTarjeta)) {
                        JOptionPane.showMessageDialog(frame, "Número de tarjeta de crédito no válido. Debe tener 16 dígitos numéricos.");
                    } else if (!isValidExpirationDate(fechaExpiracion)) {
                        JOptionPane.showMessageDialog(frame, "Fecha de vencimiento no válida. Debe estar en el formato MM/YY.");
                    } else if (isExpired(fechaExpiracion)) {
                        JOptionPane.showMessageDialog(frame, "La tarjeta de crédito ha vencido.");
                    } else {
                        TarjetaCredito estrategiaTarjeta = new TarjetaCredito(numeroTarjeta, fechaExpiracion);
                        contextoDePago.setStrategy(estrategiaTarjeta);
        
                        contextoDePago.ejecutarPago(monto);
                        JOptionPane.showMessageDialog(frame, "Pago realizado con tarjeta de crédito. Número de Tarjeta: " + numeroTarjeta);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un valor numérico válido para el monto.");
                }
            }
        });
    }
}
