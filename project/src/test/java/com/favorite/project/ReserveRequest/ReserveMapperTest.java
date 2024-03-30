package com.favorite.project.ReserveRequest;

import com.favorite.project.ReserveRequest.Mapper.ReserveMapper;
import com.favorite.project.ReserveRequest.domain.ReserveRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.favorite.project.ReserveRequest.ReserveRequestStatus.PENDING;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReserveMapperTest {

    @Mock
    private ReserveMapper reserveMapper;

    @Test
    @DisplayName("SUCCESS_CREATE_ReserveRequest")
    public void 예약요청건_생성_성공() {

        ReserveRequest reserveRequest = ReserveRequest.builder()
                .requesterId(1L)
                .boardId(24)
                .requestTime(LocalDateTime.now())
                .requestStatus(PENDING)
                .amount(BigDecimal.valueOf(1200))
                .build();

        //given
        given(reserveMapper.createReservationRequest(reserveRequest)).willReturn(1);
        //then
        reserveMapper.createReservationRequest(reserveRequest);
        //when
        int reservationRequest = reserveMapper.createReservationRequest(reserveRequest);
        Assertions.assertThat(reservationRequest).isEqualTo(1);

    }
}
