package com.mybatis.board.controller;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "redirect:/list";
    }

    @PostMapping("/save")
    public String save(BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        boardService.save(boardDto);
        return "index";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println("boardDto = " + boardDto);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println("get update boardDto = " + boardDto);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDto boardDto, Model model) {
        boardService.update(boardDto);
        // redirect를 안쓰는 이유는 수정 후 조회 수가 증가하기 때문
        BoardDto board = boardService.findById(boardDto.getId());
        model.addAttribute("boardDetail", board);
        return "detail";
    }
}
