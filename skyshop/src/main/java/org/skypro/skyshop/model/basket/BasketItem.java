package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private final int quality;

    public BasketItem(Product product,int quality){
        this.product=product;
        this.quality=quality;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuality() {
        return quality;
    }
}
