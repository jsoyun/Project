package com.favorite.project.ReserveRequest.Mapper;

import com.favorite.project.ReserveRequest.ReserveApprovedDto;
import com.favorite.project.ReserveRequest.ReserveRequestStatus;
import com.favorite.project.ReserveRequest.domain.ReserveRequest;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalResponseDto;
import com.favorite.project.ReserveRequest.dto.ReserveApprovalUpdateDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReserveMapper {

    @Insert("INSERT INTO ReserveRequest(requester_id, board_id, requestTime, requestStatus, amount) VALUES (#{reserveRequest.requesterId},#{reserveRequest.boardId},#{reserveRequest.requestTime},#{reserveRequest.requestStatus}, #{reserveRequest.amount})")
    int createReservationRequest(@Param("reserveRequest") ReserveRequest reserveRequest);

    @Update("UPDATE ReserveRequest SET requestStatus = #{ReserveApprovalUpdateDto.requestStatus} where id = #{ReserveApprovalUpdateDto.id} ")
    int approveReservationRequest(@Param("ReserveApprovalUpdateDto") ReserveApprovalUpdateDto reserveApprovalUpdateDto);

    @Select("SELECT requesterId, boardId, requestTime, requestStatus, amount FROM ReserveRequest WHERE id = #{id}")
    ReserveApprovedDto findReserveApprovedDtoById(int id);

}
