package com.example.jpa.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@ToString
public class BoardDTO {

    private Long idx;

    private String title;
    private String content;
    private Boolean delStatus;
    private LocalDateTime regDate;
    @Column(columnDefinition = "int default 0")
    private int hits = 0;

    private List<MultipartFile> attachedFiles;

}