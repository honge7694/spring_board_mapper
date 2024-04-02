package com.mybatis.board.service;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.entity.Board;
import com.mybatis.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
//    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    public void save(BoardDto boardDto) {
        boardMapper.save(boardDto);
    }

    public List<BoardDto> findAll() {
        List<Board> boardList = boardMapper.findAll();
        return boardList.stream().map(BoardDto::new).toList();
    }

    public void updateHits(Long id) {
        boardMapper.updateHits(id);
    }

    public BoardDto findById(Long id) {
        Board board = boardMapper.findById(id).orElseThrow();
        return new BoardDto(board);
    }
}
