package com.example.jpa.board;

import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
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
     * FUNCTION :: 리스트 쿼리
     * @param boardDTO
     * @return
     */
    public List<BoardEntity> findAllByDto(BoardDTO boardDTO){
       return queryFactory.selectFrom(boardEntity)
               .where(boardEntity.delStatus.eq(false))
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