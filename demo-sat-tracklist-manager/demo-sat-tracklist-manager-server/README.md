# Server Module

This module provides a REST API for CRUD operations of audio tracks in the system.

## Function in the System

The server module is responsible for:

1. Exposing REST endpoints for audio track management
2. Validating data received in requests
3. Persisting tracks in the database
4. Sending messages to the Artemis MQ queue when a new track is created

## REST API

### Endpoints

- **GET /v1/track**: Returns all registered tracks
- **GET /v1/track/{id}**: Searches for a track by ID
- **GET /v1/track/name/{trackName}**: Searches for tracks by name
- **POST /v1/track**: Creates a new track
- **PUT /v1/track/{id}**: Updates an existing track
- **DELETE /v1/track/{id}**: Removes a track

### Data Format

Example JSON for creating/updating a track:

```json
{
    "author": "Artist Name",
    "trackName": "Track Name",
    "duration": 180
}
```

## Configuration

The server is configured for:

- Port: 9090
- Context: /demo-sat-tracklist-manager/
- Base URL: http://localhost:9090/demo-sat-tracklist-manager/

## Main Dependencies

- Spring Boot Web: For creating the REST API
- Spring Data JPA: For data persistence
- SpringBloom JMS: For publishing asynchronous messages
- SpringBloom Web: For exception handling and notifications

## Running the application

To start the server, run:

```bash
java -Xms256m -Xmx512m -jar target/demo-sat-tracklist-manager-server-0.0.1-SNAPSHOT-exec.jar
```

## Prerequisites for Execution

Before starting the server, make sure that:

1. The HSQLDB database is running
2. The Artemis MQ server is running

## Testing the API

You can test the API using tools such as:

- cURL
- Postman
- Insomnia
- Swagger UI (available at http://localhost:9090/demo-sat-tracklist-manager/swagger-ui.html)

Example request with cURL:

```bash
# List all tracks
curl -X GET http://localhost:9090/demo-sat-tracklist-manager/api/v1/track

# Create a new track
curl -X POST http://localhost:9090/demo-sat-tracklist-manager/api/v1/track \
  -H "Content-Type: application/json" \
  -d '{"author":"Pink Floyd","trackName":"Comfortably Numb","duration":382}'
```
