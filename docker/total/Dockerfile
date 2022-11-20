FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/total-0.0.1-SNAPSHOT*.jar /test.jar
ENTRYPOINT exec java -jar /test.jar


