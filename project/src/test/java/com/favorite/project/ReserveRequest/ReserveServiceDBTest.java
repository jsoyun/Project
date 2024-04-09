package com.favorite.project.ReserveRequest;

import com.favorite.project.Board.BoardService;
import com.favorite.project.ReserveRequest.Mapper.ReserveMapper;
import com.favorite.project.ReserveRequest.domain.ReserveRequest;
import com.favorite.project.exception.ReservationNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.favorite.project.ReserveRequest.ReserveRequestStatus.PENDING;

@Slf4j
@SpringBootTest
public class ReserveServiceDBTest {

    @Autowired
    private ReserveMapper reserveMapper;
    @Autowired
    private BoardService boardService;


    @Test
    @Transactional
    @DisplayName("예약요청건_생성")
    void CREATE_ReservationRequest() {
        ReserveRequest reserveRequest = ReserveRequest.builder()
                .requesterId(1L)
                .boardId(26)
                .requestTime(LocalDateTime.now())
                .requestStatus(PENDING)
                .amount(BigDecimal.valueOf(1000))
                .build();

        reserveMapper.createReservationRequest(reserveRequest);


    }


    @Test
    @DisplayName("이미_예약됨_특정예약요청건_승인_실패")
    void FAIL_APPROVE_ReservationRequest() {
        Long userId = 23L;
        int boardId = 24;

        boardService.checkPoster(userId, boardId);
        ReservationNotAllowedException reservationNotAllowedException = Assertions.assertThrows(ReservationNotAllowedException.class, () -> {
            boardService.checkBoardStatus(boardId);
        });

        String message = reservationNotAllowedException.getMessage();
        System.out.println("message = " + message);


    }


}
