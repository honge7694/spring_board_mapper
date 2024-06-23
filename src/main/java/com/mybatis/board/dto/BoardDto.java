package com.mybatis.board.dto;

import com.mybatis.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private int fileAttached;
    private MultipartFile boardFile;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.boardWriter = board.getBoardWriter();
        this.boardPass = board.getBoardPass();
        this.boardTitle = board.getBoardTitle();
        this.boardContents = board.getBoardContents();
        this.boardHits = board.getBoardHits();
        this.createdAt = board.getCreatedAt();
        this.fileAttached = board.getFileAttached();
    }

    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardPass='" + boardPass + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContents='" + boardContents + '\'' +
                ", boardHits=" + boardHits +
                ", createdAt='" + createdAt + '\'' +
                ", fileAttached=" + fileAttached +
                ", boardFile=" + (boardFile != null ? boardFile.getOriginalFilename() : "null") +
            '}';
    }
}
