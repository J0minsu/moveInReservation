# DB Modeling


## User
|Column|Type|Description|
|:---:|:---:|:---:|
|phone_number|varchar(20)|pk|
|password|varchar(60)|BCrypt|
|name|varchar||

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
|ApartmentId|int|fk|
|reservationTime|date||