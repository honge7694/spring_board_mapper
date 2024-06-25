package com.mybatis.board.controller;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.dto.BoardFileDto;
import com.mybatis.board.entity.BoardFile;
import com.mybatis.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public String save(BoardDto boardDto) throws IOException {
        System.out.println("boardDto = " + boardDto);
        boardService.save(boardDto);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model) {
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardDetail", boardDto);
        System.out.println("boardDto = " + boardDto);
        if (boardDto.getFileAttached() == 1) {
            List<BoardFile> boardFileDtoList = boardService.findFile(id);
            System.out.println("[detail] boardFileDtoList = " + boardFileDtoList);
            model.addAttribute("boardFileDtoList", boardFileDtoList);
        }
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println("get update boardDto = " + boardDto);
        if (boardDto.getFileAttached() == 1) {
            List<BoardFile> boardFile = boardService.findFile(id);
            model.addAttribute("boardFile", boardFile);
            System.out.println("[update] boardFileDto = " + boardFile);
        }
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDto boardDto, Model model) throws IOException {
        boardService.update(boardDto);
        System.out.println("[update.post] boardDto = " + boardDto.toString());
        // redirect를 안쓰는 이유는 수정 후 조회 수가 증가하기 때문
        BoardDto board = boardService.findById(boardDto.getId());
        System.out.println("[update.post] board = " + board);
        model.addAttribute("boardDetail", board);
        if (board.getFileAttached() == 1) {
            List<BoardFile> boardFile = boardService.findFile(boardDto.getId());
            System.out.println("[update.post] boardFileDto = " + boardFile);
            model.addAttribute("boardFile", boardFile);
        }
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
