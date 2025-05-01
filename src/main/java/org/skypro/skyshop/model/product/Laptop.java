package org.skypro.skyshop.model.product;

import java.util.UUID;


public class Laptop extends Product {

    public Laptop(String name, String priceStr, UUID id) {
        super(name, priceStr, id);
    }


    @Override
    public boolean isSpesial() {
        return false;
    }
}
