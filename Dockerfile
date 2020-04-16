
#FROM openjdk:8-jdk-alpine as build
FROM openjdk:8
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY application application
COPY apps-facade apps-facade
COPY apps-service apps-service
COPY client-ws-ssh client-ws-ssh
COPY form-submission form-submission
COPY gradle gradle
COPY library library
COPY portal portal

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java","-jar","portal/target/portal-0.0.1-SNAPSHOT.jar"]

