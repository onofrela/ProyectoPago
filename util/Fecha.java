package util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fecha {

    private LocalDate fecha;

    public Fecha(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yy");
        try{
        this.fecha = LocalDate.parse(fecha, formato);
        }catch(DateTimeParseException e){
            System.out.println("No está en el formato correcto");
        }
    }

    public String toString(){
        return this.fecha.toString();
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}