package com.example.jpa.db.entity;

import com.example.jpa.domain.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tBoard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long idx;

    private String title;
    private String content;
    @Column(columnDefinition = "bit default 0")
    private Boolean delStatus = false;
    private LocalDateTime regDate;

    /**
     * FUNCTION :: 생성
     * @param boardDTO
     */
    @Builder
    public BoardEntity(BoardDTO boardDTO){
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.delStatus = boardDTO.getDelStatus();
        this.regDate = LocalDateTime.now();
    }
}