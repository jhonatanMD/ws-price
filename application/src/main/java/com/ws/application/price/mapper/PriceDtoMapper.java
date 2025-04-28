package com.ws.application.price.mapper;

import com.ws.application.price.model.PriceDto;
import com.ws.domain.price.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting {@link Price} domain models into {@link PriceDto}
 * Data Transfer Objects (DTOs).
 *
 * <p>This interface is implemented automatically by MapStruct and exposed as a Spring Bean
 * thanks to the {@code componentModel = "spring"} configuration.</p>
 *
 * <p>Mapping details:
 * <ul>
 *   <li>Maps {@code brand.id} from {@link Price} to {@code brandId} in {@link PriceDto}.</li>
 * </ul>
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * PriceDto priceDto = priceDtoMapper.toDto(price);
 * }</pre>
 *
 * @see Price
 * @see PriceDto
 */
@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

  /**
   * Converts a {@link Price} domain model into a {@link PriceDto}.
   *
   * @param price the domain model to convert
   * @return the corresponding {@link PriceDto}
   */
  @Mapping(source = "brand.id", target = "brandId")
  PriceDto toDto(Price price);
}
