package cashregister;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sale {
    private List<Product> soldProducts = new ArrayList<>();
    private double totalAmount = 0.0;

    public void addProduct(Product product) {
        soldProducts.add(product);
        totalAmount += product.getPricePerUnit();
    }

    public void removeProduct(String code) {
        Iterator<Product> iterator = soldProducts.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getCode().equals(code)) {
                iterator.remove();
                totalAmount -= p.getPricePerUnit();
                break;
            }
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void finalizeSale() {
        soldProducts.clear();
        totalAmount = 0.0;
    }
}

