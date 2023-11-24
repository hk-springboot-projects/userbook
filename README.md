# Userbook is user management application. 
Following operations can be performed on user data -
* CREATE
* READ
* UPDATE
* DELETE

## Dependencies
* Java 17
* spring-boot 3.x
* H2

## Installation
* build project   
```
mvn clean install
```

* start application 

```
mvn spring-boot:run
```

Application can be accessed in browser or curl using this url :
```
http://localhost:8080/users?offset=0&page_size=10&sort_by=id
```

## Detailed explanation about this application in below [article](https://medium.com/@hkcodeblogs/rest-apis-using-springboot-3-userbook-application-43df796f69fd)