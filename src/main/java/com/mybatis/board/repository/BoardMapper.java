package com.mybatis.board.repository;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.dto.BoardFileDto;
import com.mybatis.board.entity.Board;
import com.mybatis.board.entity.BoardFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    long save(Board board);

    List<Board> findAll();

    void updateHits(Long id);

    Optional<Board> findById(Long id);

    void update(BoardDto boardDto);

    void delete(Long id);

    void saveFile(BoardFileDto boardFileDto);

    void updateFile(BoardFileDto boardFileDto);

    List<BoardFile> findFileByBoardId(Long id);
}

