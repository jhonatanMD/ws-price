package com.ws.application.price.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ws.application.price.mapper.PriceDtoMapper;
import com.ws.application.price.model.PriceDto;
import com.ws.application.price.port.out.PriceOutboundPort;
import com.ws.domain.price.exception.NotFoundException;
import com.ws.domain.price.model.Brand;
import com.ws.domain.price.model.Currency;
import com.ws.domain.price.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceOutboundPort priceOutboundPort;

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceDtoMapper mapper;

    @Test
    void getPreferredPrice_ShouldReturnPrice_WhenPriceExists() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;
        var mockDomain = new Price(0, 0, 0, Currency.EUR,
                BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now(),
                new Brand(1, "Zara"));
        var mockDto = mock(PriceDto.class);
        when(mapper.toDto(any())).thenReturn(mockDto);
        when(priceOutboundPort.getPreferredPrice(applicationDate, productId, brandId)).thenReturn(Optional.of(mockDomain));
        PriceDto result = priceService.getPreferredPrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(mockDto, result);
    }

    @Test
    void getPreferredPrice_ShouldThrowException_WhenPriceDoesNotExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;
        when(priceOutboundPort.getPreferredPrice(applicationDate, productId, brandId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                priceService.getPreferredPrice(applicationDate, productId, brandId)
        );
    }
}
