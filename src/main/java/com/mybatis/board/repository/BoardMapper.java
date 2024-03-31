package com.mybatis.board.repository;

import com.mybatis.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void save(BoardDto boardDto);
}

