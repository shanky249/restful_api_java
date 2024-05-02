# Use an appropriate base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file from the build environment into the container
COPY target/message-app.jar /app

# Command to run the application
CMD ["java", "-jar", "message-app.jar"]
