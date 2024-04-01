package com.favorite.project.ReserveRequest.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReserveRequestAddDto {
    private int boardId;
    private LocalDateTime requestTime;
    private BigDecimal amount;


}



