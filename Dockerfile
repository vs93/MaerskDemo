# Use the official OpenJDK 11 base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the executable jar file from the target directory into the container
COPY target/Demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
