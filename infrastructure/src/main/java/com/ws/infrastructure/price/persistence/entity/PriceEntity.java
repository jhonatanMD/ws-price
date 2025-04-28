package com.ws.infrastructure.price.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a price in the database.
 *
 * <p>This class maps to the {@code PRICES} table in the database. It contains information about a price,
 * including its price list identifier {@link #priceList}, associated product {@link #productId},
 * brand {@link #brand}, priority {@link #priority}, currency {@link #currency}, and the effective
 * time range for the price {@link #startDate} and {@link #endDate}. The entity is also indexed to optimize
 * queries based on the product ID, brand ID, start date, end date, and priority.</p>
 *
 * @see Entity
 * @see Table
 * @see BrandEntity
 */
@Getter
@Setter
@Entity
@Table(name = "PRICES", indexes = @Index(columnList = "productId, brandId, startDate, endDate, priority DESC"))
public class PriceEntity {

    @Id
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURR")
    private Currency currency;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    private BrandEntity brand;
}
