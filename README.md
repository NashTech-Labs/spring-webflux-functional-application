# spring-webflux-functional-application
This spring application describes the functional approach of using Spring-Webflux to create microservices.
As an example, a basic user based application is created in this project with some CRUD operations.



## How to run:

 1. Run cassandra
 2. Run application with command mvn spring-boot:run

## Rest End-Points: 

1. Add User:

   POST URL: localhost:8080/users
   Request Body:
   { "id": 1, "name": "XYZ", "email": "xyz@gmail.com"}

2. Get User By Id:
   GET URL: localhost:8080/users/{id}

3. Get users:
   GET URL: localhost:8080/users
