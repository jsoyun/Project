package com.favorite.project.Clothes.dto;

import com.favorite.project.Clothes.SeasonType;
import com.favorite.project.ClothesCategory.ClothesCategoryEnumType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ClothesResponseDto {
    private ClothesCategoryEnumType clothesCategoryType;
    private int price;
    private String imgUrl;
    private LocalDate purchasedDate;
    private SeasonType seasonType;
    private int userId;
    private int categoryId;
    private int userClosetId;

}
