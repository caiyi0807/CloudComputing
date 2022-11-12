FROM openjdk:8-jdk
VOLUME /tmp
ADD total-0.0.1-SNAPSHOT.jar total.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/total.jar"]

