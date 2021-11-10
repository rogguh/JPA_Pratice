package com.example.jpa.board;

import com.example.jpa.common.api.ResMessage;
import com.example.jpa.db.entity.BoardEntity;
import com.example.jpa.domain.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/board")
    public ResponseEntity<ResMessage> search(BoardDTO boardDTO){
        return boardService.search(boardDTO);
    }

    @PostMapping(value = "/board")
    public ResponseEntity<ResMessage> save(BoardDTO boardDTO) {
        return boardService.save(boardDTO);
    }

    @GetMapping(value = "/board/{idx}")
    public ResponseEntity<ResMessage> view(@PathVariable("idx") Long idx){
        return boardService.view(idx);
    }

    @DeleteMapping(value = "/board/{idx}")
    public void delete(@PathVariable("idx") Long idx){
        boardService.delete(idx);
    }
}