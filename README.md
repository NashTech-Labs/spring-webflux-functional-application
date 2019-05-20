# spring-webflux-functional-application
This spring application describes the functional approach of using Spring-Webflux to create microservices.
As an example, a basic user based application is created in this project with some CRUD operations.



How to run:

Run cassandra
Run application with command mvn spring-boot:run

Rest End-Points:

Add User:
POST URL: localhost:8080/users
Request Body:
{ "id": 1, "name": "XYZ", "email": "xyz@gmail.com"}

Get User By Id:
GET URL: localhost:8080/users/{id}

Get users:
GET URL: localhost:8080/users
