# Static Module (Frontend Angular)

This module provides the user interface for the audio track management system, developed in Angular.

## Function in the System

The static module is responsible for:

1. Providing a graphical interface for interaction with the system
2. Allowing the registration, viewing, editing, and deletion of audio tracks
3. Displaying the processing status of tracks
4. Showing real-time notifications about processing

## Main Features

- **Track Listing**: Displays all registered tracks with information such as name, author, duration, and processing status
- **Track Registration**: Form to add new tracks to the system
- **Track Editing**: Allows modifying information of existing tracks
- **Track Deletion**: Removes tracks from the system
- **Notifications**: Displays alerts when track processing is completed

## Technologies Used

- Angular 19
- TypeScript
- RxJS
- Angular Material
- WebSockets (for real-time notifications)

## Prerequisites

- Node.js (version 18 or higher)
- npm (version 9 or higher)
- Angular CLI (version 19.2.13)

## Configuration

The frontend is configured to communicate with the backend at the following addresses:

- REST API: http://localhost:9090/demo-sat-tracklist-manager/api/v1/
- WebSocket for notifications: ws://localhost:9090/demo-sat-tracklist-manager/atm/notification/

## Development server

To start the development server, run:

```bash
ng serve
```

After starting the server, open your browser and access `http://localhost:4200/`. The application will automatically reload whenever you modify any source file.

## Code scaffolding

The Angular CLI includes powerful tools for code generation. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project, run:

```bash
ng build
```

This will compile the project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To run unit tests with [Karma](https://karma-runner.github.io), use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) tests, run:

```bash
ng e2e
```

The Angular CLI does not come with an e2e testing framework by default. You can choose one that meets your needs.

## Additional Resources

For more information about using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
