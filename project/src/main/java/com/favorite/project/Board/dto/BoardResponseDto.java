package com.favorite.project.Board.dto;

import com.favorite.project.Board.domain.Board;
import com.favorite.project.Board.BoardStatus;
import com.favorite.project.Board.BoardType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardResponseDto {
    private LocalDateTime createdAt;
    private int userId;
    private BoardType boardType;
    private int clothesId;
    private double price;
    private int rentalHours;
    private String content;
    private String imageUrl;
    private String location;
    private BoardStatus status;

    public BoardResponseDto toBoardResponse(Board board) {
        return BoardResponseDto.builder().createdAt(board.getCreatedAt())
                .boardType(board.getBoardType())
                .clothesId(board.getClothesId())
                .price(board.getPrice())
                .rentalHours(board.getRentalHours())
                .content(board.getContent())
                .imageUrl(board.getImageUrl())
                .location(board.getLocation())
                .status(board.getStatus())
                .build();

    }

}
