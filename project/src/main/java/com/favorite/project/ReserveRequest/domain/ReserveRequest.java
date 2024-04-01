package com.favorite.project.ReserveRequest.domain;

import com.favorite.project.ReserveRequest.ReserveRequestStatus;
import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ReserveRequest {
    private Long requesterId;
    private int boardId;
    private LocalDateTime requestTime;
    private ReserveRequestStatus requestStatus;
    private BigDecimal amount;


}
