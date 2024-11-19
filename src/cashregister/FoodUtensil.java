package cashregister;

public class FoodUtensil extends Product {
    private int shelfLife; // validade em dias
    private int quantity;

    public FoodUtensil(String code, String name, double price, int shelfLife, int quantity) {
        super(code, name, price);
        this.shelfLife = shelfLife;
        this.quantity = quantity;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return super.toString() + ", Shelf Life: " + shelfLife + " days, Quantity: " + quantity;
    }
}