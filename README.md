# CoderHack Leaderboard API

## Description
This is a RESTful API service for managing a leaderboard for a coding contest platform using Spring Boot and MongoDB. It supports user registration, score updates, and badge assignments.

## Endpoints
- **GET /users**: Retrieve a list of all users sorted by score.
- **GET /users/{userId}**: Retrieve a specific user.
- **POST /users**: Register a new user.
- **PUT /users/{userId}**: Update a user's score.
- **DELETE /users/{userId}**: Deregister a user.

## Setup
1. Install [MongoDB](https://www.mongodb.com/). or I have used Mongo Atlas (Cloud) Database and connected to Db using 'Connection String'.
2. Clone this repository.
3. Run `./gradlew bootrun` to start the application.
4. Use the provided Postman collection to interact with the API.

## Postman Collection
[Download the collection here](https://elements.getpostman.com/redirect?entityId=36321085-cc6c8fc9-3427-4cfa-83e1-b52ec79c66b8&entityType=collection)
