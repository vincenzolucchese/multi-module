
FROM openjdk:8-jdk-alpine as build
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

RUN ./mvnw install -DskipTests
RUN mkdir -p portal/target/dependency && (cd portal/target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/portal/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.vince.portal.application.Application"]

