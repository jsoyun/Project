package com.favorite.project.ReserveRequest;

import com.favorite.project.Board.BoardService;
import com.favorite.project.ReserveRequest.Mapper.ReserveMapper;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.favorite.project.ReserveRequest.ReserveRequestStatus.APPROVED;

@SpringBootTest
public class ReserveServiceDBTest {


    @Autowired
    private ReserveMapper reserveMapper;
    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("이미_예약됨_특정예약요청건_승인_실패")
    void FAIL_APPROVE_ReservationRequest() {
        Long userId = 23L;
        int boardId = 24;

        boardService.checkPoster(userId, boardId);
        boardService.checkBoardStatus(boardId);


    }

    @Test
    @DisplayName("예약요청건_승인_성공")
    void SUCCESS_APPROVE() {

        Long userId = 23L;
        int boardId = 25;

        boardService.checkPoster(userId, boardId);
        boardService.checkBoardStatus(boardId);
        reserveMapper.approveReservationRequest(
                ReserveApprovalUpdateDto.builder()
                        .id(8)
                        .requestStatus(APPROVED)
                        .build()
        );

        boardService.changeToReservedBoard(boardId);

    }

}
