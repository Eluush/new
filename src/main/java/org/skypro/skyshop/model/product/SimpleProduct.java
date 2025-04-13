package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, String description, int price, UUID id) {
        super(name, description, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго выше 0.");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpesial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();

    }
}