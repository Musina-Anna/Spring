package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_NonExistentProduct_ThrowsException() {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void addProduct_ExistingProduct_AddToBasket() {
        UUID validId = UUID.randomUUID();
        Product product = new SimpleProduct(validId, "Хлеб", 30);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        basketService.addProduct(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    void getUserBasket_EmptyBasket_ReturnsEmptyUserBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    void getUserBasket_HasItems_ReturnsCorrectUserBasket() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "хлеб", 100);
        Map<UUID, Integer> basketMap = Map.of(id, 2);

        when(productBasket.getProducts()).thenReturn(basketMap);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).hasSize(1);
        assertThat(userBasket.getTotal()).isEqualTo(200);
    }

}
