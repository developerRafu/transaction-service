## TRANSANCTION-SERVICE
Trasanction service is a application builded for register account and account transactions.
# Applcation tecnologies
 - Java
 - Spring web
 - Spring data JPA
 - Map struct
 - H2 database
 - Docker
 - Maven
# How to run
 - First you need to download all dependencies. So you can use the following command on root project
```
$ mvn clean install
```
 - After that, you can use the 2 ways to run the application, ```docker``` or directly via ```maven```
 - Via ``maven`` you need to run on root project:
````
$ mvn spring-boot:run
````
 - Running at docker:
 - You need ``docker`` and ```docker-composed``` installed in you pc.
 - See the tutorial right here: https://docs.docker.com/engine/install/
 - After that tun the folloeing commands
 - To build the application:
````
$ mvn clean install
````
 - To config the docker compose:
````
$ docker-compose config 
````
 - To run the application:
````
docker-compose up 
````
