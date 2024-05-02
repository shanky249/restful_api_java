# Message App

This is a sample messaging application with APIs built using Spring Boot. The application includes APIs for handling JWT, messaging, and accessing the Rick and Morty API.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- JDK (Java Development Kit) installed on your local machine.
- Apache Maven installed on your local machine.
- Docker installed on your local machine if you plan to run the application in a containerized environment.

## Building the Application

To build the application, you can use Maven:

```
mvn clean install
```

This command will compile the code, run tests, and package the application into a JAR file.

## APIs

1. **JWT Management**
    - **Build JWT**: `/jwt/build` - Endpoint to build JWT tokens with customizable issuer and subject.
    - **Validate JWT**: `/jwt/validate` - Endpoint to validate JWT tokens.

2. **Message Management**
    - **Get all messages**: `/message/all` - Endpoint to fetch all messages.
    - **Get message by ID**: `/message/{id}` - Endpoint to fetch a message by its ID.
    - **Create a new message**: `POST /message` - Endpoint to create a new message.
    - **Update an existing message**: `PUT /message/{id}` - Endpoint to update an existing message.
    - **Delete a message by ID**: `DELETE /message/{id}` - Endpoint to delete a message by its ID.

3. **Rick and Morty API**
    - **Get character by ID**: `/api/rickandmorty/character/{characterId}` - Endpoint to fetch character information from the Rick and Morty API by character ID.

## Testing

To run the tests, you can use Maven:

```
mvn test
```

## Running Locally

To run the application locally, you can use Maven as well:

```
mvn spring-boot:run
```

The application will start on port 8080 by default.

## Building and Running Docker Image

To build a Docker image of the application, navigate to the project directory and run:

```
docker build -t message-app .
```

Once the image is built, you can run it using Docker:

```
docker run -p 8080:8080 message-app
```

This will start the containerized application on port 8080.

## Accessing Swagger Documentation

The API documentation is available through Swagger UI. Once the application is running locally, you can access it at:

[Swagger UI](http://127.0.0.1:8080/swagger-ui/index.html)

This page provides detailed information about the available endpoints and how to interact with them.