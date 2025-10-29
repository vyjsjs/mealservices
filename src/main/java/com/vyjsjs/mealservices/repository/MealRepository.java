package com.vyjsjs.mealservices.repository;

import com.vyjsjs.mealservices.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 식사 기록(Meal) 엔티티에 대한 데이터 접근을 담당하는 레포지토리 인터페이스입니다.
 * JpaRepository를 상속받는 것만으로 기본적인 CRUD 기능(findAll, findById, save, delete 등)이 자동으로 제공됩니다.
 *
 * JpaRepository<[엔티티 타입], [기본 키(ID) 타입]>
 */
@Repository // 이 인터페이스가 Spring의 Repository 계층임을 명시
public interface MealRepository extends JpaRepository<Meal, Long> {

    // Spring Data JPA의 쿼리 메서드를 통해 추가적인 조회 기능을 정의할 수 있습니다.
    // 예: List<Meal> findByMealDate(LocalDate mealDate); // 날짜로 식사 기록 조회
}
