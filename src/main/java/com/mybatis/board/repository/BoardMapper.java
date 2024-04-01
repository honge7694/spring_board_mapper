package com.mybatis.board.repository;

import com.mybatis.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void save(BoardDto boardDto);

    List<BoardDto> findAll();
}

