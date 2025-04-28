package com.ws.infrastructure.price.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * Entity class representing a brand in the database.
 *
 * <p>This class maps to the {@code BRAND} table in the database. It contains information about a brand,
 * including its unique identifier {@link #id} and its name {@link #name}. This entity is typically used to
 * store and retrieve brand data from the database.</p>
 *
 * @see Entity
 * @see Table
 */
@Setter
@Getter
@Entity
@Table(name = "BRAND")
public class BrandEntity {
    @Id
    private Integer id;
    private String name;
}
