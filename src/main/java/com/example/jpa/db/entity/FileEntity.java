package com.example.jpa.db.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tFile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString
public class FileEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    private BoardEntity board;

    private String filePath;
    private String uuid;
    private String fileName;
    private Long size;
    private String ext;
    @Column(columnDefinition = "bit default 0")
    private Boolean delStatus = false;
    private LocalDateTime regDateTime;

    /**
     * 생성
     * @param board
     * @param multipartFile
     */
    @Builder
    public FileEntity(BoardEntity board, MultipartFile multipartFile){
        this.board = board;
        this.filePath = "";
        this.uuid = UUID.randomUUID().toString();
        this.fileName = "";
        this.size = multipartFile.getSize();
        this.ext = fileName.substring(fileName.lastIndexOf(".")+1);
        this.regDateTime = LocalDateTime.now();
    }

    /**
     * FUNCTION :: 삭제
     */
    public void delete(){
        delStatus = true;
    }
}