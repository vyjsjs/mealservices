package com.vyjsjs.mealservices.controller;

import com.vyjsjs.mealservices.dto.MealResponse;
import com.vyjsjs.mealservices.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 9주차: Thymeleaf 연동을 위한 View Controller
 * 웹 브라우저에게 HTML 화면(View)을 반환합니다.
 */
@Controller
@RequiredArgsConstructor
public class MealViewController {

    private final MealService mealService;

    // 목록 페이지 렌더링
    // 주소: http://localhost:8080/meals
    @GetMapping("/meals")
    public String viewMealList(Model model) {
        // 1. 서비스에서 데이터 조회
        List<MealResponse> meals = mealService.findAllMeals();

        // 2. 뷰(HTML)에 데이터 전달 ("meals"라는 이름으로 전달)
        model.addAttribute("meals", meals);

        // 3. 템플릿 파일 이름 반환 (resources/templates/meals/list.html)
        return "meals/list";
    }

    // 상세 페이지 렌더링
    // 주소: http://localhost:8080/meals/1
    @GetMapping("/meals/{id}")
    public String viewMealDetail(@PathVariable Long id, Model model) {
        // 1. 서비스에서 특정 ID의 데이터 조회
        MealResponse meal = mealService.findMealById(id);

        // 2. 뷰에 데이터 전달
        model.addAttribute("meal", meal);

        // 3. 템플릿 파일 이름 반환 (resources/templates/meals/detail.html)
        return "meals/detail";
    }
}
