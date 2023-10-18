package pago;

public class ContextoDePago {
    private EstrategiaDePago strategy;

    public ContextoDePago(EstrategiaDePago strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(EstrategiaDePago strategy) {
        this.strategy = strategy;
    }
    
    public void ejecutarPago(double monto) {
        if (strategy != null)
            strategy.realizarPago(monto);
        else
            System.out.println("No se ha especificado una estrategia de pago");
    }
}
