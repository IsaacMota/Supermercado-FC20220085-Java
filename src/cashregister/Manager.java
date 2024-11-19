package cashregister;

import java.util.Scanner;

public class Manager {
    private Inventory inventory = new Inventory();
    private CashRegister cashRegister;
    private Scanner scanner = new Scanner(System.in);

    public Manager(double initialCash) {
        cashRegister = new CashRegister(initialCash);
    }

    // Método para abrir o caixa e mostrar o total de vendas
    public void openCashRegister() {
        System.out.printf("Caixa aberto com total de vendas do último dia: %.2f\n", cashRegister.getCurrentCash());
    }

    // Método para abrir o menu de operações
    public void openMenu() {
        boolean inOperation = true;

        while (inOperation) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Registrar Produto");
            System.out.println("2. Remover Produto");
            System.out.println("3. Pesquisar Produto");
            System.out.println("4. Abrir Venda");
            System.out.println("5. Fechar Caixa");
            System.out.print("Digite uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (choice) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    openSale();
                    break;
                case 5:
                    if (confirmCloseCashRegister()) {
                        closeCashRegister();
                        inOperation = restartMenuOption(); // Pergunta ao usuário se deseja reiniciar o menu
                        if (inOperation) {
                            restartMenuRegister(); // Exibe mensagem de reabertura do caixa
                        }
                    } else {
                        System.out.println("Ação de fechar caixa cancelada.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Programa finalizado.");
    }

    // Registrar um novo produto com quantidade
    private void registerProduct() {
        System.out.print("Digite o tipo de produto (1 para Alimento/Utensílio, 2 para Eletrônico): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.print("Digite o código do produto: ");
        String code = scanner.nextLine();

        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Digite o preço por unidade: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consumir nova linha

        System.out.print("Digite a quantidade do produto: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Product product;
        if (type == 1) {
            System.out.print("Digite a validade em dias: ");
            int shelfLife = scanner.nextInt();
            product = new FoodUtensil(code, name, price, shelfLife, quantity);
        } else {
            System.out.print("Digite a garantia em meses: ");
            int warranty = scanner.nextInt();
            product = new Electronic(code, name, price, warranty, quantity);
        }

        inventory.addProduct(product);
        System.out.println("Produto adicionado com sucesso.");
    }

    // Remover um produto com confirmação
    private void removeProduct() {
        System.out.print("Digite o código do produto para remover: ");
        String code = scanner.nextLine();
        if (inventory.productExists(code)) {
            System.out.print("Você realmente deseja remover esse produto? (s/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("s")) {
                inventory.removeProduct(code);
                System.out.println("Produto removido com sucesso.");
            } else {
                System.out.println("Remoção do produto cancelada.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Pesquisar um produto
    private void searchProduct() {
        System.out.print("Digite o nome do produto para pesquisar: ");
        String name = scanner.nextLine();
        Product product = inventory.searchProductByName(name);
        if (product != null) {
            System.out.println("Produto encontrado: " + product);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Iniciar um processo de venda
    private void openSale() {
        Sale sale = new Sale();
        System.out.println("Digite o código do produto para adicionar à venda (ou 'feito' para finalizar): ");
        String code;
        while (true) {
            code = scanner.nextLine();
            if (code.equalsIgnoreCase("feito")) {
                break;
            }

            Product product = inventory.getProductByCode(code);
            if (product != null) {
                sale.addProduct(product);
                System.out.println("Produto adicionado: " + product);
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        closeSale(sale); // Fechar a venda após a adição
    }

    // Fechar a venda
    private void closeSale(Sale sale) {
        double totalAmount = sale.getTotalAmount();
        System.out.printf("Venda fechada. Total: %.2f\n", totalAmount);
        cashRegister.addToCash(totalAmount);
        sale.finalizeSale();
    }

    // Método para confirmar antes de fechar o caixa
    private boolean confirmCloseCashRegister() {
        System.out.print("Você realmente deseja fechar o caixa? (s/n): ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("s");
    }

    // Fechar o caixa e mostrar o valor total
    private void closeCashRegister() {
        System.out.printf("Caixa fechado com total: %.2f\n", cashRegister.getCurrentCash());
    }

    // Pergunta ao usuário se deseja reiniciar o menu
    private boolean restartMenuOption() {
        System.out.print("Deseja abrir o caixa? (1 para abrir o caixa): ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        return option == 1; // Retorna true para continuar ou false para encerrar
    }
    
    // Exibe mensagem de reinício do caixa com o valor total atual
    private void restartMenuRegister() {
        System.out.printf("Iniciando caixa com total: %.2f\n", cashRegister.getCurrentCash());
    }
}
