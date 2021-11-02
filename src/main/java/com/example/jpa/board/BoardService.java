package com.example.jpa.board;

import com.example.jpa.common.HttpStatusEnum;
import com.example.jpa.common.ResMessage;
import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.db.repository.BoardRepository;
import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    final Logger log = LogManager.getLogger(this.getClass());
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public ResponseEntity<ResMessage> save(BoardDTO boardDTO){

        BoardEntity board = BoardEntity.builder().boardDTO(boardDTO).build();
        boardRepository.save(board);

        ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.CREATED)
                                                    .message("OK")
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.CREATED);
    }

    public ResponseEntity<ResMessage> view(Long idx){
        BoardEntity board = boardRepository.findByIdx(idx);
        log.info("board값 : " + board.toString());
        BoardDTO boardDTO = boardMapper.toDto(board);
        log.info("boardDTO값 : " + boardDTO.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}