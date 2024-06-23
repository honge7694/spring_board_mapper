package com.mybatis.board.entity;

import com.mybatis.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Board {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private int fileAttached;

    public Board(BoardDto boardDto) {
        this.boardWriter = boardDto.getBoardWriter();
        this.boardPass = boardDto.getBoardPass();
        this.boardTitle = boardDto.getBoardTitle();
        this.boardContents = boardDto.getBoardContents();
        this.boardHits = boardDto.getBoardHits();
        this.createdAt = boardDto.getCreatedAt();
        this.fileAttached = boardDto.getFileAttached();
    }
}
