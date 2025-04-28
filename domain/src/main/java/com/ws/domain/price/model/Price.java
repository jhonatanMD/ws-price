package com.ws.domain.price.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents the price details of a product for a specific price list and brand.
 *
 * <p>This record contains all relevant information about a price, including
 * the price list, product ID, priority, currency, price amount, start and end
 * dates, and the associated brand. It is used to model pricing information
 * in the domain.</p>
 *
 * @param priceList the identifier of the price list
 * @param productId the identifier of the product
 * @param priority the priority of the price (for ranking purposes)
 * @param currency the currency of the price, defined by {@link Currency}
 * @param price the price value of the product
 * @param startDate the date and time when the price starts being valid
 * @param endDate the date and time when the price stops being valid
 * @param brand the {@link Brand} associated with the price
 */
public record Price(
        Integer priceList,
        Integer productId,
        Integer priority,
        Currency currency,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Brand brand
) {}
