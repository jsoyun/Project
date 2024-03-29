package com.favorite.project.Board.domain;

import com.favorite.project.Board.BoardStatus;
import com.favorite.project.Board.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
public class Board {
    private LocalDateTime createdAt;
    @NotNull
    @Setter
    private Long userId;
    private BoardType boardType; //RENT일 경우 시간 필수기재 , SALE retalHours가 limit 무한사용가능
    @NotNull
    @Setter
    private int clothesId;
    private double price;
    private int rentalHours;
    private String content;
    private String imageUrl;
    private String location;
    private BoardStatus status; //기본적으로 예약가능  AVAILABLE 이다

}
