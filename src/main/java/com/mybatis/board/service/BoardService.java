package com.mybatis.board.service;

import com.mybatis.board.dto.BoardDto;
import com.mybatis.board.dto.BoardFileDto;
import com.mybatis.board.entity.Board;
import com.mybatis.board.entity.BoardFile;
import com.mybatis.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Value("${file.upload-dir}")
    private String uploadDir;
    private final BoardMapper boardMapper;
    public void save(BoardDto boardDto) throws IOException {
        if (boardDto.getBoardFile().get(0).isEmpty()) {
            // 파일 업로드 되지 않았을 때
            boardDto.setFileAttached(0);

            // dto -> entity
            Board board = new Board(boardDto);

            boardMapper.save(board);
        } else {
            // 파일 업로드 되었을 때
            boardDto.setFileAttached(1);
            System.out.println("[Service] boardDto = " + boardDto);

            // dto -> entity
            Board board = new Board(boardDto);
            System.out.println("전 : board.getId() = " + board.getId());

            // 게시글 저장 후 id 값 활용
            boardMapper.save(board);
            System.out.println("후 : board.getId() = " + board.getId());

            // 파일만 가져오기
            for(MultipartFile boardFile : boardDto.getBoardFile()) {
                // 파일 이름 가져오기
                String originFileName = boardFile.getOriginalFilename();
                System.out.println("originFileName = " + originFileName);

                // 저장용 이름 만들기
                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis() + "-" + originFileName;
                System.out.println("storedFileName = " + storedFileName);

                // BoardFileDto
                BoardFileDto boardFileDto = new BoardFileDto(board.getId(), originFileName, storedFileName);

                // 파일 저장용 폴더에 파일 저장
                String savePath = uploadDir;
                boardFile.transferTo(new File(savePath + storedFileName));
                boardMapper.saveFile(boardFileDto);
            }
        }
//        boardMapper.save(boardDto);
    }

    public List<BoardDto> findAll() {
        List<Board> boardList = boardMapper.findAll();
        return boardList.stream().map(BoardDto::new).toList();
    }

    public void updateHits(long id) {
        boardMapper.updateHits(id);
    }

    public BoardDto findById(long id) {
        Board board = boardMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return new BoardDto(board);
    }

    public void update(BoardDto boardDto) throws IOException {
        if (boardDto.getBoardFile().isEmpty()) {
            // 파일 업로드 되지 않았을 때
            boardDto.setFileAttached(0);

            // dto -> entity
            boardMapper.update(boardDto);
        } else {
            // 파일 업로드 되었을 때
            boardDto.setFileAttached(1);
            System.out.println("[Service] boardDto = " + boardDto);

            /*
            // 파일만 가져오기
            MultipartFile newBoardFile = boardDto.getBoardFile();
            String originFileName = newBoardFile.getOriginalFilename();
            System.out.println("originFileName = " + originFileName);

            // 파일을 변경하지 않았다면 board만 update
            BoardFile boardFile = findFile(boardDto.getId());
            System.out.println("[if 전]boardFile = " + boardFile);
            if (boardFile.getOriginFileName().equals(originFileName)) {
                boardMapper.update(boardDto);
            } else {
                // 저장용 이름 만들기
                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis() + "-" + originFileName;
                System.out.println("storedFileName = " + storedFileName);

                // BoardFileDto
                BoardFileDto boardFileDto = new BoardFileDto(boardFile.getId(), boardDto.getId(), originFileName, storedFileName);
                System.out.println("boardFileDto = " + boardFileDto);
                // 파일 저장용 폴더에 파일 저장
                String savePath = uploadDir;
                newBoardFile.transferTo(new File(savePath + storedFileName));
                boardMapper.updateFile(boardFileDto);
            }*/
        }
    }

    public void delete(long id) {
        boardMapper.delete(id);
    }

    public List<BoardFile> findFile(long id) {
        return boardMapper.findFileByBoardId(id);
    }
}
