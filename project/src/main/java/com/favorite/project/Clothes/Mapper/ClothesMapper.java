package com.favorite.project.Clothes.Mapper;

import com.favorite.project.Clothes.SeasonType;
import com.favorite.project.Clothes.domain.Clothes;
import com.favorite.project.Clothes.dto.ClothesListDto;
import com.favorite.project.Clothes.dto.ClothesTotalList;
import com.favorite.project.Clothes.provider.ClothesSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClothesMapper {
    @Insert("INSERT INTO Clothes (clothes_category_id,season, price,img,purchased_date,userCloset_id) VALUES (#{Clothes.categoryId},#{Clothes.seasonType},#{Clothes.price},#{Clothes.imgUrl},#{Clothes.purchasedDate},#{Clothes.userClosetId})")
    boolean insertClothes(@Param("Clothes") Clothes clothes);

    @Results({
            @Result(property = "clothesCategoryEnumTypeId", column = "clothes_category_id"),
            @Result(property = "purchasedDate", column = "purchased_date"),
            @Result(property = "userClosetId", column = "userCloset_id")
    })

    @Select("SELECT id,userCloset_id,clothes_category_id, price,img, purchased_date,season FROM Clothes WHERE id =#{Id}")
    boolean checkClothesById(@Param("Id") int id);

    @Select("SELECT id,userCloset_id,clothes_category_id, price,img, purchased_date,season  FROM Clothes WHERE userCloset_id = #{UserClosetId}  LIMIT #{pageSize} OFFSET #{page} ")
    List<ClothesListDto> selectAllClothesByUserClosetId(@Param("UserClosetId") int userClosetId, @Param("page") int page, @Param("pageSize") int pageSize);

    @SelectProvider(type = ClothesSqlProvider.class, method = "selectClothes")
    List<ClothesTotalList> selectClothes(@Param("seasonType") SeasonType seasonType, @Param("clothesType") Integer clothesTypeId, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}
