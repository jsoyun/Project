package com.favorite.project.Board;

import lombok.Getter;

@Getter
public enum BoardStatus {
    AVAILABLE("예약가능"),
    RESERVED("예약완료"),
    SOLD("판매완료"),
    RENTED("대여중");

    private final String description;

    BoardStatus(String description) {
        this.description = description;
    }


}
