package cashregister;

public class CashRegister {
    private double currentCash;

    public CashRegister(double initialCash) {
        this.currentCash = initialCash;
    }

    public void addToCash(double amount) {
        currentCash += amount;
    }

    public double getCurrentCash() {
        return currentCash;
    }

    public void closeRegister() {
        System.out.printf("Caixa fechado com total: %.2f\n", currentCash);
        currentCash = 0.0; // resetar para o próximo dia
    }
}