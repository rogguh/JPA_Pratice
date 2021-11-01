package com.example.jpa.domain.mapper;

import com.example.jpa.config.GenericMapperInterface;
import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapperInterface<BoardDTO, BoardEntity> {

}