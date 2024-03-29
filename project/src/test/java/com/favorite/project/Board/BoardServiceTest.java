package com.favorite.project.Board;

import com.favorite.project.Board.dto.BoardAddDto;
import com.favorite.project.Clothes.Mapper.ClothesMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private ClothesMapper clothesMapper;
    @InjectMocks
    private BoardService boardService;


    @Test
    @DisplayName("잘못된_옷_정보를_게시판에_올려서_실패")
    public void postWrongClothesId() {

        BoardAddDto boardAddDto = BoardAddDto.builder().clothesId(1).build();
        when(clothesMapper.checkClothesById(boardAddDto.getClothesId())).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> boardService.postBoard(boardAddDto));

    }


}
