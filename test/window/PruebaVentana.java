package test.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PruebaVentana {
    
    private static JButton crearBoton(String text, String imagePath) {
        JButton button = new JButton(text);

        try {
            BufferedImage iconImage = ImageIO.read(PruebaVentana.class.getResourceAsStream(imagePath));
            ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            button.setIcon(icon);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return button;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Proceso de pago");

        try {
            // Cargar una imagen para el ícono
            BufferedImage iconImage = ImageIO.read(PruebaVentana.class.getResourceAsStream("/test/window/img/icono.png")); // Reemplaza "icono.png" con la ubicación de tu imagen
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);


        JPanel panel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel("Elige el método para pagar");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        JButton buttonEfectivo = crearBoton("Efectivo", "/test/window/img/efectivo.png");
        JButton buttonPayPal = crearBoton("PayPal", "/test/window/img/paypal.png");
        JButton buttonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/test/window/img/tarjeta.png");
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonEfectivo, gbc);

        gbc.gridx = 1;
        panel.add(buttonPayPal, gbc);

        gbc.gridx = 2;
        panel.add(buttonTarjetaCredito, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
