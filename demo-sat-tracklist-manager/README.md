# Demo SAT Tracklist Manager

This project is an audio track management system that demonstrates a microservices architecture
using Spring Boot, Angular, and asynchronous communication via Apache Artemis MQ.

## Overview

The system allows:

- Creating, updating, listing, and deleting audio tracks
- Processing audio tracks asynchronously (simulating format conversion)
- Notifying users when processing is completed

## Architecture

The project is divided into several modules:

- **domain**: Contains domain entities, repositories, and shared services
- **server**: REST API for CRUD operations on audio tracks
- **worker**: Service that processes audio tracks asynchronously
- **static**: Angular user interface
- **artemis-server**: Apache Artemis messaging server
- **db**: HSQLDB database

## Workflow

1. The user registers an audio track through the web interface
2. The server receives the request, saves the track in the database, and sends a message to the queue
3. The worker consumes the message from the queue, processes the track (simulating a 30-second process), and updates the
   status in the database
4. A notification is sent to all users when processing is completed

## Prerequisites

* JDK 21
* Apache Maven 3.9.0
* Docker v27 and Docker Compose v2.28.1 (for artemis-server and db modules)
* Node.js and npm (for the static module)

## Dependencies

First, you must install the SpringBloom.dev project in your local environment:

```bash
git clone https://github.com/desiderati/springbloom.git

cd springbloom

mvn clean install
```

## Building the application

```bash
mvn clean package
```

## Running the application (in the order defined below)

For the system to function correctly, it is necessary to start the components in the following order:

1. [Running ArtemisMQ](demo-sat-tracklist-manager-artemis-server/README.md) - Messaging server
2. [Running HSQLDB](demo-sat-tracklist-manager-db/README.md) - Database
3. [Running the Worker](demo-sat-tracklist-manager-worker/README.md) - Asynchronous track processor
4. [Running the Server](demo-sat-tracklist-manager-server/README.md) - REST API
5. [Running the Static Content](demo-sat-tracklist-manager-static/README.md) - Web interface

## Technologies Used

- Spring Boot
- Spring Data JPA
- Spring JMS
- Apache Artemis MQ
- HSQLDB
- Angular
- SpringBloom Framework

## License

This project is licensed under the MIT License.
