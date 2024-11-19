package cashregister;

public class Main {
    public static void main(String[] args) {
        // O caixa inicia com um valor pré-definido
        double initialCash = 100.0; // Total de vendas do dia anterior
        Manager manager = new Manager(initialCash);
        manager.openCashRegister(); // Abrir o caixa e mostrar o total de vendas do último dia
        manager.openMenu(); // Abrir o menu principal
    }
}