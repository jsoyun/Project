package com.favorite.project.ReserveRequest.dto;

import com.favorite.project.ReserveRequest.ReserveApprovedDto;
import com.favorite.project.ReserveRequest.ReserveRequestStatus;
import com.favorite.project.ReserveRequest.domain.ReserveRequest;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class ReserveApprovalResponseDto {
    private int requesterId;
    private int boardId;
    private LocalDateTime requestTime;
    private String requestStatus;
    private BigDecimal amount;

    public ReserveApprovalResponseDto toResponseDto(ReserveApprovedDto reserveApprovedDto) {
        return ReserveApprovalResponseDto
                .builder()
                .requesterId(reserveApprovedDto.getRequesterId())
                .boardId(reserveApprovedDto.getBoardId())
                .requestTime(reserveApprovedDto.getRequestTime())
                .requestStatus(reserveApprovedDto.getRequestStatus())
                .amount(reserveApprovedDto.getAmount())
                .build();
    }

}
