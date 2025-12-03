package com.vyjsjs.mealservices.exception;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(Long id){
        super("해당 ID의 식사 기록을 찾을 수 없습니다. (ID: " + id + ")");
    }
}
