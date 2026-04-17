# Java Shopping API

Spring Boot 기반 간단한 쇼핑 API입니다.

## 기술 스택
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- H2 Database (인메모리)

## API 엔드포인트

### 상품
- `GET /api/products` — 상품 목록
- `GET /api/products/{id}` — 상품 상세
- `GET /api/products/search?keyword=` — 상품 검색
- `POST /api/products` — 상품 등록

### 주문
- `GET /api/orders` — 주문 목록
- `POST /api/orders` — 주문 생성 (10개 이상 시 10% 할인)

### 인증
- `POST /api/auth/register` — 회원가입
- `POST /api/auth/login` — 로그인 (평문 비교)

## 실행
```bash
./mvnw spring-boot:run
```
