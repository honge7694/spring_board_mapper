package com.mybatis.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFile {
    private Long id;
    private Long boardId;
    private String originFileName;
    private String storedFileName;
}
