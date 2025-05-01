package org.skypro.skyshop.service;

import org.skypro.skyshop.model.dto.BasketItem;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.ProductBasket;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.exeption.NoSuchProductException;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Product product = storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchProductException("Товар не найден"));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getProducts().entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found in storage"));
                    return new BasketItem(product, quantity);
                })
                .toList();

        return new UserBasket(basketItems);
    }
}
