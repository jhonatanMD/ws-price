package com.ws.infrastructure.price.persistence.adapter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ws.domain.price.model.Brand;
import com.ws.domain.price.model.Currency;
import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.PriceJpaRepository;
import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import com.ws.infrastructure.price.persistence.mapper.PriceEntityMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJPARepository;
    @Mock
    private PriceEntityMapper mapper;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryImpl;

    @Test
    void getPreferredPrice_ShouldReturnMappedPrice_WhenPriceExists() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        PriceEntity mockEntity = new PriceEntity();
        Price mockDto = new Price(0, productId, 0, Currency.EUR,
                BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now(),
                new Brand(brandId, "Zara"));

        when(priceJPARepository.findTopPrice(
                productId, brandId, applicationDate)).thenReturn(Optional.of(mockEntity));
        when(mapper.toDomain(mockEntity)).thenReturn(mockDto);

        Optional<Price> result = priceRepositoryImpl.getPreferredPrice(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(mockDto, result.get());
    }

    @Test
    void getPreferredPrice_ShouldReturnEmpty_WhenPriceDoesNotExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        when(priceJPARepository.findTopPrice(
                productId, brandId, applicationDate)).thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryImpl.getPreferredPrice(applicationDate, productId, brandId);

        assertFalse(result.isPresent());
    }
}
