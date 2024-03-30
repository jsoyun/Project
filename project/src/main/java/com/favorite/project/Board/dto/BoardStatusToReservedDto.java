package com.favorite.project.Board.dto;

import com.favorite.project.Board.BoardStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardStatusToReservedDto {
    private BoardStatus boardStatus;
    private int boardId;
}
