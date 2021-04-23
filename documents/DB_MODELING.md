# DB Modeling


## User
|Column|Type|Description|
|:---:|:---:|:---:|
|phone_number|varchar(20)|pk|
|password|varchar(60)|BCrypt|
|name|varchar||
|ApartmentId|int|fk|

## Apartment
|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|pk|
|dong|varchar(4)||
|ho|varchar(4)||

## Reservation
|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|pk|
|userId|varchar(20)|fk|
|reservationTime|date||