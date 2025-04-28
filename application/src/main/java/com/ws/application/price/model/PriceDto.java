package com.ws.application.price.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing the price details of a product.
 *
 * <p>This {@link PriceDto} contains all the necessary information about a price
 * for a product including the list of prices, product identifier, price amount,
 * start and end dates, and the brand identifier.</p>
 *
 * <p>This DTO is typically used for transferring price information between
 * layers or services within the application.</p>
 * <p>
 * Example usage:
 * <pre>{@code
 * PriceDto priceDto = new PriceDto(1, 101, new BigDecimal("19.99"),
 *                                   LocalDateTime.now(), LocalDateTime.now().plusDays(10), 1001);
 * }</pre>
 *
 * @param priceList the identifier of the price list
 * @param productId the identifier of the product
 * @param price     the price value
 * @param startDate the date and time when the price starts being valid
 * @param endDate   the date and time when the price ends being valid
 * @param brandId   the identifier of the brand associated with the price
 */
public record PriceDto(
    Integer priceList,
    Integer productId,
    BigDecimal price,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer brandId
) {
}
