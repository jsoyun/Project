package com.favorite.project.Board;

import lombok.Getter;

@Getter
public enum BoardType {
    RENT("대여"),
    SALE("판매");

    private final String description;

    BoardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
