package test.window;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Verificacion {
    // Función para validar el formato de correo electrónico
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Función para validar el número de tarjeta de crédito
    public static boolean isValidCreditCardNumber(String number) {
        String creditCardRegex = "\\d{16}";
        return number.matches(creditCardRegex);
    }

    // Función para validar el formato de la fecha de vencimiento (MM/YY)
    public static boolean isValidExpirationDate(String date) {
        String expirationDateRegex = "\\d{2}/\\d{2}";
        return date.matches(expirationDateRegex);
    }

    // Función para verificar si la tarjeta de crédito ha vencido
    public static boolean isExpired(String expirationDate) {
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
}
