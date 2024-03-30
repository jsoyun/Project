package com.favorite.project.ReserveRequest.dto;

import com.favorite.project.ReserveRequest.ReserveRequestStatus;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ReserveApprovalUpdateDto {
    private int id;

    private ReserveRequestStatus requestStatus;

}
