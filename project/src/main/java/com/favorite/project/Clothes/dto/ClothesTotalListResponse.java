package com.favorite.project.Clothes.dto;

import com.favorite.project.Clothes.SeasonType;
import com.favorite.project.ClothesCategory.ClothesCategoryEnumType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClothesTotalListResponse {
    private int userClosetId;
    private String clothesCategoryEnumTypeName;
    private int price;
    private String img;
    private SeasonType season;
}
