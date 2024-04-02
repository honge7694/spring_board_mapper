package com.mybatis.board.repository;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void save(BoardDto boardDto);

    List<Board> findAll();

    void updateHits(Long id);

    Optional<Board> findById(Long id);
}

