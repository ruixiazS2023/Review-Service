# Use the official Maven image with a tag specifying the JDK version to use
FROM maven:3.8.5-openjdk-17 AS build

# Copy the project files into the container
COPY src /home/app/src
COPY pom.xml /home/app

# Build the application
RUN mvn -f /home/app/pom.xml clean package

EXPOSE 8080

WORKDIR /home/app/target
CMD ["java", "-jar", "review-0.0.1-SNAPSHOT.jar"]