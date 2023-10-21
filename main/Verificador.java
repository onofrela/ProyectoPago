package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Verificador {
    // Valida el formato de correo electrónico
    public static boolean esCorreoElectronicoValido(String correo) {
        String patronCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo.matches(patronCorreo);
    }

    // Valida el número de tarjeta de crédito
    public static boolean esNumeroTarjetaCreditoValido(String numero) {
        String patronNumeroTarjeta = "\\d{16}";
        return numero.matches(patronNumeroTarjeta);
    }

    // Valida el formato de la fecha de vencimiento (MM/YY)
    public static boolean esFechaVencimientoValida(String fecha) {
        String patronFechaVencimiento = "\\d{2}/\\d{2}";
        return fecha.matches(patronFechaVencimiento);
    }

    // Verifica si la tarjeta de crédito ha vencido
    public static boolean estaVencida(String fechaVencimiento) {
        try {
            // Obtiene la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Crea una fecha de vencimiento con el primer día del mes
            String fechaVencimientoNueva = "01/" + fechaVencimiento;

            // Parsea la fecha de vencimiento
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate fechaVencimientoParseada = LocalDate.parse(fechaVencimientoNueva, formateador);

            // Agrega un mes a la fecha de vencimiento para indicar todos los días del mes anterior
            fechaVencimientoParseada = fechaVencimientoParseada.plusMonths(1);

            // Compara la fecha de vencimiento con la fecha actual
            return fechaVencimientoParseada.isBefore(fechaActual);
        } catch (Exception e) {
            System.out.println("Error");
            // Manejo de errores en el formato de la fecha de vencimiento
            return true; // Consideramos que la tarjeta está vencida en caso de error
        }
    }
}