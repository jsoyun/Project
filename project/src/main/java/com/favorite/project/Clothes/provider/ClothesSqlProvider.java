package com.favorite.project.Clothes.provider;

import com.favorite.project.Clothes.SeasonType;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClothesSqlProvider {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String selectClothes(SeasonType seasonType, int clothesType, Double minPrice, Double maxPrice) {
        logger.info("selectClothes 쿼리 실행됨====" + clothesType);
        return new SQL() {{
            SELECT(" userCloset_id,clothes_category_id, price, img,season");
            FROM("Clothes");
            if (seasonType != null) {
                WHERE("season = #{seasonType}");
            }
            if (clothesType != 0) {
                WHERE("clothes_category_id = #{clothesType}");
            }
            if (minPrice != null && maxPrice != null) {
                WHERE("price BETWEEN #{minPrice} AND #{maxPrice}");
            } else if (minPrice != null) {
                WHERE("price >= #{minPrice}");
            } else if (maxPrice != null) {
                WHERE("price <= #{maxPrice}");
            }

        }}.toString();


    }
}
