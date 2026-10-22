package org.skypro.skyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchProductException(id));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> products = productBasket.getProducts();

        List<BasketItem> items = products.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                    .orElseThrow(()->new NoSuchProductException(entry.getKey()));
                    return new BasketItem(product, entry.getValue());
                })
                .toList();
        return new UserBasket(items);
    }


}
