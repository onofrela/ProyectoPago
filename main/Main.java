package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import strategy.strategyEfectivo;
import strategy.strategyPayPal;
import strategy.strategyTarjetaCredito;

public class Main {

    private static JButton crearBoton(String text, String imagePath) {

        //Crea el botón
        JButton button = new JButton(text);

        //Ve si puede meter la imagen
        try {
            BufferedImage iconImage = ImageIO.read(Main.class.getResourceAsStream(imagePath));
            ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Alinea los elementos
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        // Personaliza el botón
        button.setBackground(new Color(230, 215, 255));
        button.setBorder(null);
        button.setPreferredSize(new Dimension(400, 400));
        //Regresa el botón
        return button;
    }
    public static void main(String[] args) {
        //Crea la ventana
        JFrame frame = new JFrame("Proceso de pago");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        //Verifica si puede meter el ícono
        try {
            BufferedImage iconImage = ImageIO.read(Main.class.getResourceAsStream("/img/icono.png"));
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();

        }
        
        // Crea el panel contenedor con GridBagLayout
        JPanel contentPane = new JPanel(new GridBagLayout());

        // Crea y añade el label
        JLabel label = new JLabel("Elige el método para pagar");
        label.setFont(new Font("Calibri", Font.BOLD, 32));

        // Configura las restricciones para el label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(label, gbc);

        JPanel subPanel = new JPanel(new GridBagLayout());

        // Botones
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(10, 10, 10, 10);
        gbcButton.fill = GridBagConstraints.HORIZONTAL; // Expansión horizontal

        JButton buttonEfectivo = crearBoton("Efectivo", "/img/efectivo.png");
        JButton buttonPayPal = crearBoton("PayPal", "/img/paypal.png");
        JButton buttonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/img/tarjeta.png");

        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        subPanel.add(buttonEfectivo, gbcButton);

        gbcButton.gridx = 1;
        subPanel.add(buttonPayPal, gbcButton);

        gbcButton.gridx = 2;
        subPanel.add(buttonTarjetaCredito, gbcButton);

        // Configura las restricciones para el botón
        gbc.gridy = 1; // Cambia la fila para el botón
        gbc.fill = GridBagConstraints.NONE; // Restablece el relleno
        gbc.anchor = GridBagConstraints.CENTER; // Alinea el botón en el centro

        contentPane.add(subPanel, gbc);

        frame.setContentPane(contentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonEfectivo.addActionListener(new strategyEfectivo(frame));
        buttonPayPal.addActionListener(new strategyPayPal(frame));
        buttonTarjetaCredito.addActionListener(new strategyTarjetaCredito(frame));
    }
}