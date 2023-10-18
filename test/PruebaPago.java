package test;

import pago.*;
import util.Menu;

public class PruebaPago {
    public static void main(String[] args){


        System.out.println("=======================================");
        System.out.println("¿Cuál es el monto a pagar?");
        System.out.println("=======================================");
        double monto = Menu.DigitarMonto();
        System.out.println("=======================================");
        System.out.println("¿Cómo quiere realizar su pago?");
        System.out.println("=======================================");
        System.out.println("1. Con Efectivo");
        System.out.println("2. Con Tarjeta de Crédito");
        System.out.println("3. Con PayPal");
        System.out.println("=======================================");
        int opc = Menu.EscogerOpcion(3);
        System.out.println("=======================================");
        
        switch (opc) {
            case 1:
                System.out.println("Ingrese la cantidad de efectivo");
                double efectivoCantidad = Menu.DigitarMonto();
                EstrategiaDePago efectivo = new Efectivo(efectivoCantidad);
                ContextoDePago contextoEfectivo = new ContextoDePago(efectivo);
                contextoEfectivo.ejecutarPago(monto);
                break;
            case 2:
                System.out.print("Ingrese el número de tarjeta de crédito: ");
                String numeroTarjeta = Menu.DigitarNumeroTarjetaCredito();
                System.out.print("Ingrese la fecha de expiración (MM/YY): ");
                String fechaExpiracion = Menu.DigitarFechaExpiracion();
                EstrategiaDePago tarjetaCredito = new TarjetaCredito(numeroTarjeta, fechaExpiracion);
                ContextoDePago contextoTarjetaCredito = new ContextoDePago(tarjetaCredito);
                contextoTarjetaCredito.ejecutarPago(monto);
                break;
            case 3:
                System.out.print("Ingrese la cuenta de PayPal: ");
                String correoPayPal = Menu.DigitarCorreo();
                EstrategiaDePago payPal = new PayPal(correoPayPal);
                ContextoDePago contextoPayPal = new ContextoDePago(payPal);
                contextoPayPal.ejecutarPago(monto);
                break;
        }
    }
}
