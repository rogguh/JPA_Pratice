package com.example.jpa.board;

import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.dto.PagingDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static com.example.jpa.db.entity.QBoardEntity.boardEntity;
import static org.springframework.util.StringUtils.*;

@Repository
@RequiredArgsConstructor
public class BoardQueryDslRepository {

    final Logger log = LogManager.getLogger(this.getClass());
    private final JPAQueryFactory queryFactory;

    /**
     * FUNCTION :: 카운트 쿼리
     * @param boardDTO
     * @return
     */
    public long countAllByDto(BoardDTO boardDTO){
        return queryFactory.select(boardEntity.idx)
                           .from(boardEntity)
                           .where(
                                   titleContains(boardDTO.getTitle())
                           )
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
                           .where(
                                   titleContains(boardDTO.getTitle())
                           )
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

    /**
     * FUNCTION :: 검색조건
     * @param params
     * @return
     */
    private BooleanExpression titleContains(String title){
        return hasText(title) ? boardEntity.title.contains(title) : null;
    }
}