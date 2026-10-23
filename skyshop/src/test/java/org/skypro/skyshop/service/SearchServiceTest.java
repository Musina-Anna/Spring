package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test  ///первый тест
    void search_EmptyStorage_ReturnsEmptyResult() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("хлеб");

        assertThat(results).isEmpty();
    }

    @Test ///второй тест
    void search_StorageHasItemButNoMatch_ReturnsEmptyResults(){

        Searchable product=new SimpleProduct(UUID.randomUUID(),"Яблоко",50);
        when(storageService.getAllSearchable()).thenReturn(List.of(product));

        Collection<SearchResult>results=searchService.search("хлеб");

        assertThat(results).isEmpty();

    }

    @Test  ///третий тест
    void  search_MatchFound_ReturnsResults(){
        Searchable product=new SimpleProduct(UUID.randomUUID(),"TestProduct",100);
        when(storageService.getAllSearchable()).thenReturn(List.of(product));

        Collection<SearchResult>results=searchService.search("Test");

        assertThat(results).hasSize(1);
        assertThat(results.iterator().next().getName()).isEqualTo("TestProduct");
    }
}
