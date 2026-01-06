package com.vyjsjs.mealservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 간단 식단 관리 웹 서비스의 메인 애플리케이션 클래스입니다.
 * Spring Boot 애플리케이션을 실행하는 역할을 합니다.
 */

@SpringBootApplication
public class MealServicesApplication {
	public static void main(String[] args){
		// 애플리케이션 실행
		SpringApplication.run(MealServicesApplication.class, args);

		// 서버가 정상적으로 시작되면 콘솔에 메시지가 출력됩니다.
		System.out.println("--- Meal Services Started Successfully! ---");
	}
}
