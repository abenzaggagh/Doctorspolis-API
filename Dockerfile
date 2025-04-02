FROM gradle:8.11.1-alpine AS builder

ENV APP_HOME=/app

WORKDIR $APP_HOME

COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src

RUN gradle build --no-daemon --quiet

RUN gradle bootJar

FROM openjdk:21-jdk AS runner

WORKDIR $APP_HOME

COPY --from=builder /app/build/libs/Doctorspolis-API-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]