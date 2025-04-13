package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Book;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        initTestData();
    }

    private void initTestData() {

        Article article1 = new Article("Война и мир", "произведение топ 1", UUID.randomUUID());
        articles.put(article1.getId(), article1);

        Article article2 = new Article("Лучшие книги кода", "название книг", UUID.randomUUID());
        articles.put(article2.getId(), article2);


        Product book1 = new Book("Книга 1", "2000", UUID.randomUUID());
        products.put(book1.getId(), book1);

        Product book2 = new Book("Книга 2", "3000", UUID.randomUUID());
        products.put(book2.getId(), book2);
    }

    public Collection<Article> getAllArticles() {
        return new ArrayList<>(articles.values());
    }

    public Collection<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> all = new ArrayList<>();
        all.addAll(articles.values());
        all.addAll(products.values());
        return all;
    }
}