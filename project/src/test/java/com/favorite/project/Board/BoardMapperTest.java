package com.favorite.project.Board;

import com.favorite.project.Board.Mapper.BoardMapper;
import com.favorite.project.Board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BoardMapperTest {

    @Mock
    private BoardMapper boardMapper;

    @Test
    @DisplayName("게시판 추가하기 성공")
    public void InsertBoardSuccessTest() {
        Board board = Board.builder()
                .createdAt(LocalDateTime.now())
                .boardType(BoardType.RENT)
                .clothesId(123)
                .price(50.0)
                .rentalHours(4)
                .content("This is a sample content.")
                .imageUrl("https://example.com/image.jpg")
                .location("Sample Location")
                .status(BoardStatus.AVAILABLE)
                .build();

//        given
        given(boardMapper.insertBoard(board)).willReturn(1);

//        when
        boardMapper.insertBoard(board);
//        then
        Assertions.assertThat(boardMapper.insertBoard(board)).isEqualTo(1);


    }
}
