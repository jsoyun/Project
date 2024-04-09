package com.favorite.project.Board;

import com.favorite.project.Board.Mapper.BoardMapper;
import com.favorite.project.Board.domain.Board;
import com.favorite.project.Board.dto.BoardAddDto;
import com.favorite.project.Board.dto.BoardResponseDto;
import com.favorite.project.Board.dto.BoardStatusToReservedDto;
import com.favorite.project.Clothes.Mapper.ClothesMapper;
import com.favorite.project.exception.ReservationNotAllowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final ClothesMapper clothesMapper;


    //하나의 옷만 올릴 수 있음.
    public BoardResponseDto postBoard(BoardAddDto boardAddDto) {
        boolean clothes = clothesMapper.checkClothesById(boardAddDto.getClothesId());
        if (!clothes) {
            throw new NoSuchElementException("존재하는 옷이 아닙니다.");


        }
        Board board = boardAddDto.toBoard(boardAddDto);
        boardMapper.insertBoard(board);

        return BoardResponseDto.builder().build().toBoardResponse(board);


    }


    //게시글 올린 사람 맞는지 확인
    public void checkPoster(Long userId, int boardId) {
        boolean checkResult = boardMapper.checkBoardPoster(userId, boardId);
        if (!checkResult) {
            throw new NoSuchElementException("사용자는 게시글의 주인이 아닙니다");
        }
    }

    //이용가능한 게시판인지
    public void checkBoardStatus(int boardId) {

        boolean result = boardMapper.checkBoardStatusIsAvailable(boardId);
        if (!result) {
            throw new ReservationNotAllowedException("예약이 불가능합니다.");

        }

    }

    //예약승인된 게시판 예약됨으로 상태바꾸기
    public void changeToReservedBoard(int boardId) {


        boardMapper.changeToReserved(
                BoardStatusToReservedDto
                        .builder()
                        .boardStatus(BoardStatus.RESERVED)
                        .boardId(boardId)
                        .build());


    }


}
