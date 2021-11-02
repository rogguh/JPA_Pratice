package com.example.jpa.config;

import java.util.List;

public interface GenericMapperInterface<D, E> {

    E toEntity(D d);

    D toDto(E e);

    List<D> toDtoList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);
}
