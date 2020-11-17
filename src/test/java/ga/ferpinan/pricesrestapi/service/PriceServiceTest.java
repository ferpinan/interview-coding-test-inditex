package ga.ferpinan.pricesrestapi.service;

import ga.ferpinan.pricesrestapi.exception.PriceNotFoundException;
import ga.ferpinan.pricesrestapi.model.Price;
import ga.ferpinan.pricesrestapi.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 135455L;

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private Price priceMock;

    private List<Price> priceListWithMocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceListWithMocks = new ArrayList<>();
    }

    @Test
    void whenFindPriorPrice_withRepositoriesSuccessfulReturn_thenReturnRepositoriesObject(){
        LocalDateTime localDateTime = LocalDateTime.now();
        priceListWithMocks.add(priceMock);
        when(priceRepository.findPriorPriceList(eq(BRAND_ID), eq(PRODUCT_ID), eq(localDateTime), any(PageRequest.class))).thenReturn(priceListWithMocks);

        Price priorPrice = priceService.findPriorPrice(BRAND_ID, PRODUCT_ID, localDateTime);
        assertEquals(priceMock, priorPrice);
    }

    @Test
    void whenFindPriorPrice_withRepositoriesEmptyReturn_thenThrowPriceNotFoundException(){
        LocalDateTime localDateTime = LocalDateTime.now();
        when(priceRepository.findPriorPriceList(eq(BRAND_ID), eq(PRODUCT_ID), eq(localDateTime), any(PageRequest.class))).thenReturn(priceListWithMocks);

        Assertions.assertThrows(PriceNotFoundException.class, () -> priceService.findPriorPrice(BRAND_ID, PRODUCT_ID, localDateTime));
    }
}