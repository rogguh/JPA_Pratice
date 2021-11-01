package com.example.jpa.config;

public interface GenericMapperInterface<D, E> {

    // LINE :: Entity To DTO
    D toDto(E e);

    // LINE :: DTO To Entity
    E toEntity(D d);
}
