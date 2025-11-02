package com.vyjsjs.mealservices.dto;

import com.vyjsjs.mealservices.domain.Meal;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 식사 기록 조회 결과를 위한 응답 DTO.
 * 서버에서 클라이언트(사용자)에게 전달할 데이터를 정의합니다.
 */
@Getter
public class MealResponse {
    private Long id;
    private String mealName;
    private String menuDescription;
    private Integer calories;
    private LocalDate mealDate;
    private LocalDateTime createdAt;

    /**
     * Entity를 받아 Response DTO 객체를 생성하는 생성자.
     * 이를 통해 Entity와 Presentation 계층이 분리됩니다.
     */
    public MealResponse(Meal meal) {
        this.id = meal.getId();
        this.mealName = meal.getMealName();
        this.menuDescription = meal.getMenuDescription();
        this.calories = meal.getCalories();
        this.mealDate = meal.getMealDate();
        this.createdAt = meal.getCreatedAt();
        // upadatedAt 필드는 민감할 수 있어 제외했습니다.
    }
}
