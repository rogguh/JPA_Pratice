package com.example.jpa.db.entity;

import com.example.jpa.domain.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tBoard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long idx;                                                                   // LINE :: 고유값

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<FileEntity> attachedFiles = new ArrayList<>();                         // LINE :: 첨부 파일 리스트

    private String title;                                                               // LINE :: 제목
    private String content;                                                             // LINE :: 내용
    @Column(columnDefinition = "bit default 0")
    private Boolean delStatus = false;                                                  // LINE :: 삭제여부
    private LocalDateTime regDate;                                                      // LINE :: 등록일

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

        // LINE :: 첨부파일 존재
        if(!boardDTO.getAttachedFiles().isEmpty()){
            boardDTO.getAttachedFiles().stream().forEach(attachedFile -> {
                this.attachedFiles.add(FileEntity.builder()
                                  .board(this)
                                  .multipartFile(attachedFile)
                                  .build());
            });
        }
    }
}