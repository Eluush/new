package org.skypro.skyshop.model.product;

import java.util.UUID;

public class NonFoodProduct extends Product {
    public NonFoodProduct(String name, String price, UUID id) {
        super(name, price, id);
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public boolean isSpesial() {
        return false;
    }
}