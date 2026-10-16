package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope

public class Basket {
    private final Map<UUID,Integer>products=new HashMap<>();

    public void addProduct(UUID id){
        products.computeIfAbsent(id,k->0);
        products.put(id,products.get(id)+1);
    }
    public Map<UUID,Integer>getProducts(){
        return Collections.unmodifiableMap(products);
    }
}
