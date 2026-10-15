package org.skypro.skyshop.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто.");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();
        products.removeIf(p -> {
            if (p.getName().equals(name)) {
                removed.add(p);
                return true;
            }
            return false;
        });
        return removed;
    }
}










