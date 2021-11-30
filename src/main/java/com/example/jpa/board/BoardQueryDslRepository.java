package com.example.jpa.board;

import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.dto.PagingDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jpa.db.entity.QBoardEntity.boardEntity;

@Repository
@RequiredArgsConstructor
public class BoardQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * FUNCTION :: 카운트 쿼리
     * @param boardDTO
     * @return
     */
    public long countAllByDto(BoardDTO boardDTO){
        return queryFactory.select(boardEntity.idx)
                           .from(boardEntity)
                           .where(boardEntity.delStatus.eq(false))
                           .fetchCount();
    }

    /**
     * FUNCTION :: 리스트 쿼리
     * @param boardDTO
     * @return
     */
    public List<BoardDTO> findAllByDto(BoardDTO boardDTO){
        return queryFactory.select(Projections.fields(BoardDTO.class
                                             , boardEntity.idx
                                             , boardEntity.title
                                             , boardEntity.content
                                             , boardEntity.delStatus
                                             , boardEntity.regDate
                                             , boardEntity.hits
                                  ))
                           .from(boardEntity)
                           .where(boardEntity.delStatus.eq(false))
                           .offset(boardDTO.getPageNo())
                           .limit(boardDTO.getPageSize())
                           .orderBy(boardEntity.regDate.desc())
                           .fetch();
    }

    /**
     * FUNCTION :: 상세 쿼리
     * @param idx
     * @return
     */
    public BoardEntity findByIdx(Long idx){
        return queryFactory.selectFrom(boardEntity)
                .where(boardEntity.idx.eq(idx))
                .fetchOne();
    }

}