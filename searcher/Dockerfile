FROM openjdk:8u111-jdk-alpine
WORKDIR /
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8082



