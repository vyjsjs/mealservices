package com.vyjsjs.mealservices.controller;

import com.vyjsjs.mealservices.dto.MealRequest;
import com.vyjsjs.mealservices.dto.MealResponse;
import com.vyjsjs.mealservices.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    // --- 11주차 추가 내용 ---
    // 1. 식사 등록 페이지 보여주기 (GET)
    @GetMapping("/meals/new")
    public String createMealForm(Model model) {
        // 빈 DTO를 모델에 담아서 뷰로 전달 (Thymeleaf에서 필드 바인딩 용도)
        model.addAttribute("mealRequest", new MealRequest());
        return "meals/form";  // 등록과 수정을 공유할 폼 템플릿
    }

    // 2. 식사 등록 처리하기 (POST)
    @PostMapping("/meals/new")
    public String createMeal(@ModelAttribute MealRequest mealRequest) {
        // 서비스 호출하여 저장
        mealService.createMeal(mealRequest);
        // 저장 후 목록 페이지로 리다이렉트 (새로고침 시 중복 등록 방지)
        return "redirect:/meals";
    }

    // 3. 식사 수정 페이지 보여주기 (GET)
    @GetMapping("/meals/{id}/edit")
    public String updateMealForm(@PathVariable Long id, Model model) {
        // 기존 데이터 조회
        MealResponse meal = mealService.findMealById(id);

        // Response 데이터를 수정용 Request DTO로 변환하여 폼에 채워넣기
        MealRequest form = new MealRequest();
        form.setMealName(meal.getMealName());
        form.setMenuDescription(meal.getMenuDescription());
        form.setCalories(meal.getCalories());
        form.setMealDate(meal.getMealDate().toString());  // LocalDate -> String

        model.addAttribute("mealRequest", form);
        model.addAttribute("id", id);  // 수정 시 ID가 필요하므로 별도 전달
        return "meals/form";
    }

    // 4. 식사 수정 처리하기 (POST)
    @PostMapping("/meals/{id}/edit")
    public String updateMeal(@PathVariable Long id, @ModelAttribute MealRequest mealRequest) {
        mealService.updateMeal(id, mealRequest);
        return "redirect:/meals/" + id; // 수정 후 상세 페이지로 이동
    }

    // 5. 식사 기록 삭제 처리 (POST)
    // HTML form은 DELETE 메서드를 직접 지원하지 않으므로, PostMapping을 사용합니다.
    @PostMapping("/meals/{id}/delete")
    public String deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/meals";  // 삭제 후 목록 페이지로 이동
    }
}
