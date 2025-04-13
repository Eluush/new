package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {
    public final String name;
    private int price;
    private final UUID id;

    public Product(String name, String priceStr, UUID id) {
        this.name = name;
        try {
            this.price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            this.price = 0;
        }
        this.id = id;
    }


    public int getPrice() {
        return price;
    }


    public Product(String apple, int i, int price, UUID id) {
        this.price = price;
        this.id = id;
        name = "";
    }


    public String getName() {
        return name;
    }


    public abstract boolean isSpesial();

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return this.getName() + " " + this.getPrice();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }


}





