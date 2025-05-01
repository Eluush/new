package org.skypro.skyshop.model.product;

import java.util.UUID;


public class FoodProduct extends Product {

    @Override
    public boolean isSpesial() {
        return false;
    }

    public FoodProduct(String name, String price, UUID id) {
        super(name, price, id);
    }
}

