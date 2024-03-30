package com.favorite.project.ReserveRequest;

import com.favorite.project.ReserveRequest.dto.ReserveApprovalRequestDto;
import com.favorite.project.ReserveRequest.dto.ReserveRequestAddDto;
import com.favorite.project.User.domain.User;
import com.favorite.project.Validator.SessionConst;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/reserveRequest")
@RequiredArgsConstructor
public class ReserveRequestController {

    private final ReserveService reserveService;

    //예약요청
    @PostMapping
    public void createReserveRequest(@RequestBody ReserveRequestAddDto reserveRequestAddDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.USER_SESSION_ID);
        reserveService.receiveReservationRequest(reserveRequestAddDto, user);

    }

    //예약요청 승인
    @PostMapping("/approve")
    public ResponseEntity<Object> approveReserveRequest(@RequestBody ReserveApprovalRequestDto reserveApprovalRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.USER_SESSION_ID);
        reserveService.approveReservationRequest(reserveApprovalRequestDto, user);
        return new ResponseEntity(HttpStatus.OK);
    }


}
