package com.ws.infrastructure.price.persistence.adapter;


import com.ws.application.price.port.out.PriceOutboundPort;
import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.PriceJpaRepository;
import com.ws.infrastructure.price.persistence.mapper.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Adapter class that implements the {@link PriceOutboundPort} interface and interacts with the database.
 *
 * <p>This class acts as a bridge between the domain layer and the data layer, implementing the
 * {@link PriceOutboundPort} interface to provide the necessary functionality for retrieving price information
 * from the database. It uses {@link PriceJpaRepository} to query the database and maps the
 * {@link com.ws.infrastructure.price.persistence.entity.PriceEntity} to the domain model {@link Price} using {@link PriceEntityMapper}.</p>
 *
 * @see PriceOutboundPort
 * @see PriceJpaRepository
 * @see PriceEntityMapper
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceOutboundPort {

    private final PriceJpaRepository priceJPARepository;
    private final PriceEntityMapper mapper;

    /**
     * Retrieves the preferred price for a given product and brand at a specified application date.
     *
     * <p>This method queries the {@link PriceJpaRepository} to find the price based on the given
     * product ID, brand ID, and application date. If a price is found, it is mapped to the
     * domain model {@link Price} using the {@link PriceEntityMapper}.</p>
     *
     * @param applicationDate the date for which the price is being retrieved
     * @param productId the identifier of the product
     * @param brandId the identifier of the brand
     * @return an {@link Optional} containing the preferred {@link Price}, or an empty {@link Optional}
     *         if no price is found
     */
    @Override
    public Optional<Price> getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceJPARepository.findTopPrice(productId, brandId, applicationDate)
                .map(mapper::toDomain);
    }
}
