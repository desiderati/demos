# Domain Module

This module contains the domain entities, repositories, and shared services of the audio track management system.

## Function in the System

The domain module is the core of the system, containing:

1. **Domain Entities**: Representations of the system's business entities
2. **Repositories**: Interfaces for accessing persisted data
3. **Services**: Business logic shared between the server and worker modules

## Module Structure

### Entities

- **Track**: Represents an audio track in the system
    - id: Unique identifier of the track
    - msgId: UUID for message identification (implements AsyncMessage)
    - author: Author/artist of the track
    - trackName: Name of the track
    - duration: Duration of the track in seconds
    - processed: Flag indicating if the track has been processed

### Repositories

- **TrackRepository**: Interface for CRUD operations on the Track entity
    - findAll(): Returns all tracks
    - findById(id): Searches for a track by ID
    - findByTrackName(trackName): Searches for tracks by name
    - save(track): Saves a track
    - delete(track): Removes a track

### Services

- **TrackService**: Service for business operations related to tracks
    - findAllTracks(): Returns all tracks
    - findById(id): Searches for a track by ID
    - findByName(trackName): Searches for tracks by name
    - saveTrack(track): Saves a track
    - updateTrack(track): Updates an existing track
    - deleteTrackById(id): Removes a track by ID

## Validations

The module uses Bean Validation to ensure data integrity:

- author: Cannot be empty
- trackName: Cannot be empty
- duration: Must be greater than or equal to 10 seconds

## Internationalization

Validation messages are internationalized, with support for the following languages:

- English (en_US)
- Portuguese (pt_BR)

## Main Dependencies

- Spring Data JPA: For data persistence
- SpringBloom Data JPA: Extensions for Spring Data
- SpringBloom JMS: Support for asynchronous messaging
- Lombok: Reduction of boilerplate code
- Jakarta Validation: Bean validation
