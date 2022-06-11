FROM adoptopenjdk/openjdk11:alpine-jre

RUN mkdir -p /doctorspolis

ADD config /doctorspolis/config

ADD target/*.jar /software/doctorspolis.jar

WORKDIR /doctorspolis

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar doctorspolis.jar
