FROM openjdk:8u111-jdk-alpine

ARG MONGO_URI=mongodb://root:password@localhost:27017
ARG CHAT_DB=sm

ENV MONGO_URI $MONGO_URI
ENV CHAT_DB $CHAT_DB

WORKDIR /
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8081



