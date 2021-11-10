package com.example.jpa.domain.mapper;

import com.example.jpa.config.GenericMapperInterface;
import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapperInterface<BoardDTO, BoardEntity> {

    @Override
    BoardEntity toEntity(BoardDTO boardDTO);

    @Override
    @Mapping(target = "attachedFiles", ignore = true)
    BoardDTO toDto(BoardEntity boardEntity);

    @Override
    List<BoardDTO> toDtoList(List<BoardEntity> entityList);

    @Override
    List<BoardEntity> toEntityList(List<BoardDTO> dtoList);

}