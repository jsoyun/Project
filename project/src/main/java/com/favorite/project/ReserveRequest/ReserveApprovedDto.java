package com.favorite.project.ReserveRequest;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReserveApprovedDto {
    private int requesterId;
    private int boardId;
    private LocalDateTime requestTime;
    private String requestStatus;
    private BigDecimal amount;
}
