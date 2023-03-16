FROM openjdk:11

WORKDIR /app

ARG JAR_FILE=./build/libs/timedeal-0.0.1-SNAPSHOT.jar

COPY  /app/build/libs/*.jar app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]