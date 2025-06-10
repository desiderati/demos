# Apache Artemis MQ Server

This module provides an Apache Artemis MQ server for asynchronous communication between system components.

## What is Apache Artemis?

Apache Artemis is an open-source messaging server that implements the JMS (Java Message Service) protocol. It enables
asynchronous communication between different components of a system, ensuring that messages are delivered even if the
recipient is temporarily unavailable.

## Function in the System

In the audio track management system, Artemis MQ is responsible for:

1. Receiving messages from the server when a new track is registered
2. Storing these messages in a queue called `track-queue`
3. Delivering the messages to the worker when it is available to process them

## Prerequisites

* Docker v27
* Docker Compose v2.28.1

## Running the Docker container

The command below starts the Artemis server in a Docker container and displays the logs in real-time:

```shell
docker compose up -d && docker compose logs -f
```

## Opening the Artemis console

To access the Artemis administration console, run:

```shell
docker compose exec -it artemis-server /var/lib/artemis-instance/bin/artemis shell --user artemis --password artemis
```

## Creating the processing queue

The `track-queue` is NOT automatically created when the system starts, so you need to create it manually.
Please run the following command in the Artemis console:

```shell
queue create --name=track-queue --address=track-queue --anycast --durable --preserve-on-no-consumers --auto-create-address
```

## Configuration

The Artemis server is configured with the following properties:

- Username: artemis
- Password: artemis
- Port: 61616 (CORE protocol)
- Web console: http://localhost:8161/console (username: artemis, password: artemis)

## Troubleshooting

If you encounter problems with the Artemis server:

1. Check if the Docker container is running: `docker ps | grep artemis`
2. Check the container logs: `docker logs artemis-server`
3. Restart the container: `docker compose restart artemis-server`
