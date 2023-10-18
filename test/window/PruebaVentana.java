package test.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Elige el método para pagar");
        label.setFont(new Font("Calibri", Font.PLAIN, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(label, gbc);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newSize = Math.max(16, Math.min(48, e.getComponent().getWidth() / 20));
                label.setFont(new Font(label.getFont().getFamily(), label.getFont().getStyle(), newSize));
            }
        });

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(subPanel, gbc);

        JButton buttonEfectivo = crearBoton("Efectivo", "/test/window/img/efectivo.png");
        JButton buttonPayPal = crearBoton("PayPal", "/test/window/img/paypal.png");
        JButton buttonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/test/window/img/tarjeta.png");

        // Configura el tamaño preferido y máximo para los botones
        Dimension buttonSize = new Dimension(400, 200);
        buttonEfectivo.setPreferredSize(buttonSize);
        buttonPayPal.setPreferredSize(buttonSize);
        buttonTarjetaCredito.setPreferredSize(buttonSize);
        buttonEfectivo.setMaximumSize(buttonSize);
        buttonPayPal.setMaximumSize(buttonSize);
        buttonTarjetaCredito.setMaximumSize(buttonSize);

        subPanel.add(buttonEfectivo);
        subPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        subPanel.add(buttonPayPal);
        subPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        subPanel.add(buttonTarjetaCredito);

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(panel);
        contentPane.add(Box.createVerticalGlue());

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
}