package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.*;
import strategy.strategyEfectivo;
import strategy.strategyPayPal;
import strategy.strategyTarjetaCredito;

public class Main{

    private static JButton crearBoton(String text, String imagePath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Calibri", Font.PLAIN, 16));

        try {
            BufferedImage iconImage = ImageIO.read(Main.class.getResourceAsStream(imagePath));
            ImageIcon icon = new ImageIcon(iconImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH));
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
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("Proceso de pago");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);

        try {
            BufferedImage iconImage = ImageIO.read(Main.class.getResourceAsStream("/img/icono.png"));
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel contentPane = new JPanel();
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JPanel botones = new JPanel(new GridLayout(1, 0, 10, 0)); // 1 fila, 0 columnas, espacio horizontal de 10 píxeles


        JLabel label = new JLabel("Elige el método para pagar");
        label.setFont(new Font("Calibri", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label);

        JButton buttonEfectivo = crearBoton("Efectivo", "/img/efectivo.png");
        JButton buttonPayPal = crearBoton("PayPal", "/img/paypal.png");
        JButton buttonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/img/tarjeta.png");

        botones.add(buttonEfectivo);
        botones.add(buttonPayPal);
        botones.add(buttonTarjetaCredito);

        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addComponent(botones)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(botones)
        );

        frame.setContentPane(contentPane);
        frame.setVisible(true);

        buttonEfectivo.addActionListener(new strategyEfectivo(frame));
        buttonPayPal.addActionListener(new strategyPayPal(frame));
        buttonTarjetaCredito.addActionListener(new strategyTarjetaCredito(frame));
    }
}