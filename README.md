# 📆 Sparta_Schedule
스파르타코딩 내일배움캠프 - 일정 관리 프로젝트

## 📌 프로젝트 소개
Java - Spring 프레임워크를 사용하여 일정을 관리하는 앱의 서버 환경을 구현합니다.

## 🛠 개발 환경
- `Java`
- `Oracle OpenJDK 17.0.5`
- 프레임워크 : `Spring Boot 3.4.5`
- 데이터베이스 : `MySQL 8.0.42`
- IDE : `IntelliJ IDEA`

## 🔍 주요 기능
일정 CRUD
- 일정 생성
  - 할 일, 작성자, 비밀번호 저장
  - id, 작성일 자동 생성
- 일정 조회
  - 전체 목록 조회 (작성자명/수정일 필터링 가능)
  - id 기반 단건 일정 조회
- 일정 수정
  - 할일, 작성자명 단건 일정 수정 가능
  - 비밀번호 일치 여부 검증
- 일정 삭제
  - 단건 일정 삭제
  - 비밀번호 일치 여부 검증

--------------------
## 📋 API 명세서
아래는 주요 기능을 요약하여 정리한 표입니다.  
| 기능            | Method       | URL                   | Request        | reponse       | 상태코드      |
|-----------------|--------------|-----------------------|----------------|---------------|---------------|
| 일정 등록        | POST         | /schedule             | 요청 body      | 등록 정보      | 201 : Created |
| 일정 전체 조회   | GET          | /schedule             | 요청 param     | 다건 응답 정보 | 200 : OK      |
| 일정 목록 조회   | GET          | /schedule/find-param  | 요청 param     | 다건 응답 정보 | 200 : OK      |
| 일정 단건 조회   | GET          | /schedule/{id}        | 요청 param     | 단건 응답 정보 | 200 : OK      |
| 일정 수정        | PATCH        | /schedule/{id}        | 요청 body      | 단건 응답 정보 | 200 : OK      |
| 일정 삭제        | DELETE       | /schedule/{id}        | 요청 body      | -             | 200 : OK      |

**자세한 요청/응답 스펙과 예시는 [Postman 문서](https://documenter.getpostman.com/view/30877327/2sB2qUmjBx)에서 확인할 수 있습니다.**

--------------------
## 🧾 ERD
사용자가 등록한 일정 정보를 저장하고 관리하기 위한 `schedule` 테이블의 구조입니다.

![image](https://github.com/user-attachments/assets/0b2d8f05-6701-4f96-92d6-ed787b42f4f6)
