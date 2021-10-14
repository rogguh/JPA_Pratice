package com.example.jpa.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "tBoard")
@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long idx;

    private String title;
    private String content;
    private LocalDateTime regDate;

}
