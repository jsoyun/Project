package com.favorite.project.Clothes;

import com.favorite.project.Clothes.Mapper.ClothesMapper;
import com.favorite.project.Clothes.domain.Clothes;
import com.favorite.project.Clothes.dto.*;
import com.favorite.project.ClothesCategory.ClothesCategoryEnumType;
import com.favorite.project.UserCloset.UserClosetValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesMapper clothesMapper;
    private final UserClosetValidator closetValidator;

    public ClothesInsertionResponseDto addClothes(ClothesAddDto clothesAddDto) {
        Clothes clothes = clothesAddDto.toClothes(clothesAddDto);
        boolean checkValidClosetResult = checkValidCloset(clothes);

        if (checkValidClosetResult) {
            clothesMapper.insertClothes(clothes);
            return clothes.toClothesResponseDto(clothes);

        }
        throw new IllegalArgumentException("유효한 옷장이 아닙니다");


    }


    public boolean checkValidCloset(Clothes clothes) {

        return closetValidator.checkUserCloset(clothes.getUserClosetId());
    }


    public List<UserClosetResponseDto> getAllClothesByUserClosetId(int userClosetId, int page, int pageSize) {
        List<ClothesListDto> clothesListDtos = clothesMapper.selectAllClothesByUserClosetId(userClosetId, page, pageSize);
        List<UserClosetResponseDto> userClosetResponseDtoList = new ArrayList<>();
        for (ClothesListDto clothesListDto : clothesListDtos) {
            UserClosetResponseDto userClosetResponseDTO =
                    UserClosetResponseDto
                            .builder()
                            .build()
                            .toUserClosetIdRequestDto(clothesListDto);
            userClosetResponseDtoList.add(userClosetResponseDTO);
        }

        return userClosetResponseDtoList;

    }


    public List<ClothesTotalListResponse> getClothes(SeasonType seasonType, ClothesCategoryEnumType clothesCategoryEnumType, Double minPrice, Double maxPrice) {
        Integer clothesCategoryId = (clothesCategoryEnumType != null) ? clothesCategoryEnumType.getClothesCategoryId() : null;
        List<ClothesTotalList> clothesTotalLists = clothesMapper.selectClothes(seasonType, clothesCategoryId, minPrice, maxPrice);
        return clothesTotalLists.stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private ClothesTotalListResponse convertToResponseDto(ClothesTotalList clothesListDto) {
        return ClothesTotalListResponse.builder()
                .price(clothesListDto.getPrice())
                .img(clothesListDto.getImg())
                .userClosetId(clothesListDto.getUserClosetId())
                .season(clothesListDto.getSeason())
                .clothesCategoryEnumTypeName(ClothesCategoryEnumType.getClothesCategoryName(clothesListDto.getClothesCategoryEnumTypeId()))
                .build();


    }


}
