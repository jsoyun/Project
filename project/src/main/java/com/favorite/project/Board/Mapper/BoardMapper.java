package com.favorite.project.Board.Mapper;

import com.favorite.project.Board.domain.Board;
import com.favorite.project.Board.dto.BoardStatusToReservedDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO Board (created_at,user_id,board_type, clothes_id, price, rental_Hours, content, image_url, location,status) VALUES (#{board.createdAt},#{board.userId}, #{board.boardType}, #{board.clothesId}, #{board.price} , #{board.rentalHours},#{board.content}, #{board.imageUrl}, #{board.location}, #{board.status} )")
    int insertBoard(@Param("board") Board board);

    @Select("SELECT count(id) from Board where id =#{boardId} and user_id =#{userId}")
    boolean checkBoardPoster(@Param("userId") Long userId, @Param("boardId") int boardId);

    @Update("UPDATE Board SET status = #{boardStatusToReservedDto.boardStatus} WHERE id =#{boardStatusToReservedDto.boardId}   ")
    boolean changeToReserved(@Param("boardStatusToReservedDto") BoardStatusToReservedDto boardStatusToReservedDto);

    @Select("SELECT EXISTS( SELECT id from Board where id = #{boardId} and status= 'AVAILABLE')")
    boolean checkBoardStatusIsAvailable(int boardId);
}
