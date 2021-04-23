# API SPECIFICATION

## User
|Method|Uri( /api/users )|Description|
|:---:|:---:|:---:|

## Login
|Method|Uri|Description|
|:---:|:---:|:---:|
|POST|/login|로그인|

## Apartment
|Method|Uri( /api/apartments )|Description|
|:---:|:---:|:---:|
|GET|/|동 리스트 반환|

## Reservation
|Method|Uri( /api/reservations )|Description|
|:---:|:---:|:---:|
|GET|/|예약정보 제공(Date, Apartment)|
|POST|/|입주 예약|
|DELETE|/{id}|예약 취소|