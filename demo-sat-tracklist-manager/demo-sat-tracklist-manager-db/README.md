# HSQLDB Database Server

This module provides an HSQLDB database server for persistent storage of system data.

## What is HSQLDB?

HSQLDB (HyperSQL Database) is a relational database management system written in Java. It is a lightweight,
high-performance database that can be run in server mode or embedded in Java applications.

## Function in the System

In the audio track management system, HSQLDB is responsible for:

1. Storing information about audio tracks
2. Persisting the processing state of tracks
3. Providing fast data access for the server and worker

## Prerequisites

* Docker v27
* Docker Compose v2.28.1

## Running the Docker container

The command below starts the HSQLDB server in a Docker container and displays the logs in real-time:

```shell
docker compose up -d && docker compose logs -f
```

## Configuration

The HSQLDB server is configured with the following properties:

- Port: 9001
- Connection URL: jdbc:hsqldb:hsql://localhost/hsqldb
- Username: sa
- Password: (empty)

## Database Structure

The database contains the following main table:

- **track**: Stores information about audio tracks
    - id: Unique identifier of the track
    - author: Author/artist of the track
    - track_name: Name of the track
    - duration: Duration of the track in seconds
    - processed: Flag indicating if the track has been processed

## Accessing the Database

To access the database directly, you can use any SQL client that supports HSQLDB, such as DBeaver or IntelliJ IDEA
Database Tools, using the connection URL mentioned above.

## Troubleshooting

If you encounter problems with the HSQLDB server:

1. Check if the Docker container is running: `docker ps | grep hsqldb`
2. Check the container logs: `docker logs hsqldb-server`
3. Restart the container: `docker compose restart hsqldb-server`
