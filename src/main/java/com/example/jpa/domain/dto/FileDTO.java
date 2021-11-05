package com.example.jpa.domain.dto;

import java.time.LocalDateTime;

public class FileDTO {

    private Long fileIdx;

    private String filePath;
    private String uuid;
    private String fileName;
    private Long size;
    private String ext;
    private LocalDateTime regDateTime;
}
