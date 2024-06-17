package com.favorite.project.Clothes.dto;

import com.favorite.project.Clothes.SeasonType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClothesTotalList {

    private int userClosetId;
    private int clothesCategoryEnumTypeId;
    private int price;
    private String img;
    private SeasonType season;

}
