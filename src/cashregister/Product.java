package cashregister;

public class Product {
    protected String code;
    protected String name;
    protected double pricePerUnit;

    public Product(String code, String name, double pricePerUnit) {
        this.code = code;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public String toString() {
        return String.format("Código: %s, Nome: %s, Preço: %.2f", code, name, pricePerUnit);
    }
}