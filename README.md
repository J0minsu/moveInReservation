# ToyProject
###  아파트 입주 일정 관리 서버
***
##### Goals
1. Session 을 이용한 로그인
2. Interceptor 를 활용한 권한, 로깅 시스템
3. JPA를 이용한 RESTFul-apis 구현
4. BCrypt 를 이용한 암호화 
5. AOP 활용
***

##### Contents 
 1. 프로젝트 구조
 2. RDBMS 모델
 3. API 명세
 4. 회고
  ***

###1. 프로젝트 구조
![Structure](https://github.com/J0minsu/moveInReservation/blob/master/documents/Structure.png?raw=true)
######로그인 정보를 서버사이드의 세션에 저장하고, Interceptor를 이용해 비교합니다.
######또한, LogInterceptor로 API의 작동과정을 로그로 남깁니다.
######클라이언트에서는 LocalStorage에 서비스를 제공하기위한 최소한의 클라이언트정보를 저장합니다.

### Technology set
|Technical Name|Value|
|:---:|:---:|
|Language|Java 11|
|Framework|Spring boot 2.x|
|Build|Gradle|
|Encrypt|BCrypt|
|DBMS|MySQL 8.0.18|
|Persistance Framework|JPA|
|Test|JUnit5, Postman|
|IDE|InteliJ|


### Using Dependencies

     'org.springframework.boot:spring-boot-starter-data-jpa'
      'org.springframework.boot:spring-boot-starter-security'
      'org.springframework.boot:spring-boot-starter-web'
      'org.springframework.session:spring-session-core'
      'org.projectlombok:lombok'
      'org.springframework.boot:spring-boot-starter-test'
      'org.springframework.security:spring-security-test'

<br>

***
<br><br>
### 2. REBMS 모델
##### DB ERD
![DB_ERD](https://github.com/J0minsu/moveInReservation/blob/master/documents/DB_ERD.png?raw=true)

#####User
|Column|Type|Description|
|:---:|:---:|:---:|
|phone_number|varchar(20)|pk|
|password|varchar(60)|BCrypt|
|name|varchar||
|ApartmentId|int|fk|

##### Apartment
|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|pk|
|dong|varchar(4)||
|ho|varchar(4)||

##### Reservation
|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|pk|
|userId|varchar(20)|fk|
|reservationTime|date|
|bookingTime|date|

<br>

***
<br><br>
### 3. API 명세
##### User
|Method|Uri( /api/users )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|POST|/register|회원 등록|Void|
##### Login
|Method|Uri|Description|Return Type|
|:---:|:---:|:---:|:---:|
|POST|/login|로그인|User|
|POST|/logout|로그아웃|Void|
##### Apartment
|Method|Uri( /api/apartments )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|GET|/dongs|동 리스트 반환|List<Apartment>|

##### Reservation
|Method|Uri( /api/reservations )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|GET|/|예약정보 제공|Reservation|
|GET|/lists|같은 동 세대 예약정보 제공|List|
|POST|/|입주 예약|Void|
|DELETE|/|예약 취소|Void|

<br>


***
<br><br>
### 7. 회고

<br>

또한, DB 공유자원에 대한 중복접근 예방에 대한 학습 필요성을 느꼈습니다. 예약시스템 구축에 필수적 요소라고 생각하지만
단일 웹서버의 경우에서만 생각해보았지 멀티 웹서버에서 하나의 DB에 접근하는 경우에서는 생각을 해본 적이 없습니다.
단일 웹서버의 경우, 웹서버 내의 키를 사용하면 됐지만, 멀티 웹서버의 경우, DB 내에서 Isolation level, Redis
의 방식을 알 수 있었습니다. 이번 프로젝트에서는 Isolation Level로 해결했고, 락에 대한 지식이 넓어진 프로젝트였습니다.

<br>

이번 프로젝트를 진행하며 Validation에 대한 고민을 많이했습니다. 아파트 입주의 경우, 사다리차, 인력 등의
에러 하나에 발생하는 금전적 손해가 막대한 사안이기 때문에 중복예약 케이스에 대한 Validation 에 대해
특히 더 고민했던것같습니다. 