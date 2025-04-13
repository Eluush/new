package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Book extends Product {
    public Book(String name, String priceStr, UUID id) {
        super(name, priceStr, id);
    }

    @Override
    public boolean isSpesial() {
        return false;
    }
}
