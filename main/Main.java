package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.*;
import strategy.*;

public class Main {

    private static JButton crearBoton(String texto, String rutaImagen) {
        //Crea el botón con el texto indicado y le añade formato
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Calibri", Font.PLAIN, 16));

        try {
            // Carga la imagen del botón
            BufferedImage imagenIcono = ImageIO.read(Main.class.getResourceAsStream(rutaImagen));
            ImageIcon icono = new ImageIcon(imagenIcono.getScaledInstance(75, 75, Image.SCALE_SMOOTH));
            boton.setIcon(icono);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Posiciona los elementos al centro y el texto abajo
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);

        // Personaliza el botón
        boton.setBackground(Color.WHITE);
        boton.setBorder(new LineBorder(Color.BLACK, 3, true));

        return boton;
    }

    public static void main(String[] args) {

        //Crea la ventana de Java con ciertas características
        JFrame ventana = new JFrame("Proceso de pago");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(720, 480);
        ventana.setLocationRelativeTo(null);

        try {
            // Establece el ícono de la ventana
            BufferedImage imagenIcono = ImageIO.read(Main.class.getResourceAsStream("/img/icono.png"));
            ventana.setIconImage(imagenIcono);
        } catch (IOException e) {
            // Si hay error de lectura, imprime ese mensaje
            e.printStackTrace();
        }

        //Origina el panel de contenido y establece un layout de grupo
        JPanel panelContenido = new JPanel();
        GroupLayout layout = new GroupLayout(panelContenido);
        panelContenido.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //Añade un texto
        JLabel etiqueta = new JLabel("Elige el método de pago");
        etiqueta.setFont(new Font("Calibri", Font.BOLD, 32));
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(etiqueta);

        //Crea el panel horizontal de botones
        JPanel botones = new JPanel(new GridLayout(1, 0, 10, 0)); // 1 fila, 0 columnas, espacio horizontal de 10 píxeles

        //Crea y añade los botones al panel de botones
        JButton botonEfectivo = crearBoton("Efectivo", "/img/efectivo.png");
        JButton botonPayPal = crearBoton("PayPal", "/img/paypal.png");
        JButton botonTarjetaCredito = crearBoton("Tarjeta de Crédito", "/img/tarjeta.png");

        botones.add(botonEfectivo);
        botones.add(botonPayPal);
        botones.add(botonTarjetaCredito);

        //Añade un margen interno de 50px
        panelContenido.setBorder(new EmptyBorder(50, 50, 50, 50));

        //Establece los grupos que estarán dentro del Layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(etiqueta)
                .addComponent(botones)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(etiqueta)
                .addComponent(botones)
        );

        //Añade el panel de contenido a la ventana y hace que la ventana muestre todo el contenido (que aparezca en la pantalla)
        ventana.setContentPane(panelContenido);
        ventana.setVisible(true);

        // Asigna oyentes (receptores de la acción) a los botones para activar las estrategias de pago correspondientes
        botonEfectivo.addActionListener(new EstrategiaEfectivo(ventana));
        botonPayPal.addActionListener(new EstrategiaPayPal(ventana));
        botonTarjetaCredito.addActionListener(new EstrategiaTarjetaCredito(ventana));
    }
}