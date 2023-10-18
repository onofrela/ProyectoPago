package util;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

  static Scanner sc = new Scanner(System.in);

  public static int EscogerOpcion(int opciones){
    System.out.print("Opción: ");
    int opc = sc.nextInt();
    while(opc < 1 || opc > opciones){
      System.out.println("Opción inválida, vuelva a digitar una opción");
      opc = sc.nextInt();
    }
    sc.nextLine();
    return opc;
  }

  public static double DigitarMonto(){
    boolean salir = false;
    double monto = 0;
    while(!salir){
      try {
        System.out.print("Monto: $");
        monto = sc.nextDouble();
        if (monto < 0)
          System.out.println("Monto no válido, digite otro monto");
        else
          salir = true;
      } catch (Exception e) {
        System.out.println("Lo siento, hubo un error al digitar el monto, vuélvelo a intentar");
        sc.nextLine(); //Limpia el buffer
      }
    }
    return monto;
  }

  public static String DigitarNumeroTarjetaCredito() {
    boolean numeroValido = false;
    String numeroTarjeta = "";
    Pattern pattern = Pattern.compile("\\d{16}");  // Validación de 16 dígitos.
    while (!numeroValido) {
      System.out.print("Número de Tarjeta de Crédito: ");
      numeroTarjeta = sc.next();
      if (pattern.matcher(numeroTarjeta).matches()) {
        numeroValido = true;
      } else {
        System.out.println("Número de tarjeta no válido. Deben ser 16 dígitos.");
      }
    }
    return numeroTarjeta;
  }


  public static String DigitarFechaExpiracion() {
    boolean fechaValida = false;
    String fechaExpiracion = "";
    Pattern pattern = Pattern.compile("\\d{2}/\\d{2}");  // Validación del formato MM/YY.
    while (!fechaValida) {
      System.out.print("Fecha de Expiración (MM/YY): ");
      fechaExpiracion = sc.next();
      if (pattern.matcher(fechaExpiracion).matches()) {
        fechaValida = true;
      } else {
        System.out.println("Fecha de expiración no válida. Debe estar en el formato MM/YY.");
      }
    }
    return fechaExpiracion;
  }

  public static String DigitarCorreo() {
    boolean correoValido = false;
    String correo = "";
    Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");  // Validación de correo.
    while (!correoValido) {
      System.out.print("Correo Electrónico: ");
      correo = sc.next();
      if (pattern.matcher(correo).matches()) {
        correoValido = true;
      } else {
        System.out.println("Correo no válido. Debe contener un formato de correo electrónico válido.");
      }
    }
    return correo;
  }

} 