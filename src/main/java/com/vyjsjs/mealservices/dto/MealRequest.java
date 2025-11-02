package com.vyjsjs.mealservices.dto;

import com.vyjsjs.mealservices.domain.Meal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 식사 기록 생성 및 수정을 위한 요청 DTO (Data Transfer Object).
 * 클라이언트(사용자)로부터 받는 데이터를 정의합니다.
 */

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
public class MealRequest {

    private String mealName;  // 식사 구분 (아침, 점심, 저녁)
    private String menuDescription;  // 메뉴 상세 설명
    private Integer calories;  // 칼로리
    private String mealDate;  // 식사 날짜 (문자열 형태로 받음)

    /**
     * DTO의 데이터를 Meal Entity로 변환하여 반환하는 메서드.
     * Service 계층에서 이 메서드를 호출하여 DB에 저장할 엔티티를 생성합니다.
     */
    public Meal toEntity() {
        return Meal.builder()
                .mealName(this.mealName)
                .menuDescription(this.menuDescription)
                .calories(this.calories)
                // String으로 받은 날짜를 LocalDate로 변환
                .mealDate(LocalDate.parse(this.mealDate))
                .build();
    }

}
