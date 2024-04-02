package com.mybatis.board.dto;

import com.mybatis.board.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.boardWriter = board.getBoardWriter();
        this.boardPass = board.getBoardPass();
        this.boardTitle = board.getBoardTitle();
        this.boardContents = board.getBoardContents();
        this.boardHits = board.getBoardHits();
        this.createdAt = board.getCreatedAt();
    }
}
