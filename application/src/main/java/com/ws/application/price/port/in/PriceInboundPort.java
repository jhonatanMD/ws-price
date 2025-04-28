package com.ws.application.price.port.in;

import com.ws.application.price.model.PriceDto;
import java.time.LocalDateTime;

/**
 * Inbound port interface for retrieving the preferred price of a product
 * based on the application date and brand.
 *
 * <p>This interface defines the contract for fetching the preferred price
 * for a given product from an external source, such as a price service or database.</p>
 *
 * <p>The {@link PriceDto} returned contains the details of the price,
 * including the price list identifier, product ID, price value, start and end
 * dates, and brand identifier.</p>
 */
public interface PriceInboundPort {

  /**
   * Retrieves the preferred price for a given product, brand, and application date.
   *
   * @param applicationDate the date on which the price is being applied
   * @param productId       the identifier of the product
   * @param brandId         the identifier of the brand
   * @return the {@link PriceDto} representing the preferred price for the product and brand
   */
  PriceDto getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
