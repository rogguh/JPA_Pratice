package com.example.jpa.board;

import com.example.jpa.common.api.HttpStatusEnum;
import com.example.jpa.common.api.ResMessage;
import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BoardService {

    final Logger log = LogManager.getLogger(this.getClass());
    private final BoardRepository boardRepository;
    private final BoardQueryDslRepository boardQueryDslRepository;
    private final BoardMapper boardMapper;

    /**
     * FUNCTION :: 리스트
     * @param boardDTO
     * @return
     */
    public ResponseEntity<ResMessage> search(BoardDTO boardDTO){
        Map<String, Object> rtnMap = new HashMap<String, Object>();

        long count = boardQueryDslRepository.countAllByDto(boardDTO);
        boardDTO.setTotalCount(count);
        List<BoardDTO> list = boardQueryDslRepository.findAllByDto(boardDTO);

        rtnMap.put("boardDTO", boardDTO);
        rtnMap.put("list", list);

        ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.OK)
                                                    .message("OK")
                                                    .data(rtnMap)
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.OK);
    }

    /**
     * FUNCTION :: 저장
     * @param boardDTO
     * @return
     */
    public ResponseEntity<ResMessage> save(BoardDTO boardDTO) {

        BoardEntity board = BoardEntity.builder().boardDTO(boardDTO).build();
        boardRepository.save(board);

        ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.CREATED)
                                                    .message("CREATED")
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.CREATED);
    }

    /**
     * FUNCTION :: 상세
     * @param idx
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseEntity<ResMessage> view(Long idx){
        BoardEntity board = boardQueryDslRepository.findByIdx(idx);
        board.view(); // LINE :: 조회수 증가

        BoardDTO boardDTO = boardMapper.toDto(board);

        ResMessage resMessage = ResMessage.builder().httpStatusEnum(HttpStatusEnum.OK)
                                                    .message("OK")
                                                    .data(boardDTO)
                                                    .build();

        return new ResponseEntity<>(resMessage, HttpStatus.OK);
    }

    /**
     * FUNCTION :: 삭제
     * @param idx
     */
    public void delete(Long idx){
        BoardEntity board = boardQueryDslRepository.findByIdx(idx);
        board.delete();
    }
}