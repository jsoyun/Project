package com.favorite.project.ReserveRequest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReserveApprovalRequestDto {
    private int reserveRequestId;
    private int boardId;

}
