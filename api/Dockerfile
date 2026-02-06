FROM gradle:7-jdk-11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle builtFatJar --no-daemon

FROM openjdk:11
EXPOSE 443:443
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/snoozespot-api.jar
ENTRYPOINT ["java", "-jar", "/app/snoozespot-api.jar"]