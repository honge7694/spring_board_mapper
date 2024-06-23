package com.mybatis.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardFileDto {
    private Long id;
    private Long boardId;
    private String originFileName;
    private String storedFileName;

    public BoardFileDto(Long boardId, String originFileName, String storedFileName) {
        this.boardId = boardId;
        this.originFileName = originFileName;
        this.storedFileName = storedFileName;
    }

    public BoardFileDto(Long id, Long boardId, String originFileName, String storedFileName) {
        this.id = id;
        this.boardId = boardId;
        this.originFileName = originFileName;
        this.storedFileName = storedFileName;
    }
}
