package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Book;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;


    @Test
    void search_WhenStorageEmpty_ReturnsEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        List<SearchResult> result = (List<SearchResult>) searchService.search("any");

        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenNoMatches_ReturnsEmptyList() {
        Searchable article = mock(Searchable.class);
        when(article.getSearchTerm()).thenReturn("Статья о программировании");

        Searchable product = mock(Searchable.class);
        when(product.getSearchTerm()).thenReturn("Книга по Java");

        when(storageService.getAllSearchables()).thenReturn(List.of(article, product));

        List<SearchResult> result = (List<SearchResult>) searchService.search("Python");

        assertTrue(result.isEmpty());
        verify(article, atLeastOnce()).getSearchTerm();
        verify(product, atLeastOnce()).getSearchTerm();
    }

    @Test
    void search_WhenMatchesExist_ReturnsResults() {
        Book book = new Book("Effective Java", "999.99", UUID.randomUUID());
        Article article = new Article("Java Weekly", "Новости Java", UUID.randomUUID());

        when(storageService.getAllSearchables()).thenReturn(List.of(book, article));

        List<SearchResult> result = (List<SearchResult>) searchService.search("Java");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(r -> r.getName().contains("Effective Java")));
        assertTrue(result.stream().anyMatch(r -> r.getName().contains("Java Weekly")));
    }
}