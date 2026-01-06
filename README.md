# 🥗 MealService (식단 관리 홈 서버 프로젝트)

### "나만의 홈 서버에서 돌아가는 CRUD 식단 관리 서비스"

## 1. 프로젝트 개요
* **프로젝트명:** MealService
* **설명:** 매일의 식사를 기록하고 관리하는 웹 애플리케이션입니다. 로컬 개발 환경을 넘어, **직접 구축한 리눅스 홈 서버(Raspberry Pi)**에 배포하여 24시간 중단 없이 서비스를 운영하는 과정을 통해 웹 개발의 전 과정(개발-DB구축-배포)을 학습했습니다.
* **개발 기간:** 15주 (로드맵 기반 단계별 학습)

## 2. ⚒️ 사용 기술 스택 (Tech Stack)

### Backend
* **Java 17**
* **Spring Boot 3.5.6**
* **Spring Data JPA**
* **Gradle**

### Database
* **MySQL / MariaDB** (Production - 라즈베리파이 서버)
* **H2 Database** (Development - 로컬 테스트용)

### Frontend
* **Thymeleaf** (Server-side Templating)
* **HTML5 / CSS**

### Infrastructure & DevOps
* **Raspberry pi 4B** (Ubuntu Server / Linux)
* **Git / GitHub**

## 3. ✨주요 기능
1.  **식단 기록 (Create):** 아침, 점심, 저녁 메뉴와 칼로리 정보를 기록합니다.
2.  **식단 조회 (Read):** 저장된 식단 목록을 리스트 형태로 조회하고, 상세 내용을 확인합니다.
3.  **식단 수정 (Update):** 잘못 입력된 메뉴나 칼로리 정보를 수정합니다.
4.  **식단 삭제 (Delete):** 불필요한 기록을 삭제합니다.
5.  **무중단 배포:** `nohup`을 활용하여 SSH 접속이 끊겨도 서버가 계속 동작하도록 설정했습니다.

## 4. 🚀 아키텍처 및 배포 구조
* **Local (Mac M2):** IntelliJ IDEA + MySQL 개발 환경
* **Server (Raspberry Pi 4):** Linux 환경에 Java와 MariaDB를 직접 설치하고, 빌드된 JAR 파일을 전송하여 구동.

## 5. 📸 실행 화면
<img width="640" height="192" alt="1 메인화면" src="https://github.com/user-attachments/assets/042e97eb-85eb-4702-ac33-e8f27d008818" />
<img width="642" height="289" alt="2 상세화면" src="https://github.com/user-attachments/assets/113e90ae-c963-4fac-906a-51f86f0066d9" />
<img width="329" height="203" alt="3 수정화면" src="https://github.com/user-attachments/assets/9fbc8aa5-a428-474c-b0e3-ec9aadb1e493" />

## 6. 🔥 트러블 슈팅 & 배운 점 (Retrospective)

### 1) H2에서 MySQL로의 전환
* **문제:** 초기에는 인메모리 DB인 H2를 사용하여 서버 재시작 시 데이터가 휘발되는 문제가 있었음.
* **해결:** `application.properties` 설정을 변경하고 로컬 및 서버에 MySQL(MariaDB)을 구축하여 영구 저장소로 전환함. `ddl-auto` 옵션을 `update`로 설정하여 데이터 보존성을 확보함.

### 2) 라즈베리파이 배포와 Jar 파일 문제
* **문제:** `scp`로 빌드 파일을 전송했으나 `Invalid or corrupt jarfile` 에러가 발생함.
* **원인:** `build/libs` 내에 라이브러리가 포함되지 않은 `plain.jar`(19KB)가 전송되었거나, 와일드카드(`*`) 사용으로 잘못된 파일이 실행됨.
* **해결:** `bootJar`로 생성된 50MB 이상의 Fat Jar 파일을 명시적으로 지정하여 전송함으로써 해결.

### 3) 백그라운드 실행
* **학습:** 터미널 종료 시 서버가 꺼지는 현상을 막기 위해 `nohup` 명령어와 `&` (백그라운드 연산자)를 사용하여 데몬 형태로 서버를 띄우는 법을 익힘.

---
*Developed by vyjsjs* 
