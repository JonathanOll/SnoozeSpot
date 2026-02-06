FROM gradle:8.7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:11-jdk
RUN mkdir /database
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/snoozespot-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/snoozespot-api.jar"]
