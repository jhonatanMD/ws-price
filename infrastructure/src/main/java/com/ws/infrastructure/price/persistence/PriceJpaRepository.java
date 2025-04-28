package com.ws.infrastructure.price.persistence;


import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link PriceEntity}.
 *
 * <p>This interface extends {@link JpaRepository} and provides methods to query the {@link PriceEntity}
 * table in the database. It includes a custom query method to find the highest-priority price for a product
 * and brand, valid at a given date. The repository uses Spring Data JPA to handle data access operations
 * without the need for boilerplate code.</p>
 *
 * @see PriceEntity
 * @see JpaRepository
 */

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Finds the top (highest-priority) price for a specific product and brand, valid at a given date.
     *
     * <p>This query fetches the {@link PriceEntity} with the highest priority for a given {@code productId}
     * and {@code brandId}, and checks that the price is valid for the provided {@code date}. It returns
     * the first result ordered by priority in descending order.</p>
     *
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @param date the date for which the price needs to be valid
     * @return an {@link Optional} containing the {@link PriceEntity} if a price is found, or an empty {@link Optional}
     * if no price matches the criteria
     */
    @Query("""
                SELECT p FROM PriceEntity p
                WHERE p.productId = :productId
                AND p.brand.id = :brandId
                AND p.startDate <= :date
                AND p.endDate >= :date
                ORDER BY p.priority DESC LIMIT 1
            """)
    Optional<PriceEntity> findTopPrice(@Param("productId") Integer productId,
                                       @Param("brandId") Integer brandId,
                                       @Param("date") LocalDateTime date);

}
