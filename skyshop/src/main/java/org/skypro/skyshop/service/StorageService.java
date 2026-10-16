package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.UUID;
import java.util.Optional;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initData();
    }

    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }

    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> allItems = new ArrayList<>();
        allItems.addAll(productStorage.values());
        allItems.addAll(articleStorage.values());
        return allItems;
    }
    public Optional<Product>getProductByld(UUID id){
        return Optional.ofNullable(productStorage.get(id));
    }

    private void initData() {

        UUID breadId = UUID.randomUUID();
        productStorage.put(breadId, new SimpleProduct(breadId, "Хлеб", 30));

        UUID milkId = UUID.randomUUID();
        productStorage.put(milkId, new DiscountedProduct(milkId, "Молоко", 100, 10));

        UUID penId = UUID.randomUUID();
        productStorage.put(penId, new FixPriceProduct(penId, "Ручка"));

        UUID appleID = UUID.randomUUID();
        productStorage.put(appleID, new SimpleProduct(appleID, "Яблоко", 50));

        UUID cheeseId = UUID.randomUUID();
        productStorage.put(cheeseId, new SimpleProduct(cheeseId, "Сыр", 200));

        UUID article1Id = UUID.randomUUID();
        articleStorage.put(article1Id, new Article(article1Id, "Как выбрать хлеб", "Хлеб бывает разный"));

        UUID article2Id = UUID.randomUUID();
        articleStorage.put(article2Id, new Article(article2Id, "Польза молока", "Молоко содержит кальций"));
    }
}
