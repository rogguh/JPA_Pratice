package com.example.jpa.board;

import com.example.jpa.common.api.ResMessage;
import com.example.jpa.domain.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/board")
    public void save(BoardDTO boardDTO) {
        boardService.save(boardDTO);
    }

    @GetMapping(value = "/board/{idx}")
    public ResponseEntity<ResMessage> view(@PathVariable("idx") Long idx){
        return boardService.view(idx);
    }
}