FROM amazoncorretto:11-alpine-jdk
COPY target/barber-0.0.1-SNAPSHOT.jar barber-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/barber-0.0.1-SNAPSHOT.jar"]