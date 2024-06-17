package com.favorite.project.Clothes;

import com.favorite.project.Clothes.dto.*;
import com.favorite.project.ClothesCategory.ClothesCategoryEnumType;
import com.favorite.project.User.domain.User;
import com.favorite.project.UserCloset.UserClosetValidator;
import com.favorite.project.Validator.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {

    private final ClothesService clothesService;
    private final UserClosetValidator userClosetValidator;


    @PostMapping
    public ResponseEntity<ClothesInsertionResponseDto> addClothes(@RequestBody ClothesAddDto clothesAddDto, HttpServletRequest request) {
        // TODO: 유효한 옷타입 카테고리인지 검증
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(SessionConst.USER_SESSION_ID);
            userClosetValidator.checkUserBySession(user.getId());
            ClothesInsertionResponseDto clothesInsertionResponseDto = clothesService.addClothes(clothesAddDto);
            return new ResponseEntity<>(clothesInsertionResponseDto, HttpStatus.OK);

        }
        throw new NullPointerException("세션 정보가 없습니다.");

    }

    @PostMapping("/userClosetId")
    public ResponseEntity<List<UserClosetResponseDto>> getClothesByCloset(@RequestBody UserClosetIdRequestDto userClosetId, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<UserClosetResponseDto> clothesResponseDtoList = clothesService.getAllClothesByUserClosetId(userClosetId.getUserClosetId(), page, pageSize);
        if (clothesResponseDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clothesResponseDtoList, HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<ClothesTotalListResponse>> getClothes(@RequestParam(required = false) SeasonType seasonType,
                                                                     @RequestParam(required = false) ClothesCategoryEnumType clothesCategoryEnumType,
                                                                     @RequestParam(required = false) Double minPrice,
                                                                     @RequestParam(required = false) Double maxPrice
    ) {
        List<ClothesTotalListResponse> clothesList = clothesService.getClothes(seasonType, clothesCategoryEnumType, minPrice, maxPrice);
        if (clothesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clothesList, HttpStatus.OK);

    }


}
