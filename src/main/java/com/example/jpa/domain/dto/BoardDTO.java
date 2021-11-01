package com.example.jpa.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO {

    private Long idx;

    private String title;
    private String content;
    private LocalDateTime regDate;

}
