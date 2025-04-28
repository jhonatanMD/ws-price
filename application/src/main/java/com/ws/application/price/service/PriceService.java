package com.ws.application.price.service;

import com.ws.application.price.mapper.PriceDtoMapper;
import com.ws.application.price.model.PriceDto;
import com.ws.application.price.port.in.PriceInboundPort;
import com.ws.application.price.port.out.PriceOutboundPort;
import com.ws.domain.price.exception.NotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service class that implements the {@link PriceInboundPort} interface,
 * providing the logic to retrieve the preferred price for a product
 * based on the application date and brand.
 *
 * <p>This service interacts with the outbound port {@link PriceOutboundPort}
 * to fetch the price data and uses the {@link PriceDtoMapper} to map
 * the retrieved {@link com.ws.domain.price.model.Price} entity to a {@link PriceDto}.</p>
 *
 * <p>If no price is found for the given parameters, a {@link NotFoundException}
 * is thrown to indicate that no price data is available.</p>
 *
 * @see PriceInboundPort
 * @see PriceOutboundPort
 * @see PriceDtoMapper
 */

@Component
@RequiredArgsConstructor
public class PriceService implements PriceInboundPort {


  private static final String MSG_NOTFOUND_ERROR = "No price available.";
  private final PriceOutboundPort priceOutboundPort;
  private final PriceDtoMapper mapper;

  /**
   * Retrieves the preferred price for the specified product and brand
   * at the given application date.
   *
   * @param applicationDate the date on which the price is being applied
   * @param productId       the identifier of the product
   * @param brandId         the identifier of the brand
   * @return the corresponding {@link PriceDto} if a price is found
   * @throws NotFoundException if no price is found for the given parameters
   */
  @Override
  public PriceDto getPreferredPrice(LocalDateTime applicationDate, Integer productId,
                                    Integer brandId) {
    return priceOutboundPort.getPreferredPrice(applicationDate, productId, brandId)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException(MSG_NOTFOUND_ERROR));
  }
}
