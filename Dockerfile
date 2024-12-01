# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies (this step makes Docker use cache if dependencies are not changed)
COPY pom.xml .

# Run Maven to fetch the dependencies
RUN mvn dependency:go-offline

# Copy the entire project to the container
COPY . .

# Build the project with Maven (this creates the executable .jar file)
RUN mvn clean install -DskipTests

# Set the entry point to run the application
CMD ["java", "-jar", "target/ContactManager-1.0-SNAPSHOT.jar"]
