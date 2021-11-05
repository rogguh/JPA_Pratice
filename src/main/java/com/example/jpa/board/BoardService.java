package com.example.jpa.board;

import com.example.jpa.common.api.HttpStatusEnum;
import com.example.jpa.common.api.ResMessage;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    final Logger log = LogManager.getLogger(this.getClass());
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    /**
     * FUNCTION :: 저장
     * @param boardDTO
     * @return
     */
    public void save(BoardDTO boardDTO) {

        BoardEntity board = BoardEntity.builder().boardDTO(boardDTO).build();
        boardRepository.save(board);

        /*ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.CREATED)
                                                    .message("CREATED")
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.CREATED);*/
    }

    /**
     * FUNCTION :: 상세
     * @param idx
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseEntity<ResMessage> view(Long idx){
        BoardEntity board = boardRepository.findByIdx(idx);
        BoardDTO boardDTO = boardMapper.toDto(board);

        ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.OK)
                                                    .message("OK")
                                                    .data(boardDTO)
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.OK);
    }
}