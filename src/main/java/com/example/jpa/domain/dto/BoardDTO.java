package com.example.jpa.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class BoardDTO {

    private Long idx;

    private String title;
    private String content;
    private Boolean delStatus;
    private LocalDateTime regDate;

}