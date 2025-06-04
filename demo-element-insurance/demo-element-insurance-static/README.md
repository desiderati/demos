# Element Insurance Static Module

This module implements the frontend user interface for the Element Insurance application using Angular.

## Overview

The static module provides a user-friendly web interface for interacting with the Element Insurance API. 
It allows users to manage insurance types and quotations through a modern, responsive UI.

## Features

- Dashboard with overview of insurance data
- CRUD operations for insurance types
- CRUD operations for quotations
- Form validation
- Responsive design for desktop and mobile devices
- Integration with the backend REST API

## Technology Stack

- Angular 8
- Angular Material (for UI components)
- TypeScript
- HTML/CSS
- RxJS (for reactive programming)

## Prerequisites

* Node v12.9.1
* NPM v6.10.2
* Backend server running (see `demo-element-insurance-server` module)

## Project Structure

The Angular application follows a standard structure:

```
demo-element-insurance-static/
├── src/
│   ├── app/                  # Application code
│   ├── assets/               # Static assets
│   └── environments/         # Environment configurations
```

## Development Server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload 
if you change any of the source files.

## Building the Application

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. 
Use the `--prod` flag for a production build:

```bash
ng build --prod
```

## Code Scaffolding

Run `ng generate component component-name` to generate a new component. 
You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Running Unit Tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running End-to-End Tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## API Integration

The frontend communicates with the backend REST API using Angular's HttpClient. 
The API endpoints are configured in the environment files and can be adjusted 
for different deployment environments.

## Deployment

To deploy the application:

1. Build the application with `ng build --prod`
2. Copy the contents of the `dist/` directory to your web server
3. Configure the web server to serve the application and handle routing

## Troubleshooting

If you encounter issues with the frontend application:

1. Ensure the backend server is running
2. Check browser console for JavaScript errors
3. Verify API endpoint configurations in the environment files
4. Clear browser cache if necessary

## Further Help

To get more help on the Angular CLI use `ng help` 
or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
