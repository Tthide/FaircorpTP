
# FaircorpTP
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


Implementation of the faircorp app described on the following tutorial : https://dev-mind.fr/formations.html

## Online version
          
Deployed on Clever Cloud :  faircorp-thibault-borde.cleverapps.io 

Link to swagger-ui of the api : faircorp-thibault-borde.cleverapps.io/swagger-ui 

## User login

Currently there are only 2 users.

ADMIN which has the authorization to access the API. <br>
USER who doesn't have any particular authorization. <br>

ADMIN username : admin <br>
      password : adminpass <br>
      
      
USER  username : user <br> 
      password : myPassword   <br>

## Entities

It allows the management of 3 entities :
- Rooms
- Windows
- Heaters

## Remote REST API

It can also fetch Addresses from https://api-adresse.data.gouv.fr/

See https://adresse.data.gouv.fr/api-doc/adresse for documentation of the remote API

## Powered by:
- Java        (11.0.17)
- Spring      (2.7.3)
- Gradle      (7.5.1)
- JUnit       (Latest)
- H2          (Latest)
- Swagger 
- Log4j2      (2.7.12)
- Checkstyle  (7.8.1)
- Spotbugs    (Latest)



## Compile

```Linux
./gradlew bootRun
```                
    

# API Documentation

## Room

- GET /api/rooms : get all rooms
- GET /api/rooms/{id} : get a room by id
- POST /api/rooms : create a room
- DELETE /api/rooms/{id} : delete a room by id
- PUT /api/rooms/{id}/switchHeaters : switch heaters of a room by id
- PUT /api/rooms/{id}/switchWindows : switch windows of a room by id
- GET /api/rooms/building/{buildingId} : get all rooms of a building by id

## Window

- GET /api/windows : get all windows
- GET /api/windows/room/{roomId} : get all windows of a room by id
- POST /api/windows : create a window
- DELETE /api/windows/{id} : delete a window by id
- PUT /api/windows/{id}/switch : switch a window by id

## Heater

- GET /api/heaters : get all heaters
- GET /api/heaters/room/{roomId} : get all heaters of a room by id
- POST /api/heaters : create a heater
- DELETE /api/heaters/{id} : delete a heater by id
- PUT /api/heaters/{id}/switch : switch a heater by id

## Adress-search-service

-GET /api/address : find address corresponding to a name


# Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
