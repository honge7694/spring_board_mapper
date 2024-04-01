package com.mybatis.board.service;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.repository.BoardMapper;
import com.mybatis.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
//    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    public void save(BoardDto boardDto) {
        boardMapper.save(boardDto);
    }

    public List<BoardDto> findAll() {
        return boardMapper.findAll();
    }
}
