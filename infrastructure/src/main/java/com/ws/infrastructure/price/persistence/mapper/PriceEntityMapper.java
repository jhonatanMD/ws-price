package com.ws.infrastructure.price.persistence.mapper;

import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;


/**
 * Mapper interface for converting {@link PriceEntity} to {@link Price}.
 *
 * <p>This interface uses MapStruct to define a mapping between the {@link PriceEntity} class
 * (representing a price record in the database) and the {@link Price} domain model
 * (representing a price in the business logic layer). The mapping is performed by MapStruct
 * at compile-time, generating the implementation of this interface.</p>
 *
 * @see PriceEntity
 * @see Price
 * @see Mapper
 */
@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    /**
     * Maps a {@link PriceEntity} to a {@link Price} domain object.
     *
     * @param entity the {@link PriceEntity} to map
     * @return the corresponding {@link Price} domain object
     */
    Price toDomain(PriceEntity entity);
}
