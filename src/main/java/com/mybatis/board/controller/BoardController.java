package com.mybatis.board.controller;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        return "index";
    }
}
