# use the maven image as the build stage
FROM maven:3.8.1-jdk-11 AS build

# copy the application source code into the container
COPY . /app

# set the working directory
WORKDIR /app

# run the application build using maven
RUN mvn clean package

# use the openjdk image as the runtime stage
FROM arm32v7/openjdk:8-jdk-slim

# copy the JAR file generated during the build to the Docker image
COPY --from=build /app/target/*.jar /app.jar

# run the Spring Boot application inside the container
ENTRYPOINT ["java","-jar","/app.jar"]