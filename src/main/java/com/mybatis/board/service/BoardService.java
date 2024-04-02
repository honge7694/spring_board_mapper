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
        System.out.println("id = " + id);
        Board board = boardMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return new BoardDto(board);
    }

    public void update(BoardDto boardDto) {
        boardMapper.update(boardDto);
    }
}
