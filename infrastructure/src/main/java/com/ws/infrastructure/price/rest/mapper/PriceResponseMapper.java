package com.ws.infrastructure.price.rest.mapper;

import com.ws.application.price.model.PriceDto;
import com.ws.infrastructure.price.rest.dto.PriceResponseDTO;
import org.mapstruct.Mapper;


/**
 * Mapper interface for converting {@link PriceDto} to {@link PriceResponseDTO}.
 *
 * <p>This interface uses MapStruct to define a mapping between the {@link PriceDto} class
 * (a data transfer object used for representing price information in the service layer) and
 * the {@link PriceResponseDTO} (a response DTO that will be sent to the client). The mapping
 * is performed at compile-time by MapStruct, generating the implementation of this interface.</p>
 *
 * @see PriceDto
 * @see PriceResponseDTO
 * @see Mapper
 */
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    /**
     * Maps a {@link PriceDto} to a {@link PriceResponseDTO}.
     *
     * @param price the {@link PriceDto} to map
     * @return the corresponding {@link PriceResponseDTO}
     */
    PriceResponseDTO toResponse(PriceDto price);
}
