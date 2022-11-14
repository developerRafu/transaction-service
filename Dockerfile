FROM openjdk:11-jdk-slim
MAINTAINER pismo.com
COPY target/credit-service-0.0.1-SNAPSHOT.jar credit-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/credit-service-0.0.1-SNAPSHOT.jar"]
