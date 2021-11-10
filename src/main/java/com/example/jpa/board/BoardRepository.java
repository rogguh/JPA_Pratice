package com.example.jpa.board;

import com.example.jpa.db.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
