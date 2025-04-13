package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final SearchService searchService;

    public ShopController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public String getAllProducts(@RequestParam("name") String name,
                                 @RequestParam("2000") String priceStr,
                                 UUID id) {
        return name + priceStr + id;
    }

    @GetMapping("/articles")
    public String getAllArticles(@RequestParam("title") String title,
                                 @RequestParam("text") String text,
                                 UUID id) {
        return title + text + id;
    }
}