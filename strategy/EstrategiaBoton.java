package strategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Esta interfaz define el contrato para las estrategias que se aplicarán cuando se haga clic en un botón.
// Implementa la interfaz ActionListener para manejar eventos de acción de botón.
public interface EstrategiaBoton extends ActionListener {
    
    // Este método se llamará cuando se realice una acción en un botón.
    // La implementación de esta interfaz debe proporcionar la lógica específica de la estrategia a aplicar.
    void actionPerformed(ActionEvent evento);
}