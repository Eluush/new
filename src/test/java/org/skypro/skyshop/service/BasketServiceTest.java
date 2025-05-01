package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exeption.NoSuchProductException;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.ProductBasket;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_WhenProductNotExists_ThrowsException() {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductToBasket(invalidId));
    }

    @Test
    void addProduct_WhenProductExists_CallsAddMethod() {

        UUID validId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    void getUserBasket_WhenBasketEmpty_ReturnsEmpty() {

        when(productBasket.getProducts()).thenReturn(Map.of());


        UserBasket result = basketService.getUserBasket();


        assertTrue(result.getItems().isEmpty());
        assertEquals(0.0, result.getTotal());
    }


    @Test
    void getUserBasket_WhenBasketHasItems_ReturnsCorrectData() {

        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = mock(Product.class);
        when(product1.getPrice()).thenReturn(200);

        Product product2 = mock(Product.class);
        when(product2.getPrice()).thenReturn(300);

        when(productBasket.getProducts())
                .thenReturn(Map.of(
                        productId1, 2,
                        productId2, 1
                ));

        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));


        UserBasket result = basketService.getUserBasket();


        assertEquals(2, result.getItems().size());
        assertEquals(700.0, result.getTotal());
    }
}