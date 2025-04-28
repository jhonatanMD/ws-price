package com.ws.domain.price.model;

/**
 * Represents a brand with an identifier and a name.
 *
 * <p>This record is used to represent a brand entity in the domain, including
 * an identifier {@code id} and the brand's {@code name}. Records are a special
 * kind of class in Java that provide a compact syntax for creating immutable
 * data carriers.</p>
 *
 * @param id the unique identifier of the brand
 * @param name the name of the brand
 */
public record Brand(Integer id, String name) {}