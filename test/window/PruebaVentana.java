package test.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;
import test.window.strategy.strategyEfectivo;
import test.window.strategy.strategyPayPal;
import test.window.strategy.strategyTarjetaCredito;

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
    public static void main(String[] args) {

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

        buttonEfectivo.addActionListener(new strategyEfectivo(frame));
        buttonPayPal.addActionListener(new strategyPayPal(frame));
        buttonTarjetaCredito.addActionListener(new strategyTarjetaCredito(frame));
    }
}

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        JPanel principal = new JPanel();
        GroupLayout layout = new GroupLayout(principal);
        principal.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JPanel cards = new JPanel(new GridLayout(1, 0, 10, 0)); // 1 fila, 0 columnas, espacio horizontal de 10 píxeles
        JButton boton1 = new JButton("Haz click");
        JButton boton2 = new JButton("Haz click");
        cards.add(boton1);
        cards.add(boton2);
    
        JLabel texto = new JLabel("Ingrese el método de pago");

        principal.setBorder(new EmptyBorder(50, 50, 50, 50));

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(texto)
                .addComponent(cards)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(texto)
                .addComponent(cards)
        );

        frame.add(principal);
        frame.setVisible(true);
    }
}
