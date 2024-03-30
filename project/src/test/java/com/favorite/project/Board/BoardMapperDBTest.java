package com.favorite.project.Board;

import com.favorite.project.Board.Mapper.BoardMapper;
import com.favorite.project.Board.dto.BoardStatusToReservedDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardMapperDBTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void 게시물작성자확인() {

        Long userId = 23L;
        int boardId = 26;
        boolean result = boardMapper.checkBoardPoster(userId, boardId);
        Assertions.assertThat(result).isEqualTo(true);


    }

    @Test
    void 보드상태수정테스트() {
        BoardStatus reserved = BoardStatus.RESERVED;
        BoardStatusToReservedDto statusToReservedDto = BoardStatusToReservedDto.builder()
                .boardId(24)
                .boardStatus(reserved)
                .build();

        boardMapper.changeToReserved(statusToReservedDto);
    }

}
