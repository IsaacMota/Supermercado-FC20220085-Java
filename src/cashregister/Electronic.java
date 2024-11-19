package cashregister;

public class Electronic extends Product {
    private int warranty; // garantia em meses
    private int quantity;

    public Electronic(String code, String name, double price, int warranty, int quantity) {
        super(code, name, price);
        this.warranty = warranty;
        this.quantity = quantity;
    }

    public int getWarranty() {
        return warranty;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return super.toString() + ", Warranty: " + warranty + " months, Quantity: " + quantity;
    }
}