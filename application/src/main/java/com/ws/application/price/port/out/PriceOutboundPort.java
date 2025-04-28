package com.ws.application.price.port.out;

import com.ws.domain.price.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Outbound port interface for retrieving the preferred price of a product
 * based on the application date and brand.
 *
 * <p>This interface defines the contract for fetching the preferred price
 * from an external system or service (e.g., a database, external API, etc.)</p>
 *
 * <p>The {@link Price} entity returned contains the price details, including
 * price list, product ID, price value, start and end dates, and brand identifier.</p>
 */
public interface PriceOutboundPort {


  /**
   * Retrieves the preferred price for a given product, brand, and application date from an external source.
   *
   * @param applicationDate the date on which the price is being applied
   * @param productId       the identifier of the product
   * @param brandId         the identifier of the brand
   * @return an {@link Optional} containing the {@link Price} entity if found, or an empty {@link Optional} if no price is available
   */
  Optional<Price> getPreferredPrice(LocalDateTime applicationDate, Integer productId,
                                    Integer brandId);
}