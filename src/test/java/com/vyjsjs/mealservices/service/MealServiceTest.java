package com.vyjsjs.mealservices.service;

import com.vyjsjs.mealservices.domain.Meal;
import com.vyjsjs.mealservices.dto.MealRequest;
import com.vyjsjs.mealservices.dto.MealResponse;
import com.vyjsjs.mealservices.repository.MealRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)  // 1. Mockito 프레임워크 사용 알림
class MealServiceTest {

    @Mock  // 2. 가짜(Mock) Repository 생성 (실제 DB 연결 안 함)
    private MealRepository mealRepository;

    @InjectMocks  // 3. 가짜 Repository를 주입받은 Service 생성
    private MealService mealService;

    @Test
    @DisplayName("식사 기록 생성 성공 테스트")
    void createMeal() {
        // --- Given (준비) ---
        MealRequest request = new MealRequest();
        request.setMealName("점심");
        request.setMenuDescription("제육볶음");
        request.setCalories(700);
        request.setMealDate("2025-05-01");

        // DB에 저장되면 ID가 1인 Meal 객체가 나온다고 가정
        Meal savedMeal = Meal.builder()
                .mealName("점심")
                .menuDescription("제육볶음")
                .calories(700)
                .mealDate(LocalDate.of(2025, 5, 1))
                .build();

        // "repository.save()가 호출되면 savedMeal을 리턴하라"고 가짜 설정
        given(mealRepository.save(any(Meal.class))).willReturn(savedMeal);

        // --- When (실행) ---
        MealResponse response = mealService.createMeal(request);

        // --- Then (검증) ---
        // 반환된 값이 예상과 같은지 확인
        assertThat(response.getMealName()).isEqualTo("점심");
        assertThat(response.getCalories()).isEqualTo(700);

        // save 메서드가 진짜로 1번 호출되었는지 검증
        verify(mealRepository).save(any(Meal.class));
    }

    @Test
    @DisplayName("식사 기록 단건 조회 테스트")
    void findMealById() {
        // --- Given ---
        Long mealId = 1L;
        Meal meal = Meal.builder()
                .mealName("아침")
                .menuDescription("사과")
                .calories(100)
                .mealDate(LocalDate.now())
                .build();

        // "findById(1)이 호출되면 위의 meal을 리턴하라"
        given(mealRepository.findById(mealId)).willReturn(Optional.of(meal));

        // --- When ---
        MealResponse response = mealService.findMealById(mealId);

        // --- Then ---
        assertThat(response.getMealName()).isEqualTo("아침");
        assertThat(response.getMenuDescription()).isEqualTo("사과");
    }

    @Test
    @DisplayName("식사 기록 삭제 테스트")
    void deleteMeal(){
        // --- Given ---
        Long mealId = 1L;

        // -- When ---
        mealService.deleteMeal(mealId);

        // --- Then ---
        // deleteById가 호출되었는지 확인
        verify(mealRepository).deleteById(mealId);
    }
}
