# 🍽️ Simple Meal Management Service (식단 관리 웹 서버)

## ✨ 프로젝트 개요

| **구분** | **내용** |
| --- | --- |
| **프로젝트 목적** | Java Spring Boot를 활용한 **CRUD 기능** 구현을 통해 서버 개발자로서의 기본 역량 확보 및 실무 기반 다지기 |
| **개발 기간** | 2025년 2학기 (총 15주) |
| **주요 기능** | 사용자가 일일 식사 기록 (메뉴, 칼로리, 시간대)을 등록, 조회, 수정, 삭제하는 웹 API 및 화면 제공 |
| **개발자** | 경희대학교 컴퓨터공학과 2학년 (서버 개발자 지망생) |

## 🛠️ 기술 스택 (Tech Stack)

프로젝트에 사용된 핵심 기술 스택 및 역할입니다.

### 백엔드 & DB

| **카테고리** | **기술** | **역할** |
| --- | --- | --- |
| **언어** | `Java 17` | 서버 개발 메인 언어 |
| **프레임워크** | `Spring Boot 3.x` | 백엔드 서버 구축 및 환경 설정 자동화 |
| **데이터 접근** | `Spring Data JPA` | 객체-관계 매핑 (ORM)을 통한 DB 연동 |
| **데이터베이스** | `H2 Database` | 개발 및 테스트용 인메모리(In-Memory) 데이터베이스 |
| **템플릿 엔진** | `Thymeleaf` | 서버 사이드 렌더링을 위한 View 계층 구축 |
| **의존성 관리** | `Lombok` | Getter, Setter, 생성자 등 반복 코드 최소화 |

### 개발 환경 & 협업

| **카테고리** | **도구** | **역할** |
| --- | --- | --- |
| **빌드 도구** | `Gradle` | 프로젝트 빌드 및 의존성 관리 |
| **버전 관리** | `Git` / `GitHub` | 분산 버전 관리 시스템 및 원격 저장소 |
| **문서화** | `Notion` | 주간 개발 일지 및 학습 내용 기록 |

## 🏗️ 시스템 아키텍처 (Layered Architecture)

프로젝트는 유지보수성과 확장성을 위해 계층형 아키텍처를 기반으로 설계되었습니다.

- **Controller Layer:** HTTP 요청/응답 처리 (API 인터페이스)
- **Service Layer:** 핵심 비즈니스 로직 처리 (4주차 구현 완료)
- **Repository Layer:** DB 접근 및 CRUD 작업 수행 (3주차 구현 완료)
- **Domain & DTO:** 데이터 모델 및 데이터 전송 객체 (3, 4주차 구현 완료)

## 🗺️ 개발 로드맵 및 현재 상태

| **주차** | **주요 목표** | **진행 상태** | **커밋 메시지** |
| --- | --- | --- | --- |
| **1주차** | Spring Boot 환경 설정 및 프로젝트 초기화 | ✅ 완료 | `feat: Initial Spring Boot project setup and configuration` |
| **2주차** | Git/GitHub 버전 관리 시스템 구축 | ✅ 완료 | `docs: Add README and set up Git workflow` |
| **3주차** | JPA Entity 및 Repository 구현 (DB 기반 마련) | ✅ 완료 | `feat: Configure H2 Database and Implement Meal Entity and Repository` |
| **4주차** | **DTO 및 Service 계층 구현 (비즈니스 로직 완성)** | ✅ 완료 | `feat: Implement MealService with CRUD logic and DTOs` |
| **5주차** | Controller 계층 구현 및 REST API 완성 | 🔜 진행 예정 | - |
| **6주차** | Thymeleaf 연동 (최초 View 화면 구성) | 🔜 진행 예정 | - |
| **7주차 이후** | 유효성 검사, 예외 처리, 테스트 코드 작성 등 | 🔜 진행 예정 | - |

## 🚀 로컬 환경 실행 가이드

### ⚙️ 실행 요구사항

- Java JDK 17+
- Git
- IntelliJ IDEA (권장)

### 1. 프로젝트 클론

```
git clone [본인 GitHub 저장소 URL]
cd mealsevices

```

### 2. 애플리케이션 실행

IntelliJ IDEA에서 프로젝트를 열고 `MealSevicesApplication.java`의 `main` 메서드를 실행합니다.

혹은 터미널에서 다음 명령어를 사용합니다.

```
./gradlew bootRun

```

### 3. DB 콘솔 접속 확인

서버 구동 후, 브라우저에서 H2 콘솔에 접속하여 테이블 생성 여부를 확인할 수 있습니다.

| **항목** | **값** |
| --- | --- |
| **H2 Console URL** | `http://localhost:8080/h2-console` |
| **JDBC URL** | `jdbc:h2:mem:mealdb` |
| **User Name** | `sa` |
