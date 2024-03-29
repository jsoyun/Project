package com.favorite.project.Board.dto;

import com.favorite.project.Board.domain.Board;
import com.favorite.project.Board.BoardStatus;
import com.favorite.project.Board.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardAddDto {
    private LocalDateTime createdAt;
    private BoardType boardType;
    private int clothesId;
    private double price;
    private int rentalHours;
    private String content;
    private String imageUrl;
    private String location;
    private BoardStatus status;

    @Setter
    private Long userId;

    public Board toBoard(BoardAddDto boardAddDto) {
        return Board.builder().createdAt(boardAddDto.createdAt)
                .boardType(boardAddDto.boardType)
                .userId(boardAddDto.userId)
                .clothesId(boardAddDto.clothesId)
                .price(boardAddDto.price)
                .rentalHours(boardAddDto.rentalHours)
                .content(boardAddDto.content)
                .imageUrl(boardAddDto.imageUrl)
                .location(boardAddDto.location)
                .status(boardAddDto.status)
                .build();

    }

}
