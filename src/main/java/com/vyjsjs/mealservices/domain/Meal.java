package com.vyjsjs.mealservices.domain;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< Updated upstream
import java.time.LocalDate;
import java.time.LocalDateTime;
=======
import  java.time.LocalDate;
import  java.time.LocalDateTime;
>>>>>>> Stashed changes

/**
 * 식사 기록(Meal) 정보를 나타내는 JPA Entity 클래스입니다.
 * 데이터베이스의 'meal' 테이블과 매핑됩니다.
 */
<<<<<<< Updated upstream
@Entity  // 1. 이 클래스가 JPA 엔티티임을 명시
@Table(name = "meal")  // 2. 매핑될 테이블 이름 지정 (선택 사항, 클래스명과 다를 때 사용)
@Getter  // 3. Lombok: 모든 필드의 Getter 메서드를 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 4. Lombok: JPA를 위한 기본 생성자 자동 생성 (protected 접근 제한)
@ToString  // 5. Lombok: 객체 내용을 쉽게 출력하기 위한 toString 메서드 자동 생성
public class Meal {

    @Id // 6. 기본 키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 7. 기본 키 생성 전략: DB가 자동으로 값을 증가시킴
    private Long id;

    @Column(nullable = false, length = 50)  // DB 컬럼 설정: NULL 불가능, 최대 길이 50
    private String mealName;  // 식사 구분 (예: 아침, 점심, 저녁)

    @Column(nullable = false, length = 255)
    private String menuDescription; // 메뉴 상세 설명
=======

@Entity
@Table(name= "meal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)  // DB 컬럼 설정: NULL 불가능, 최대 길이 50)
    private String mealName;  // 식사 구분 (예: 아침, 점심, 저녁)

    @Column(nullable = false, length = 255)
    private String menuDescription;  // 메뉴 상세 설명
>>>>>>> Stashed changes

    // 칼로리는 NULL이 허용되도록 설정 (optional)
    private Integer calories;

    @Column(nullable = false)
    private LocalDate mealDate;  // 식사 기록 날짜

    // 기록 생성 및 수정 시점 자동 관리
    private LocalDateTime createdAt;
<<<<<<< Updated upstream
    private LocalDateTime updateAt;

    // 8. 빌더 패턴을 사용하여 객체를 생성하기 위한 생성자 (Lombok의 @Builder 사용 시 필요)
=======
    private LocalDateTime updatedAt;

    // 외부에서 바로 필드에 접근하지 않고, 이 메서드를 통해 안전하게 수정하도록 합니다.
>>>>>>> Stashed changes
    @Builder
    public Meal(String mealName, String menuDescription, Integer calories, LocalDate mealDate) {
        this.mealName = mealName;
        this.menuDescription = menuDescription;
        this.calories = calories;
        this.mealDate = mealDate;
<<<<<<< Updated upstream
    }

    // 9. Entity의 데이터를 수정하는 메서드
    // 외부에서 바로 필드에 접근하지 않고, 이 메서드를 통해 안전하게 수정하도록 합니다.
    public void update(String mealName, String menuDescription, Integer calories, LocalDate mealDate) {
        this.mealName = mealName;
        this.menuDescription = menuDescription;
        this.calories = calories;
        this.mealDate = mealDate;
        this.updateAt = LocalDateTime.now();  // 수정 시간 업데이트
    }

    // 10. 엔티티 저장 전/수정 전에 호출될 콜백 메서드 (생성/수정 시간 자동화)
=======
        this.updatedAt = LocalDateTime.now();  // 수정 시간 업데이트
    }

>>>>>>> Stashed changes
    @PrePersist  // 저장 전에 실행
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate  // 업데이트 전에 실행
    public void preUpdate() {
<<<<<<< Updated upstream
        this.updateAt = LocalDateTime.now();
    }

=======
        this.updatedAt = LocalDateTime.now();
    }
>>>>>>> Stashed changes
}
