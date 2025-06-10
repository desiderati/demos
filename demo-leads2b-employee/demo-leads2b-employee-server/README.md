# Leads2B Employee Server Module

This module provides the backend API for the Leads2B Employee Management System. It's a Node.js/Express.js application
that handles CRUD operations for employee data and connects to MongoDB for data persistence.

## Overview

The server module:

- Provides RESTful API endpoints for employee management
- Handles authentication and authorization
- Connects to MongoDB for data storage
- Serves as the middleware between the frontend and database

## Prerequisites

* Node v12.9.1
* NPM v6.10.2
* MongoDB running on `localhost:27017` (see [MongoDB Module](../demo-leads2b-employee-mongodb/README.md))
* Docker and Docker Compose (for containerized deployment)

## API Endpoints

The server exposes the following main endpoints:

- `POST /auth` - User authentication
- `GET /employees` - Get all employees
- `POST /employees` - Create new employee
- `PUT /employees/:id` - Update employee
- `DELETE /employees/:id` - Delete employee

## Development Setup

### Project Installation

```bash
# Install dependencies
npm install
```

### Running in Development Mode

```bash
# Start the server
npm run start
```

The server will be available at http://localhost:3000.

## Building for Production

### Building Docker Image

```bash
# Build the Docker image
docker build -t desiderati/leads2b-employee-server:latest .

# Push to Docker Hub (optional)
docker push desiderati/leads2b-employee-server:latest
```

## Deployment

### Using Docker Compose

```bash
# Start the server with Docker Compose
docker-compose up -d && docker-compose logs -f
```

### Using Docker Directly

```bash
# Run the Docker container
docker run -it -p 3000:3000 --rm -e DATABASE_URL=<YOUR_IP_ADDRESS>:27017 desiderati/leads2b-employee-server:latest
```

Replace `<YOUR_IP_ADDRESS>` with the IP address of your MongoDB server.

## Configuration

The server can be configured using environment variables:

- `DATABASE_URL` - MongoDB connection URL (default: localhost:27017)

## Testing the API

You can test the API using tools like Postman or curl:

```bash
# Example: Get all employees
curl http://localhost:3000/employees

# Example: Authenticate
curl -X POST http://localhost:3000/auth \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'
```

## Integration with Other Modules

- This module depends on the [MongoDB Module](../demo-leads2b-employee-mongodb/README.md) for data storage
- The [Static Module](../demo-leads2b-employee-static/README.md) (frontend) communicates with this server to display and
  manage employee data
