package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 150;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ":Фиксированная цена" + FIXED_PRICE + " руб.";
    }
}

