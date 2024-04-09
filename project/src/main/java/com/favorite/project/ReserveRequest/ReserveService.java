package com.favorite.project.ReserveRequest;

import com.favorite.project.Board.BoardService;
import com.favorite.project.ReserveRequest.Mapper.ReserveMapper;
import com.favorite.project.ReserveRequest.domain.ReserveRequest;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalRequestDto;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalResponseDto;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalUpdateDto;
import com.favorite.project.ReserveRequest.dto.ReserveRequestAddDto;
import com.favorite.project.User.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.favorite.project.ReserveRequest.ReserveRequestStatus.APPROVED;
import static com.favorite.project.ReserveRequest.ReserveRequestStatus.PENDING;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveMapper reserveMapper;
    private final BoardService boardService;

    //예약 요청
    public void receiveReservationRequest(ReserveRequestAddDto requestAddDto, User user) {
        ReserveRequest reserveRequest = ReserveRequest.builder()
                .requesterId(user.getId())
                .boardId(requestAddDto.getBoardId())
                .requestTime(requestAddDto.getRequestTime())
                .requestStatus(PENDING)  //처음생성시 "요청대기중"
                .amount(requestAddDto.getAmount())
                .build();
        reserveMapper.createReservationRequest(reserveRequest);
        //TODO: 예약요청건 생성 -> 판매자한테 알람가야함!!!
        // 알람도메인에게 알려줌.

    }


    @Transactional
    public ReserveApprovalResponseDto approveReservationRequest(ReserveApprovalRequestDto reserveApprovalRequestDto, User user) {
        int boardId = reserveApprovalRequestDto.getBoardId();
        boardService.checkPoster(user.getId(), boardId);
        boardService.checkBoardStatus(boardId);
        reserveMapper.approveReservationRequest(
                ReserveApprovalUpdateDto.builder()
                        .id(reserveApprovalRequestDto.getReserveRequestId())
                        .requestStatus(APPROVED)
                        .build()
        );

        ReserveApprovedDto reserveApprovedDtoById =
                reserveMapper.findReserveApprovedDtoById(reserveApprovalRequestDto.getReserveRequestId());

        boardService.changeToReservedBoard(reserveApprovalRequestDto.getBoardId());

        return ReserveApprovalResponseDto.builder().build().toResponseDto(reserveApprovedDtoById);


    }


}
