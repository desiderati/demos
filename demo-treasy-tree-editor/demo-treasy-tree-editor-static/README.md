# Treasy Tree Editor - Frontend Application

## Overview

This module provides the frontend web application for the Treasy Tree Editor. It's built with modern JavaScript
frameworks and offers an intuitive user interface for visualizing and manipulating tree data structures. The frontend
communicates with the backend server via RESTful API calls.

## Features

* Interactive tree visualization
* Drag-and-drop interface for tree manipulation
* Create, edit, and delete tree nodes through a user-friendly interface
* Responsive design for desktop and mobile devices
* Docker support for containerized deployment

## Technologies Used

* JavaScript/TypeScript
* Modern frontend frameworks
* Webpack for bundling
* SCSS for styling
* Docker for containerization

## Prerequisites

* Node.js v12.9.1 or higher
* NPM v6.10.2 or higher
* Backend server (see [treasy-tree-editor-server](../demo-treasy-tree-editor-server/README.md))

## Running the Project for Development

### Project Setup

```bash
git clone https://github.com/desiderati/demos.git

cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-static

npm install
```

### Building the Project

To compile the project for production:

```bash
npm run build
```

### Development Mode with Hot-Reload

For development with automatic reloading on code changes:

```bash
npm run start
```

This will start a development server with hot-reloading enabled.

## Running the Project Using Docker

### Option 1: Using Docker Compose (Recommended)

```bash
docker-compose up -d && docker-compose logs -f
```

### Option 2: Using Docker Directly

```bash
docker run -it -p 8000:80 --rm -e API_URL=localhost:9090 desiderati/treasy-tree-editor-static:latest
```

The `API_URL` environment variable should point to your backend server.

## Building and Publishing the Docker Image

If you want to build and push your own Docker image:

```bash
docker build -t desiderati/treasy-tree-editor-static:latest .

docker push desiderati/treasy-tree-editor-static:latest
```

## Accessing the Application

Once the application is running, you can access it at:

```
http://localhost:8000
```

## Configuration

The frontend application can be configured using environment variables:

* `API_URL` - URL of the backend API server (default: localhost:9090)
* `PORT` - Port for the development server (default: 8000)

## Troubleshooting

If you encounter issues:

1. Ensure the backend server is running and accessible
2. Check the browser's console for JavaScript errors
3. Verify that port 8000 is not being used by another service
4. Make sure all dependencies are installed with `npm install`
