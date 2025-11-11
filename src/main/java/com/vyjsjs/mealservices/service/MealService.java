package com.vyjsjs.mealservices.service;

import com.vyjsjs.mealservices.domain.Meal;
import com.vyjsjs.mealservices.dto.MealRequest;
import com.vyjsjs.mealservices.dto.MealResponse;
import com.vyjsjs.mealservices.repository.MealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 식사 기록에 대한 비지니스 로직을 처리하는 서비스 클래스입니다.
 * Repository와 Controller 사이에서 데이터를 처리하고 비즈니스 규칙을 적용합니다.
 */
@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    // --- C (Create) ---
    /**
     * 새로운 식사 기록을 등록합니다.
     */
    @Transactional
    public MealResponse createMeal(MealRequest request) {
        Meal meal = request.toEntity();
        Meal savedMeal = mealRepository.save(meal);
        return new MealResponse(savedMeal);
    }

    // --- R (Read - List) ---
    /**
     * 모든 식사 기록 목록을 조회합니다.
     */
    public List<MealResponse> findAllMeals() {
        // DB에서 모든 Meal Entity를 조회한 후, 스트림을 이용해 MealResponse 리스트 변환합니다.
        return mealRepository.findAll().stream()
                .map(MealResponse::new)  // Meal Entity 하나당 MealResponse 객체로 변환
                .collect(Collectors.toList());
    }

    // --- R (Read - Single) ---
    /**
     * 특정 ID의 식사 기록 하나를 조회합니다.
     */
    public MealResponse findMealById(Long id) {
        // ID로 찾고, 만약 없다면 예외를 발생시킵니다.
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));
        return new MealResponse(meal);
    }

    // --- U (Update) ---

    /**
     * 특정 ID의 식사 기록을 수정합니다.
     */
    @Transactional
    public MealResponse updateMeal(Long id, MealRequest request) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));

        // JPA의 @Transactional 덕분에 Repository.save()를 호출하지 않아도 변경사항 DB에 반영됩니다.
        meal.update(
                request.getMealName(),
                request.getMenuDescription(),
                request.getCalories(),
                LocalDate.parse(request.getMealDate())
        );

        return new MealResponse(meal);
    }

    // --- D (Delete) ---
    /**
     * 특정 ID의 식사 기록을 삭제합니다.
     */
    public void deleteMeal(Long id){
        // ID에 해당하는 기록이 있는지 확인하고 삭제합니다.
        mealRepository.deleteById(id);
    }


}
