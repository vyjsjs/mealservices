package com.vyjsjs.mealservices.controller;

import com.vyjsjs.mealservices.dto.MealRequest;
import com.vyjsjs.mealservices.dto.MealResponse;
import com.vyjsjs.mealservices.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Presentation Layer (C - V - C)
 *
 * @RestController : @Controller + @ResponseBody. 이 클래스가 REST API 컨트롤러임을 Spring에게 알립니다.
 * 모든 메서드는 뷰(HTML)를 반환하는 대신 JSON/XML 등의 데이터 (Body)를 반환합니다.
 * @RequestMapping("/api/meals") : 이 컨트롤러의 모든 메서드에 대한 공통 URL 접두사를 설정합니다.
 * (예: /api/meals, /api/meals/1)
 * @RequiredArgsConstructor : final로 선언된 필드(mealService)에 대한 생성자를 자동으로 주입합니다. (DI)
 */
@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    // C - Create (식사 기록 생성)
    /**
     * @return ResponseEntity<MealResponse> : 생성된 식사 기록(MealREsponse)과 HTTP 상태 코드 201(Created)을 반환합니다.
     * @PostMapping : HTTP POST 요청을 이 메서드에 매핑합니다. (URL: /api/meals)
     * @RequestBody : HTTP 요청의 본문(Body)에 담긴 JSON 데이터를 MealRequest DTO 객체로 변환합니다.
     */
    @PostMapping
    public ResponseEntity<MealResponse> createMeal(@RequestBody MealRequest request) {
        MealResponse response = mealService.createMeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // R - Read All (모든 식사 기록 조회)
    /**
     * @return ResponseEntity<List < MealResponse>> : 모든 식사 기록 목로고가 HTTP 상태 코드 200(OK)을 반환합니다.
     * @GetMapping : HTTP GET 요청을 이 메서드에 매핑합니다. (URL: /api/meals)
     */
    @GetMapping
    public ResponseEntity<List<MealResponse>> getAllMeals() {
        List<MealResponse> responses = mealService.findAllMeals();
        return ResponseEntity.ok(responses);
    }

    // R - Read One (특정 식사 기록 조회)
    /**
     * @return ResponseEntity<MealResponse> : 조회된 식사 기록과 HTTP 상태 코드 200(OK)을 반환합니다.
     * @GetMapping("/{id}") : HTTP GET 요청을 이 메서드에 매핑합니다. (URL: /api/meals/{id})
     * @PathVariable : URL 경로에 포함된 변수(id)를 메서드의 파라미터로 가져옵니다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id) {
        MealResponse response = mealService.findMealById(id);
        return ResponseEntity.ok(response);
    }

    // U - Update (식사 기록 수정)
    /**
     * @return ResponseEntity<MealResponse> : 수정도니 식사 기록과 HTTP 상태 코드 200(OK)을 반환합니다.
     * @PutMapping("/{id}) : HTTP PUT 요청을 이 메서드에 매핑합니다. (URL: /api/meals/{id})
     */
    @PutMapping("/{id}")
    public ResponseEntity<MealResponse> updateMeal(@PathVariable Long id, @RequestBody MealRequest request) {
        MealResponse response = mealService.updateMeal(id, request);
        return ResponseEntity.ok(response);
    }

    // D - Delete (식사 기록 삭제)
    /**
     * @DeleteMapping("/{id}") : HTTP DELETE 요청을 이 메서드에 매핑합니다. (URL: /api/meals/{id})
     * @return ResponseEntity<Long> : 삭제된 식사 기록의 id와 HTTP 상태 코드 200(OK)을 반환합니다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok(id);  // 삭제 성공 시, 삭제된 ID와 200 OK 응답
    }
}
