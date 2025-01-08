# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Copy the JAR file to the container
ADD target/ticket-service-0.0.1-SNAPSHOT.jar ticket-service-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","ticket-service-0.0.1-SNAPSHOT.jar" ]