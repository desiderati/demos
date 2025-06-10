# Worker Module

This module is responsible for asynchronous processing of audio tracks in the system.

## Function in the System

The worker module is responsible for:

1. Consuming messages from the `track-queue` in Artemis MQ
2. Processing audio tracks asynchronously
3. Updating the status of tracks in the database
4. Sending notifications to users when processing is completed

## Track Processing

When a new track is registered through the REST API, the server sends a message to the `track-queue`. The worker consumes this message and:

1. Simulates an audio conversion process (waits 30 seconds)
2. Marks the track as processed (processed = true)
3. Saves the updated track in the database
4. Sends a notification to all connected users

## Error Handling

The worker has an error handling mechanism that:

1. Captures exceptions during processing
2. Logs errors
3. Sends error notifications to users
4. Implements retry policies for failed messages

## Configuration

The worker is configured to:

- Consume messages from the `track-queue`
- Use the same database as the server
- Send notifications through the SpringBloom notification system

## Main Dependencies

- Spring Boot: Base framework
- Spring JMS: For message consumption
- SpringBloom JMS: Extensions for asynchronous processing
- SpringBloom Web Notification: For sending notifications

## Running the application

To start the worker, run:

```bash
java -Xms256m -Xmx512m -jar target/demo-sat-tracklist-manager-worker-0.0.1-SNAPSHOT-exec.jar
```

## Prerequisites for Execution

Before starting the worker, make sure that:

1. The HSQLDB database is running
2. The Artemis MQ server is running
3. The `track-queue` has been created in Artemis MQ

## Monitoring

The worker logs detailed information about processing. You can monitor worker activity through:

1. Application logs
2. The Artemis MQ administration console
3. Notifications sent to users

## Troubleshooting

If the worker is not processing messages:

1. Check if Artemis MQ is running
2. Confirm that the `track-queue` exists
3. Check if there are messages in the queue through the Artemis console
4. Restart the worker if necessary
