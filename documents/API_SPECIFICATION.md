# API SPECIFICATION

## User
|Method|Uri( /api/users )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|POST|/register|회원 등록|Void|
## Login
|Method|Uri|Description|Return Type|
|:---:|:---:|:---:|:---:|
|POST|/login|로그인|User|
|POST|/logout|로그아웃|Void|
## Apartment
|Method|Uri( /api/apartments )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|GET|/|동 리스트 반환|List<Apartment>|

## Reservation
|Method|Uri( /api/reservations )|Description|Return Type|
|:---:|:---:|:---:|:---:|
|GET|/|예약정보 제공|Reservation|
|GET|/|같은 동 세대 예약정보 제공|List|
|POST|/|입주 예약|Void|
|DELETE|/{id}|예약 취소|Void|

