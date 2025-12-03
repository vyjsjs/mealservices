package com.vyjsjs.mealservices.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // 모든 컨트롤러에서 발생하는 예외를 감시합니다.
public class GlobalExceptionHandler {

    // MealNotFoundException이 발생하면 이 메서드가 가로챕니다.
    @ExceptionHandler(MealNotFoundException.class)
    public String handleMealNotFound(MealNotFoundException e, Model model) {
        // 에러 메시지를 모델에 담아서
        model.addAttribute("errorMessage", e.getMessage());
        // error/404.html 페이지를 보여줍니다.
        return "error/404";
    }
}
