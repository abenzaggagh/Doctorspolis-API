FROM adoptopenjdk/openjdk11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build/libs/*.jar doctorspolis.jar
EXPOSE 8080
CMD ["java", "-jar", "doctorspolis.jar"]